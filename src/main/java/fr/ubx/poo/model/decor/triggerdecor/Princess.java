package fr.ubx.poo.model.decor.triggerdecor;


import fr.ubx.poo.game.World;
import fr.ubx.poo.model.go.character.Player;

public class Princess extends TriggerDecor{
    @Override
    public String toString() {
        return "Princess";
    }

    @Override
    public boolean nonPlayerCanWalkOn() {
        return false;
    }

    @Override
    public void trigger(Player player, World world) {
        player.win();
    }

    @Override
    public boolean isDestructible() {
        return false;
    }
}
