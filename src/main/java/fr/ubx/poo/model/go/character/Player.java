/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.model.go.character;

import fr.ubx.poo.game.Direction;
import fr.ubx.poo.game.Position;
import fr.ubx.poo.game.World;
import fr.ubx.poo.game.damage.PlayerDamage;
import fr.ubx.poo.game.Game;
import fr.ubx.poo.model.decor.Decor;
import fr.ubx.poo.model.decor.obstructdecor.Box;

import java.util.ArrayList;

public class Player extends Alive {

    private int bombNb;
    private int bombRange;
    private int keysNb;
    private boolean winner;
    private boolean moveRequested = false;
    private boolean invincibility = false;
    private static long lastUpdate = 0;

    public Player(Game game, Position position) {
        super(game, position, game.getInitPlayerLives());
        this.bombNb = 1;
        this.bombRange = 1;
        this.keysNb = 0;
    }

    public int getKeysNb() {
        return keysNb;
    }

    public void setKeysNb(int keysNb) {
        this.keysNb = keysNb;
    }

    public int getBombNb() {
        return bombNb;
    }

    public void setBombNb(int bombNb) {
        this.bombNb = bombNb;
    }

    public int getBombRange() {
        return bombRange;
    }

    public void setBombRange(int bombRange) {
        this.bombRange = bombRange;
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

    public void setInvincibility(boolean invincibility){
        this.invincibility = invincibility;
    }

    public boolean isInvincible(){
        return invincibility;
    }

    public void requestMove(Direction direction) {
        if (direction != this.direction) {
            this.direction = direction;
        }
        moveRequested = true;
    }

    public void update(long now) {

        if (invincibility)
            updateInvincibility(now);
        else
            lastUpdate = now; //collect the time at each update

        if (moveRequested) {
            if (canMove(direction)) {
                doMove(direction);
            }
        }
        moveRequested = false;
    }

    public void updateInvincibility(long now){
        if(now - lastUpdate >= 1000000000L){ //1 second
            invincibility = false;
            lastUpdate = now;
        }
    }

    protected void moveConsequence(){

        Decor decor = game.getWorld().get(this.getPosition());
        if (decor != null) {
            decor.trigger(this, game.getWorld());
        }

        Monster monster = game.getMonsters().stream().filter(m -> m.getPosition().equals(getPosition())).findAny().orElse(null);
        if (monster != null)
            walkOnMonster();

    }

    @Override
    protected boolean canMoveOnDecor(Decor decor) {
        boolean canMove = decor.canWalkOn();
        if (decor.isBox()){
            Box box = (Box) decor;
            canMove = box.move(this.direction, direction.nextPosition(getPosition()), game); //move return true if the box moved
        }
        return canMove;
    }

    private void walkOnMonster(){
        PlayerDamage damage = new PlayerDamage();
        damage.take(this);
    }


}
