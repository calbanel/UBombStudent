package fr.ubx.poo.game;

public class Timer {

    private final long start;
    private final long time;
    private long progress;
    private boolean finish;
    private boolean launch;

    public Timer(long start, long time){
        this.start = start;
        this.progress = start;
        this.time = time;
        this.finish = false;
        this.launch = true;
    }

    public boolean isFinish() {
        return finish;
    }

    public long getProgress() {
        return progress;
    }

    public void update(long now){
        if (launch) {
            if (now - start >= time) {
                finish = true;
                progress = now;
                launch = false;
            }
            progress = now - start;
        }


    }
}
