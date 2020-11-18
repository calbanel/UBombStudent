package fr.ubx.poo.game;

import fr.ubx.poo.model.go.character.Player;

public class PlayerDamage extends Damage{
    public void take(Player player){
        player.setLives(player.getLives()-1);
    }
}
