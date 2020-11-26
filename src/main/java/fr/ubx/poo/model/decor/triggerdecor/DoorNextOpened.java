package fr.ubx.poo.model.decor.triggerdecor;

import fr.ubx.poo.game.World;
import fr.ubx.poo.model.go.character.Player;

public class DoorNextOpened extends DoorOpened{
    @Override
    public String toString() {
        return "DoorNextOpened";
    }

    public void trigger(Player player, World world) {
        System.out.println("not implemented yet");
    }
}
