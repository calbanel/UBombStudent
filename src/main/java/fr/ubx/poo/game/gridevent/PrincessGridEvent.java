package fr.ubx.poo.game.gridevent;

import fr.ubx.poo.game.damage.PlayerDamage;
import fr.ubx.poo.model.go.character.Player;

public class PrincessGridEvent extends GridEvent{
    public void trigger(Player player){
        player.win();
    }
}
