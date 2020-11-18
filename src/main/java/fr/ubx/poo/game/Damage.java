package fr.ubx.poo.game;

import fr.ubx.poo.model.go.character.Player;

public class Damage {

    //classe qui gère la perte de vie et la frame d'invicibilité

    public void take(Player player){
        player.setLives(player.getLives()-1);
    }
}
