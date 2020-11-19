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

    public void requestMove(Direction direction) {
        if (direction != this.direction) {
            this.direction = direction;
        }
        moveRequested = true;
    }

    public void update(long now) {
        if (moveRequested) {
            if (canMove(direction)) {
                doMove(direction);
            }
        }
        moveRequested = false;
    }

    protected void moveConsequence(){

            Decor decor = game.getWorld().get(this.getPosition());
            if (decor != null) {
                decor.trigger(this, game.getWorld());
            }

            ArrayList<Monster> monsters = game.getMonster();
            for (Monster monster : monsters) {
                if (this.getPosition().equals(monster.getPosition()))
                    walkOnMonster();
            }
    }

    @Override
    protected boolean canMoveOnDecor(Decor decor) {
        boolean canMove = decor.canWalkOn();
        if (decor.isBox()){
            Box box = (Box) decor;
            canMove = box.move(this.getDirection(),getPosition(),game.getWorld());
        }
        return canMove;
    }

    private void walkOnMonster(){
        PlayerDamage damage = new PlayerDamage();
        damage.take(this);
    }

    public boolean isPlayer(){
        return true;
    }


}
