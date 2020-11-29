package fr.ubx.poo.game;

public class BombTimer {

    private long time;
    private long lastUpdate;
    private boolean finish;

    public BombTimer(long start, long time){
        this.lastUpdate = start;
        this.time = time;
        this.finish = false;
    }

    public boolean isFinish() {
        return finish;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void update(long now){

        if (now - lastUpdate >= time) {
            finish = true;
            lastUpdate = now;
        }


    }
}
