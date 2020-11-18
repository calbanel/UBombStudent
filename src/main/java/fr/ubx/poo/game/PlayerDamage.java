package fr.ubx.poo.game;

import fr.ubx.poo.model.go.character.Player;

public class PlayerDamage extends Damage{
    public boolean take(Player player){
        player.setLives(player.getLives()-1);
        return isDie(player); //retourne true si le joueur est mort, false si il est en vie
    }

    private boolean isDie(Player player){
        boolean isDie = false;
        if(player.getLives() == 0){
            isDie = true;
        }
        return isDie;
    }
}
