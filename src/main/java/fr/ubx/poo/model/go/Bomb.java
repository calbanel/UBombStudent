package fr.ubx.poo.model.go;

import fr.ubx.poo.game.*;
import fr.ubx.poo.model.decor.Decor;
import fr.ubx.poo.model.decor.Explosion;
import fr.ubx.poo.model.go.character.Alive;

import java.util.ArrayList;

public class Bomb extends GameObject {

    private final Timer timer;
    private boolean explode;
    private final int range;
    private int state;

    private final static long STATE1 = 1000000000L; // 1 sec
    private final static long STATE2 = 2000000000L; // 2 sec
    private final static long STATE3 = 3000000000L; // 3 sec

    private Timer clearTimer;
    private boolean cleared;

    public Bomb(Game game, Position position, int currentLevel, long now, int range) {
        super(game, position, currentLevel);
        this.timer = new Timer(now,4000000000L); // 4 sec
        this.explode = false;
        this.state = 0;
        this.range = range;
        this.clearTimer = null;
        this.cleared = false;
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

        if(timer.isFinish() && !explode) {
            explosion();
        }

        if(explode && clearTimer == null){
            clearTimer = new Timer(now, 90000000L); // 90 msec
        }

        if(clearTimer != null && !cleared) {
            clearTimer.update(now);
            if (clearTimer.isFinish()) {
                clearExplosion();
                cleared = true;
            }
        }

    }

    public int getState(){
        return state;
    }

    public void explosion(){

        explode = true;

        Position nextPos;
        boolean obstacle;

        explosionDamage(getPosition());

        for(Direction direction : Direction.values()){
            nextPos = getPosition();
            obstacle = false;

            for (int r = 1; r <= range; r++) {

                if (!obstacle) {

                    nextPos = direction.nextPosition(nextPos);
                    obstacle = explosionDamage(nextPos);
                }

            }

        }
        game.getPlayer().setBombBag(game.getPlayer().getBombBag()+1);
    }

    private boolean explosionDamage(Position pos){ //return true if there is an obstacle
        boolean obstacle = false;

        if(!getCurrentWorld().isInside(pos))
            obstacle = true;

        Decor decor = getCurrentWorld().get(pos);
        if (decor != null)
            obstacle = decorTouch(decor, pos);

        ArrayList<GameObject> go = game.getGameObjectAtPos(pos, getCurrentWorld());
        if(!go.isEmpty()) {
            go.forEach(this::gameObjectTouch);
        }

        if(!obstacle && getCurrentWorld().isEmpty(pos))
            getCurrentWorld().set(pos, new Explosion());

        return obstacle;
    }

    private void clearExplosion(){
        Position nextPos;
        Decor decor;

        getCurrentWorld().clear(getPosition());

        for(Direction direction : Direction.values()){
            nextPos = getPosition();

            for (int r = 1; r <= range; r++) {

                nextPos = direction.nextPosition(nextPos);
                decor = getCurrentWorld().get(nextPos);

                if (decor != null && decor.isExplosion())
                    getCurrentWorld().clear(nextPos);
            }

        }
    }

    private void gameObjectTouch(GameObject go){

        if(go.isBomb()){
            Bomb bomb = (Bomb) go;
            if(!bomb.isExplode())
                bomb.explosion();
        }
        else {
            Alive alive = (Alive) go;
            alive.takeDamage();
        }
    }

    private boolean decorTouch(Decor decor, Position nextPos){
        decor.hitByBomb(getCurrentWorld(), nextPos);

        return !decor.canWalkOn() || decor.isOpenedDoor();
    }

    public boolean isExplode(){
        return explode;
    }

    public boolean isCleared(){
        return cleared;
    }

    public boolean isBomb() {return true;}

}
