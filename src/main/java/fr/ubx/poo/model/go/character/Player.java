/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.model.go.character;

import fr.ubx.poo.game.Direction;
import fr.ubx.poo.game.Position;
import fr.ubx.poo.game.PlayerDamage;
import fr.ubx.poo.model.Movable;
import fr.ubx.poo.model.decor.*;
import fr.ubx.poo.model.go.GameObject;
import fr.ubx.poo.game.Game;

import java.util.function.BiConsumer;

public class Player extends GameObject implements Movable {

    private final boolean alive = true;
    Direction direction;
    private boolean moveRequested = false;
    private int lives;
    private boolean winner;

    public Player(Game game, Position position) {
        super(game, position);
        this.direction = Direction.S;
        this.lives = game.getInitPlayerLives();
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
        Decor decor = game.getWorld().get(nextPos);
        canMove = nextPos.inside(game.getWorld().dimension);
        if(decor instanceof Tree ||decor instanceof Stone )
            canMove = false;
        if(decor instanceof Box ) //plus tard il ramassera la caisse
            canMove = false;
        return canMove;
    }

    public void doMove(Direction direction) {
        PlayerDamage damage = new PlayerDamage();
        Position nextPos = direction.nextPosition(getPosition());
        setPosition(nextPos);
        Decor decor = game.getWorld().get(nextPos);
        if(decor instanceof Monster) {
            damage.take(this);
        }

    }

    public void update(long now) {
        if (moveRequested) {
            if (canMove(direction)) {
                doMove(direction);
            }
        }
        moveRequested = false;
    }

    public boolean isWinner() {
        return winner;
    }

    public boolean isAlive() {
        return alive;
    }

}
