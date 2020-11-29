package fr.ubx.poo.game;

public class BombTimer {

    private long start;
    private long time;
    private long progress;
    private boolean finish;

    public BombTimer(long start, long time){
        this.start = start;
        this.progress = start;
        this.time = time;
        this.finish = false;
    }

    public boolean isFinish() {
        return finish;
    }

    public long getProgress() {
        return progress;
    }

    public void update(long now){

        if (now - start >= time) {
            finish = true;
            progress = now;
        }
        progress = now - start;


    }
}
