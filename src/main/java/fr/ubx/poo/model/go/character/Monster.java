package fr.ubx.poo.model.go.character;

import fr.ubx.poo.game.Direction;
import fr.ubx.poo.game.Game;
import fr.ubx.poo.game.Position;
import fr.ubx.poo.model.decor.Decor;

public class Monster extends Alive {

    private long lastUpdate = 0;
    private long speed;

    @Override
    public String toString() {
        return "Monster";
    }

    public Monster(Game game, Position position, int currentLevel) {
        super(game, position, currentLevel, 1);
        speed = speedCalcul(currentLevel);
    }

    //for each level the time during each move of the monsters decrease of 15% -> (seconds) 1.5 | 1.27 | 1.08 | 0.92 | 0.78 | ...
    private static long speedCalcul(int level){
        long speed = 1500000000L;
        for(int i = 1; i < level; i++){
            speed = (long) (speed * 0.85);
        }
        return speed;
    }

    public void update(long now){
        boolean stuck = stuck();

        if(!stuck) {
            Direction random = Direction.random();
            if (now - lastUpdate >= speed) { // 1.5 seconds
                while (!canMove(random))
                    random = Direction.random();

                this.direction = random;
                doMove(random);
                lastUpdate = now;
            }
        }

    }

    private boolean stuck(){
        boolean stuck = true;
        Decor decor;
        for(Direction dir : Direction.values()){
            decor = getCurrentWorld().get(dir.nextPosition(getPosition()));
            if(dir.nextPosition(getPosition()).inside(getCurrentWorld().dimension)) {
                if (decor == null || decor.nonPlayerCanWalkOn()) {
                    stuck = false;
                }
            }
        }
        return stuck;
    }

    protected void moveConsequence() {
        Player player = game.getPlayer();
        if (player.getCurrentWorld().equals(getCurrentWorld())){
            if (this.getPosition().equals(player.getPosition()))
                walkOnPlayer(player);
        }

    }

    @Override
    protected boolean canMoveOnDecor(Decor decor) {
        return decor.nonPlayerCanWalkOn();
    }

    private void walkOnPlayer(Player player){
        player.takeDamage();
    }

    public boolean isMonster() {return true;}

    public void takeDamage() {
        this.setLives(this.getLives() - 1);
        statusAfterDamage();
    }

    protected void statusAfterDamage() {
        if (isDie()) {
            this.die();
        }
    }


}
