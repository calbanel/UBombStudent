package fr.ubx.poo.game.gridevent;

import fr.ubx.poo.model.go.character.Player;

public abstract class GridEvent {
    public abstract void trigger(Player player);
}
