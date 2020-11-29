package fr.ubx.poo.game.damage;

import fr.ubx.poo.model.go.character.Alive;

public class DamageOnMonster extends Damage{
    public void take(Alive alive) {
        alive.setLives(alive.getLives() - 1);
        statusAfterDamage(alive);
    }

    protected void statusAfterDamage(Alive alive) {
        if (isDie(alive)) {
            alive.die();
        }
    }
}
