/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.model.go.character;

import fr.ubx.poo.game.Direction;
import fr.ubx.poo.game.Position;
import fr.ubx.poo.game.Timer;
import fr.ubx.poo.game.damage.DamageOnPlayer;
import fr.ubx.poo.game.Game;
import fr.ubx.poo.model.decor.Decor;
import fr.ubx.poo.model.decor.obstructdecor.Box;
import fr.ubx.poo.model.decor.triggerdecor.TriggerDecor;
import fr.ubx.poo.model.go.Bomb;
import fr.ubx.poo.model.go.GameObject;

import java.util.ArrayList;


public class Player extends Alive {

    private int bombNb;
    private int bombBag;
    private int bombRange;
    private int keysNb;
    private boolean winner;
    private boolean moveRequested = false;
    private boolean invincibility;
    private Timer invincibilityTimer = null;
    private static long lastUpdate = 0;
    private ArrayList<Bomb> bombs;

    public Player(Game game, Position position) {
        super(game, position, game.getInitPlayerLives());
        this.bombNb = 1;
        this.bombBag = 1;
        this.bombRange = 1;
        this.keysNb = 0;
        this.invincibility = false;
        this.bombs = new ArrayList<>();
    }

    public void win(){
        winner = true;
    }

    public boolean isWinner() {
        return winner;
    }

    public boolean isPlayer(){
        return true;
    }

    public void update(long now) {

        //SAVE THE UPDATE TIME
        lastUpdate = now;

        //BOMBS GESTION
        bombsUpdate(now);

        //INVINCIBILITY GESTION
        invincibilityUpdate(now);

        //PLAYER MOVE GESTION
        if (moveRequested) {
            if (canMove(direction)) {
                doMove(direction);
            }
        }
        moveRequested = false;
    }


    //PLAYER MOVEMENT

    public void requestMove(Direction direction) {
        if (direction != this.direction) {
            this.direction = direction;
        }
        moveRequested = true;
    }

    protected void moveConsequence(){

        Decor decor = game.getWorld().get(this.getPosition());
        if (decor != null && decor.isTriggerDecor()) {
            TriggerDecor tDecor = (TriggerDecor) decor;
            tDecor.trigger(this, game.getWorld());
        }

        game.getMonsters().stream().filter(m -> m.getPosition().equals(getPosition())).findAny().ifPresent(monster -> walkOnMonster());

    }

    @Override
    protected boolean canMoveOnDecor(Decor decor) {
        boolean canMove = decor.canWalkOn();
        if (decor.isBox()){
            Box box = (Box) decor;
            canMove = boxMove(box); //move return true if the box moved
        }
        return canMove;
    }

    private void walkOnMonster(){
        DamageOnPlayer damage = new DamageOnPlayer();
        damage.take(this);
    }


    //INVINCIBILITY

    private void invincibilityUpdate(long now){
        if (invincibility){
            if(invincibilityTimer == null)
                invincibilityTimer = new Timer(now,1000000000L);
            invincibilityTimer.update(now);
            if(invincibilityTimer.isFinish()){
                invincibility = false;
                invincibilityTimer = null;
            }
        }
    }

    public void setInvincibility(boolean invincibility){
        this.invincibility = invincibility;
    }

    public boolean isInvincible(){
        return invincibility;
    }


    //BOX MOVE

    private boolean boxMove(Box box){
        Position boxPos = direction.nextPosition(getPosition());

        if (boxCanMove(boxPos)){
            box.move(boxPos, direction.nextPosition(boxPos), game);
            return true;
        }
        return false;
    }

    private boolean boxCanMove(Position boxPos){
        Position nextPos = direction.nextPosition(boxPos);
        if (!nextPos.inside(game.getWorld().dimension))
            return false;

        if (!game.getWorld().isEmpty(nextPos))
            return false;

        GameObject go = game.getGameObjectAtPos(nextPos);
        return go == null;
    }


    //BOMBS

    public int getBombNb() {
        return bombNb;
    }

    public void setBombNb(int bombNb) {
        this.bombNb = bombNb;
    }

    public int getBombBag() {
        return bombBag;
    }

    public void setBombBag(int bombBag) {
        if(bombBag <= 0){
            bombBag = 0;
        }
        this.bombBag = Math.min(bombBag, bombNb);
    }

    public int getBombRange() {
        return bombRange;
    }

    public void setBombRange(int bombRange) {
        this.bombRange = bombRange;
    }

    private void bombsUpdate(long now){
        if(!bombs.isEmpty()) {
            bombs.forEach(b -> b.update(now));
            System.out.println(bombs.size());
            bombs.removeIf(Bomb::isCleared);
            System.out.println(bombs.size());
        }
    }

    public void newBomb(){
        if (bombs.size() <= bombBag) {
            bombs.add(new Bomb(game, getPosition(), lastUpdate, bombRange));
            bombBag--;
        }

    }

    public ArrayList<Bomb> getBombs(){
        return bombs;
    }


    //WORLDS

    public int getKeysNb() {
        return keysNb;
    }

    public void setKeysNb(int keysNb) {
        this.keysNb = keysNb;
    }

}
