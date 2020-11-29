package fr.ubx.poo.model.decor.triggerdecor;

import fr.ubx.poo.game.World;
import fr.ubx.poo.model.decor.Decor;
import fr.ubx.poo.model.go.character.Player;


public abstract class TriggerDecor extends Decor {
    public abstract void trigger(Player player, World world);
    public boolean isTriggerDecor(){
        return true;
    }
    public boolean isDestructible(){
        return true;
    }
}

