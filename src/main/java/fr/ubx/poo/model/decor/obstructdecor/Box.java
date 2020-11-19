

package fr.ubx.poo.model.decor.obstructdecor;

import fr.ubx.poo.game.Direction;
import fr.ubx.poo.game.Position;
import fr.ubx.poo.game.World;
import fr.ubx.poo.model.decor.Decor;

public class Box extends ObstructDecor {
    @Override
    public String toString() {
        return "Box";
    }

    @Override
    public boolean isBox(){
        return true;
    }

    public boolean move(Direction direction, Position position, World world){ //return true if the box is movable, false if is not
        boolean canMove;
        Position nextPos = direction.nextPosition(direction.nextPosition(position));
        Decor decor = world.get(nextPos);
        if (decor == null && nextPos.inside(world.dimension)){
            world.set(nextPos,this);
            world.clear(direction.nextPosition(position));
            canMove = true;
        }
        else{
            canMove = false;
        }
        return canMove;
    }
}

