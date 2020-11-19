/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.model.go.character;

import fr.ubx.poo.game.Direction;
import fr.ubx.poo.game.Position;
import fr.ubx.poo.game.damage.PlayerDamage;
import fr.ubx.poo.game.gridevent.GridEvent;
import fr.ubx.poo.game.gridevent.MonsterGridEvent;
import fr.ubx.poo.game.gridevent.PrincessGridEvent;
import fr.ubx.poo.model.Entity;
import fr.ubx.poo.model.Movable;
import fr.ubx.poo.model.decor.*;
import fr.ubx.poo.model.decor.triggerdecor.Monster;
import fr.ubx.poo.model.decor.triggerdecor.Princess;
import fr.ubx.poo.model.go.GameObject;
import fr.ubx.poo.game.Game;

public class Player extends GameObject implements Movable {

    private boolean alive = true;
    Direction direction;
    private boolean moveRequested = false;
    private int lives;
    private int bombNb;
    private int bombRange;
    private boolean winner;

    public Player(Game game, Position position) {
        super(game, position);
        this.direction = Direction.S;
        this.lives = game.getInitPlayerLives();
        this.bombNb = 1;
        this.bombRange = 1;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public Direction getDirection() {
        return direction;
    }

    public void requestMove(Direction direction) {
        if (direction != this.direction) {
            this.direction = direction;
        }
        moveRequested = true;
    }

    @Override
    public boolean canMove(Direction direction) {
        boolean canMove;

        Position nextPos = direction.nextPosition(getPosition());
        canMove = nextPos.inside(game.getWorld().dimension);

        Decor decor = game.getWorld().get(nextPos);
        if (decor != null)
            canMove = decor.canWalkOn();

        return canMove;
    }

    public void doMove(Direction direction) {
        PlayerDamage damage = new PlayerDamage();
        Position nextPos = direction.nextPosition(getPosition());
        setPosition(nextPos);

        Decor decor = game.getWorld().get(nextPos);
        if(decor != null)
            decor.trigger(this);

    }

    public void update(long now) {
        if (moveRequested) {
            if (canMove(direction)) {
                doMove(direction);
            }
        }
        moveRequested = false;
    }

    public void win(){
        winner = true;
    }

    public boolean isWinner() {
        return winner;
    }

    public void die(){
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

}
