

package fr.ubx.poo.model.decor.obstructdecor;

import fr.ubx.poo.game.Direction;
import fr.ubx.poo.game.Game;
import fr.ubx.poo.game.Position;
import fr.ubx.poo.model.Movable;
import fr.ubx.poo.model.decor.Decor;
import fr.ubx.poo.model.go.character.Monster;

import java.util.ArrayList;

public class Box extends ObstructDecor implements Movable {
    @Override
    public String toString() {
        return "Box";
    }

    @Override
    public boolean isBox(){
        return true;
    }

    private static Game game = null;
    private static Position boxPos = null;

    public boolean move(Direction direction, Position boxPos, Game game){ //return true if the box is movable, false if is not
        this.game = game;
        this.boxPos = boxPos;

        if (canMove(direction)){
            doMove(direction);
            return true;
        }
        return false;
    }

    @Override
    public boolean canMove(Direction direction) {
        Position nextPos = direction.nextPosition(boxPos);
        if (!nextPos.inside(game.getWorld().dimension))
            return false;

        Decor decor = game.getWorld().get(nextPos);
        if (decor != null)
            return false;

        ArrayList<Monster> monsters = game.getMonsters();
        for (Monster monster : monsters) {
            if (nextPos.equals(monster.getPosition()))
                return false;
        }

        return true;
    }

    @Override
    public void doMove(Direction direction) {
        game.getWorld().set(direction.nextPosition(boxPos), this);
        game.getWorld().clear(boxPos);
    }
}

