package fr.ubx.poo.model.decor.triggerdecor;

import fr.ubx.poo.game.gridevent.GridEvent;
import fr.ubx.poo.game.gridevent.MonsterGridEvent;
import fr.ubx.poo.model.go.character.Player;

public class Monster extends TriggerDecor{
    @Override
    public String toString() {
        return "Monster";
    }

    public void trigger(Player player) {
        GridEvent gridEvent = new MonsterGridEvent();
        gridEvent.trigger(player);
    }
}
