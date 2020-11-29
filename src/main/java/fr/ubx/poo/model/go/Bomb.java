package fr.ubx.poo.model.go;

import fr.ubx.poo.game.Game;
import fr.ubx.poo.game.BombTimer;
import fr.ubx.poo.game.Position;

public class Bomb extends GameObject {

    BombTimer timer;

    public Bomb(Game game, Position position, long now) {
        super(game, position);
        this.timer = new BombTimer(now,4000000000L);
    }

}
