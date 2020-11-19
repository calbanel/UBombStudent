package fr.ubx.poo.game.gridevent;

import fr.ubx.poo.game.Position;
import fr.ubx.poo.model.go.character.Player;

public class PrincessGridEvent extends GridEvent{
    public void trigger(Player player, Position position){
            player.win();
    }
}
