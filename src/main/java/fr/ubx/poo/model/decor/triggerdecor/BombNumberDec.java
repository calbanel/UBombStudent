package fr.ubx.poo.model.decor.triggerdecor;

import fr.ubx.poo.game.World;
import fr.ubx.poo.model.go.character.Player;

public class BombNumberDec extends TriggerDecor {
    @Override
    public String toString() {
        return "BombNumberDec";
    }

    public void trigger(Player player, World world) {
        if (player.getBombNb() > 1) {
            player.setBombNb(player.getBombNb() - 1);
            player.setBombBag(player.getBombBag()-1);
        }
        world.clear(player.getPosition());


    }
}
