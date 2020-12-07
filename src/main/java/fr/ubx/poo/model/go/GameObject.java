/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.model.go;

import fr.ubx.poo.game.Position;
import fr.ubx.poo.game.Game;
import fr.ubx.poo.game.World;
import fr.ubx.poo.model.Entity;

/***
 * A GameObject can acces the game and knows its position in the grid.
 */
public abstract class GameObject extends Entity {
    protected final Game game;
    protected int currentLevel;
    private Position position;


    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public GameObject(Game game, Position position, int currentLevel) {
        this.game = game;
        this.position = position;
        this.currentLevel = currentLevel;
    }

    public boolean isPlayer() {return false;}
    public boolean isBomb() {return false;}
    public boolean isMonster() {return false;}
    public World getCurrentWorld(){return game.getWorlds().get(this.currentLevel-1);}
}
