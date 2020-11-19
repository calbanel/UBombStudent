package fr.ubx.poo.model.decor.triggerdecor;
import fr.ubx.poo.game.Position;
import fr.ubx.poo.game.gridevent.BombNumberIncGridEvent;
import fr.ubx.poo.game.gridevent.GridEvent;
import fr.ubx.poo.model.Entity;
import fr.ubx.poo.model.go.character.Player;

public class BombNumberInc extends TriggerDecor{
    @Override
    public String toString() {
        return "BombNumberInc";
    }

    @Override
    public void trigger(Player player, Position position) {
        BombNumberIncGridEvent gridEvent = new BombNumberIncGridEvent();
        gridEvent.trigger(player, position);
    }
}
