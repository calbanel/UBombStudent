package fr.ubx.poo.model.decor.triggerdecor;
import fr.ubx.poo.model.go.character.Alive;
import fr.ubx.poo.model.go.character.Player;


public class BombNumberInc extends TriggerDecor{
    @Override
    public String toString() {
        return "BombNumberInc";
    }

    @Override
    public void trigger(Alive alive) {
        if (alive.isPlayer()){
            Player player = (Player) alive;
            player.setBombNb(player.getBombNb()+1);
        }
    }
}
