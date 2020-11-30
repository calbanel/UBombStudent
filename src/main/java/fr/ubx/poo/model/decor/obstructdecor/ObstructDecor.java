package fr.ubx.poo.model.decor.obstructdecor;

import fr.ubx.poo.model.decor.Decor;

public abstract class ObstructDecor extends Decor {
    public boolean canWalkOn(){
        return false;
    }
    public boolean isDestructible(){
        return false;
    }
}
