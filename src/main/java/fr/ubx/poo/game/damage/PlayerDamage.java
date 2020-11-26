package fr.ubx.poo.game.damage;

import fr.ubx.poo.model.go.character.Player;

public class PlayerDamage extends Damage{
    public void take(Player player) {
        if (!player.isInvincible()){
            player.setLives(player.getLives() - 1);
            statusAfterDamage(player);
        }
    }

    private boolean isDie(Player player){
        boolean isDie;
        if(player.getLives() == 0){
            isDie = true;
        }
        else
            isDie = false;

        return isDie;
    }

    private void statusAfterDamage(Player player){
        if(!isDie(player)){
            player.setInvincibility(true);
        }
        else
            player.die();
    }

}
