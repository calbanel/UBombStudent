package fr.ubx.poo.model.decor.triggerdecor;

import fr.ubx.poo.game.World;
import fr.ubx.poo.model.go.character.Player;

public class BombRangeInc extends TriggerDecor{
    @Override
    public String toString() {
        return "BombRangeInc";
    }

    public void trigger(Player player, World world) { //n'influe pas sur les bombes déjà posées
        player.setBombRange(player.getBombRange()+1);
        world.clear(player.getPosition());
    }
}
