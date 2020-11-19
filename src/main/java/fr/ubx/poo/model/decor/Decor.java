/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.model.decor;

import fr.ubx.poo.game.World;
import fr.ubx.poo.model.Entity;
import fr.ubx.poo.model.go.character.Player;

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
    public abstract void trigger(Player player, World world);
}
