package fr.ubx.poo.model.decor.triggerdecor;
import fr.ubx.poo.game.World;
import fr.ubx.poo.model.go.character.Player;

public class Heart extends TriggerDecor{
    @Override
    public String toString() {
        return "Heart";
    }

    public void trigger(Player player, World world) {
        player.setLives(player.getLives()+1);
        world.clear(player.getPosition());
    }
}
