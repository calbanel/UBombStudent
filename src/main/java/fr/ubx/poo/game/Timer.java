package fr.ubx.poo.game;

public class Timer {

    private long start;
    private long time;
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

    public boolean isLaunch() {
        return launch;
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

    public void reset(long finish){
        this.start = finish;
        this.progress = finish;
        this.finish = false;
        this.launch = true;
    }
}
