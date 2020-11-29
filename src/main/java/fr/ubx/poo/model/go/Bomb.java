package fr.ubx.poo.model.go;

import fr.ubx.poo.game.*;
import fr.ubx.poo.game.damage.Damage;
import fr.ubx.poo.game.damage.DamageOnMonster;
import fr.ubx.poo.game.damage.DamageOnPlayer;
import fr.ubx.poo.model.decor.Decor;
import fr.ubx.poo.model.go.character.Alive;
import fr.ubx.poo.model.go.character.Player;

public class Bomb extends GameObject {

    private Timer timer;
    private boolean explode;
    private final World world = game.getWorld();
    private int range;
    private int state;

    private final long STATE1 = 1000000000L;
    private final long STATE2 = 2000000000L;
    private final long STATE3 = 3000000000L;

    public Bomb(Game game, Position position, long now, int range) {
        super(game, position);
        this.timer = new Timer(now,4000000000L);
        this.explode = false;
        this.state = 0;
        this.range = range;
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
        if(timer.isFinish() && explode == false) {
            explosion();
        }
    }

    public int getState(){
        return state;
    }

    public void explosion(){
        explode = true;
        Position nextPos;
        Decor decor;
        GameObject go;
        Damage damage;
        boolean obstacle;

        for(Direction direction : Direction.values()){
            nextPos = getPosition();
            obstacle = false;

            for (int r = 1; r <= range; r++) {
                if (!obstacle) {
                    nextPos = direction.nextPosition(nextPos);

                    decor = world.get(nextPos);
                    if (decor != null) {
                        decor.hitByBomb(world, nextPos);
                        if (!decor.canWalkOn()) {
                            obstacle = true;
                        }
                    }

                    go = game.getGameObjectAtPos(nextPos);
                    if(go != null)
                    {
                        if(go.isBomb()){
                            Bomb bomb = (Bomb) go;
                            if(!bomb.isExplode())
                                bomb.explosion();
                        }

                        Alive alive = (Alive) go;
                        if(go.isPlayer()){
                            damage = new DamageOnPlayer();
                            damage.take(alive);
                        }

                        if(go.isMonster()){
                            damage = new DamageOnMonster();
                            damage.take(alive);
                        }
                    }



                }
            }

        }

        game.getPlayer().setBombBag(game.getPlayer().getBombBag()+1);
    }

    public boolean isExplode(){
        return explode;
    }

    public boolean isBomb() {return true;}

}
