package fr.ubx.poo.model.decor.triggerdecor;

import fr.ubx.poo.game.World;
import fr.ubx.poo.model.go.character.Player;

public abstract class DoorOpened extends TriggerDecor{
    public boolean nonPlayerCanWalkOn() {
        return false;
    }
    public abstract void trigger(Player player, World world);
    public boolean isDestructible(){
        return false;
    }
}
