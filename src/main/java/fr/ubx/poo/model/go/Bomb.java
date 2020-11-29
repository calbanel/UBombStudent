package fr.ubx.poo.model.go;

import fr.ubx.poo.game.Game;
import fr.ubx.poo.game.BombTimer;
import fr.ubx.poo.game.Position;

public class Bomb extends GameObject {

    private BombTimer timer;
    private boolean explode;

    public Bomb(Game game, Position position, long now) {
        super(game, position);
        this.timer = new BombTimer(now,4000000000L);
        this.explode = false;
    }

    public void update(long now){
        timer.update(now);
        if(timer.isFinish()) {
            System.out.println("cc");
            explosion();
        }
    }

    public void explosion(){
        explode = true;
    }

    public boolean isExplode(){
        return explode;
    }

}
