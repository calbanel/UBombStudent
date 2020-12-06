package fr.ubx.poo.model.decor.obstructdecor;

import fr.ubx.poo.game.Game;
import fr.ubx.poo.game.Position;

public class Box extends ObstructDecor {
    @Override
    public String toString() {
        return "Box";
    }

    @Override
    public boolean isBox(){
        return true;
    }

    public void move(Position pos, Position nextPos, Game game) {
        game.getCurrentWorld().set(nextPos, this);
        game.getCurrentWorld().clear(pos);
    }

    public boolean isDestructible(){
        return true;
    }
}

