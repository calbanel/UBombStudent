package fr.ubx.poo.model.decor.triggerdecor;

import fr.ubx.poo.game.World;
import fr.ubx.poo.model.decor.Decor;
import fr.ubx.poo.model.go.character.Alive;


public abstract class TriggerDecor extends Decor {
    public abstract void trigger(Alive alive, World world);
}
