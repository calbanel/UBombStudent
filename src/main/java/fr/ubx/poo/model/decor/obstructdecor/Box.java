

package fr.ubx.poo.model.decor.obstructdecor;

import fr.ubx.poo.game.Direction;
import fr.ubx.poo.game.Game;
import fr.ubx.poo.game.Position;
import fr.ubx.poo.model.Movable;
import fr.ubx.poo.model.decor.Decor;
import fr.ubx.poo.model.go.character.Monster;

import java.util.ArrayList;

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
        game.getWorld().set(nextPos, this);
        game.getWorld().clear(pos);
    }
}

