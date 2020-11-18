package fr.ubx.poo.game.damage;

import fr.ubx.poo.model.go.character.Player;

public class PlayerDamage extends Damage{
    public void take(Player player) {
        player.setLives(player.getLives() - 1);
        isDie(player);
    }

    private void isDie(Player player){
        if(player.getLives() == 0){
            player.die();
        }
    }
}
