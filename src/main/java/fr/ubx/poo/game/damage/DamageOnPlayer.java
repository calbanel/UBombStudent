package fr.ubx.poo.game.damage;

import fr.ubx.poo.model.go.character.Alive;
import fr.ubx.poo.model.go.character.Player;

public class DamageOnPlayer extends Damage{
    public void take(Alive alive) {
        Player player = (Player) alive;
        if (!player.isInvincible()){
            player.setLives(player.getLives() - 1);
            statusAfterDamage(player);
        }
    }

    protected void statusAfterDamage(Alive alive){
        Player player = (Player) alive;
        if(!isDie(player)){
            player.setInvincibility(true);
        }
        else
            player.die();
    }

}
