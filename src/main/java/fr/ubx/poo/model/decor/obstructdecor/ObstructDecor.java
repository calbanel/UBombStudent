package fr.ubx.poo.model.decor.obstructdecor;

import fr.ubx.poo.model.decor.Decor;
import fr.ubx.poo.model.go.character.Player;

public abstract class ObstructDecor extends Decor {
    public boolean canWalkOn(){
        return false;
    }
    public void trigger(Player player){
        System.out.println("Error trigger on obstruct decor");
    }
}