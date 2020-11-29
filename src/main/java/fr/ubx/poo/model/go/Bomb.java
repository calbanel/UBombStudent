package fr.ubx.poo.model.go;

import fr.ubx.poo.game.*;
import fr.ubx.poo.game.damage.Damage;
import fr.ubx.poo.game.damage.DamageOnMonster;
import fr.ubx.poo.game.damage.DamageOnPlayer;
import fr.ubx.poo.model.decor.Decor;
import fr.ubx.poo.model.decor.Explosion;
import fr.ubx.poo.model.go.character.Alive;

public class Bomb extends GameObject {

    private final Timer timer;
    private boolean explode;
    private final World world = game.getWorld();
    private final int range;
    private int state;

    private final static long STATE1 = 1000000000L;
    private final static long STATE2 = 2000000000L;
    private final static long STATE3 = 3000000000L;

    private Timer clearTimer;
    private boolean cleared;

    public Bomb(Game game, Position position, long now, int range) {
        super(game, position);
        this.timer = new Timer(now,4000000000L);
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
            clearTimer = new Timer(now, 300000000L);
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
        System.out.println("yo");
        explode = true;

        Position nextPos;
        Decor decor;
        GameObject go;
        boolean obstacle;

        for(Direction direction : Direction.values()){
            nextPos = getPosition();
            obstacle = false;

            for (int r = 1; r <= range; r++) {

                if (!obstacle) {

                    nextPos = direction.nextPosition(nextPos);

                    if(!world.isInside(nextPos))
                        obstacle = true;

                    decor = world.get(nextPos);
                    if (decor != null)
                        obstacle = decorTouch(decor,nextPos);
                        if(!obstacle)
                            world.set(nextPos, new Explosion());

                    go = game.getGameObjectAtPos(nextPos);
                    if(go != null)
                        gameObjectTouch(go);
                }

            }

        }
        game.getPlayer().setBombBag(game.getPlayer().getBombBag()+1);


    }

    private void clearExplosion(){
        Position nextPos;
        Decor decor;

        for(Direction direction : Direction.values()){
            nextPos = getPosition();

            for (int r = 1; r <= range; r++) {

                nextPos = direction.nextPosition(nextPos);
                decor = world.get(nextPos);
                if (decor instanceof Explosion)
                    world.clear(nextPos);
            }

        }
    }

    private void gameObjectTouch(GameObject go){
        Damage damage;
        if(go.isBomb()){
            Bomb bomb = (Bomb) go;
            if(!bomb.isExplode())
                bomb.explosion();
        }
        else {
            Alive alive = (Alive) go;
            if (go.isPlayer()) {
                damage = new DamageOnPlayer();
                damage.take(alive);
            }

            if (go.isMonster()) {
                damage = new DamageOnMonster();
                damage.take(alive);
            }
        }
    }

    private boolean decorTouch(Decor decor, Position nextPos){
        decor.hitByBomb(world, nextPos);
        return !decor.canWalkOn();
    }

    public boolean isExplode(){
        return explode;
    }

    public boolean isCleared(){
        return cleared;
    }

    public boolean isBomb() {return true;}

}
