package fr.ubx.poo.model.decor.triggerdecor;

import fr.ubx.poo.model.decor.Decor;
import fr.ubx.poo.model.go.character.Player;

public abstract class TriggerDecor extends Decor {
    public abstract void trigger(Player player);
}