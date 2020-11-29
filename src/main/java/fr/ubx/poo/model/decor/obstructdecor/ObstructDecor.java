package fr.ubx.poo.model.decor.obstructdecor;

import fr.ubx.poo.game.World;
import fr.ubx.poo.model.decor.Decor;
import fr.ubx.poo.model.go.character.Player;

public abstract class ObstructDecor extends Decor {
    public boolean canWalkOn(){
        return false;
    }
    public void trigger(Player player, World world){
        System.out.println("Error trigger on obstruct decor");
    }
    public boolean isDestructible(){
        return false;
    }
}
