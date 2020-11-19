package fr.ubx.poo.game.gridevent;
import fr.ubx.poo.model.go.character.Player;

public class BombNumberIncGridEvent extends GridEvent{
    public void trigger(Player player){
        player.setBombNb(player.getBombNb()+1);
    }
}
