package fr.ubx.poo.game.damage;

import fr.ubx.poo.model.go.character.Alive;

public abstract class Damage {

    public abstract void take(Alive alive);

    protected boolean isDie(Alive alive){
        return alive.getLives() == 0;
    }

    protected abstract void statusAfterDamage(Alive alive);

}
