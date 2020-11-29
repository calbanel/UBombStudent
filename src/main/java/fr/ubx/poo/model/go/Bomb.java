package fr.ubx.poo.model.go;

import fr.ubx.poo.game.Game;
import fr.ubx.poo.game.Timer;
import fr.ubx.poo.game.Position;
import fr.ubx.poo.game.World;

public class Bomb extends GameObject {

    private Timer timer;
    private boolean explode;
    private World world;
    private int state;

    private long STATE1 = 1000000000L;
    private long STATE2 = 2000000000L;
    private long STATE3 = 3000000000L;

    public Bomb(Game game, Position position, long now) {
        super(game, position);
        this.timer = new Timer(now,4000000000L);
        this.explode = false;
        this.world = this.game.getWorld();
        this.state = 0;
    }

    public void update(long now){
        timer.update(now);
        if (timer.getProgress() >= STATE1 && timer.getProgress() < STATE2){
            state = 1;
        }
        if (timer.getProgress() >= STATE2 && timer.getProgress() < STATE3){
            state = 2;
        }
        if (timer.getProgress() >= STATE3){
            state = 3;
        }
        if(timer.isFinish()) {
            explosion();
        }
    }

    public int getState(){
        return state;
    }

    public void explosion(){
        explode = true;
    }

    public boolean isExplode(){
        return explode;
    }

}
