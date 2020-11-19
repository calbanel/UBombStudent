package fr.ubx.poo.game.gridevent;

import fr.ubx.poo.game.Position;
import fr.ubx.poo.game.damage.PlayerDamage;
import fr.ubx.poo.model.Entity;
import fr.ubx.poo.model.go.GameObject;
import fr.ubx.poo.model.go.character.Player;


public class MonsterGridEvent extends GridEvent{

    public void trigger(Player player, Position position){
        PlayerDamage damage = new PlayerDamage();
        damage.take(player);
    }

}
