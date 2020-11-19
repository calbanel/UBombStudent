

package fr.ubx.poo.model.decor.obstructdecor;

import fr.ubx.poo.game.Direction;
import fr.ubx.poo.game.Game;
import fr.ubx.poo.game.Position;
import fr.ubx.poo.game.World;
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

    public boolean move(Direction direction, Position boxPos, Game game){ //return true if the box is movable, false if is not
        boolean canMove = false;
        World world = game.getWorld();
        Position nextPos = direction.nextPosition(boxPos);

        if (boxCanMove(nextPos,game)) {
            world.set(nextPos, this);
            world.clear(boxPos);
            canMove = true;
        }

        return canMove;
    }

    private boolean boxCanMove(Position nextPos, Game game){

        if (!nextPos.inside(game.getWorld().dimension))
            return false;

        Decor decor = game.getWorld().get(nextPos);
        if (decor != null)
            return false;

        ArrayList<Monster> monsters = game.getMonster();
        for (Monster monster : monsters) {
            if (nextPos.equals(monster.getPosition()))
                return false;
        }

        return true;
    }
}

