package fr.ubx.poo.model.decor.triggerdecor;
import fr.ubx.poo.game.World;
import fr.ubx.poo.model.go.character.Player;

public class DoorPrevOpened extends DoorOpened{
    @Override
    public String toString() {
        return "DoorPrevOpened";
    }

    public void trigger(Player player, World world) {
        player.changeLevel(player.getCurrentLevel()-1,"prev");
    }
}
