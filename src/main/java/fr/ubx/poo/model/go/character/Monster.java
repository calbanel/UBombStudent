package fr.ubx.poo.model.go.character;

import fr.ubx.poo.game.Game;
import fr.ubx.poo.game.Position;
import fr.ubx.poo.game.gridevent.GridEvent;
import fr.ubx.poo.game.gridevent.MonsterGridEvent;
import fr.ubx.poo.model.Entity;
import fr.ubx.poo.model.decor.triggerdecor.TriggerDecor;
import fr.ubx.poo.model.go.character.Player;

public class Monster extends Alive {
    @Override
    public String toString() {
        return "Monster";
    }

    public Monster(Game game, Position position) {
        super(game, position, 1);
    }

    public void update(long now){
        System.out.println("Test");
    }
}
