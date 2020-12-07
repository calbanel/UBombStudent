/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.model.decor;

import fr.ubx.poo.game.Position;
import fr.ubx.poo.game.World;
import fr.ubx.poo.model.Entity;

/***
 * A decor is an element that does not know its own position in the grid.
 */
public abstract class Decor extends Entity {
    public boolean canWalkOn(){
        return true;
    }
    public boolean nonPlayerCanWalkOn(){
        return canWalkOn();
    }
    public boolean isBox(){
        return false;
    }
    public boolean isExplosion(){
        return false;
    }
    public abstract boolean isDestructible();
    public boolean isTriggerDecor(){
        return false;
    }
    public boolean isClosedDoor(){return false;}
    public void hitByBomb(World world, Position position){
        if (isDestructible()){
            world.clear(position);
        }
    }
}
