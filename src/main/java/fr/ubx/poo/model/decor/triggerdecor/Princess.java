package fr.ubx.poo.model.decor.triggerdecor;


import fr.ubx.poo.game.gridevent.GridEvent;
import fr.ubx.poo.game.gridevent.PrincessGridEvent;
import fr.ubx.poo.model.go.character.Player;

public class Princess extends TriggerDecor{
    @Override
    public String toString() {
        return "Princess";
    }

    @Override
    public void trigger(Player player) {
        GridEvent gridEvent = new PrincessGridEvent();
        gridEvent.trigger(player);
    }
}
