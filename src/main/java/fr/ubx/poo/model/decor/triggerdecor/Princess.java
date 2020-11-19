package fr.ubx.poo.model.decor.triggerdecor;


import fr.ubx.poo.model.go.character.Alive;
import fr.ubx.poo.model.go.character.Player;

public class Princess extends TriggerDecor{
    @Override
    public String toString() {
        return "Princess";
    }

    @Override
    public void trigger(Alive alive) {
        if (alive.isPlayer()){
            Player player = (Player) alive;
            player.win();
        }
    }
}
