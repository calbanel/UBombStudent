/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.model.decor;

import fr.ubx.poo.game.World;
import fr.ubx.poo.model.Entity;
import fr.ubx.poo.model.go.character.Alive;

/***
 * A decor is an element that does not know its own position in the grid.
 */
public class Decor extends Entity {
    public boolean canWalkOn(){
        return true;
    }
    public void trigger(Alive alive, World world){
        System.out.println("This decor isn't finish yet.");
    }
}
