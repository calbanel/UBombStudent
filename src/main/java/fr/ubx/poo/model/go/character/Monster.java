package fr.ubx.poo.model.go.character;

import fr.ubx.poo.game.*;
import fr.ubx.poo.model.decor.Decor;

public class Monster extends Alive {

    private long lastUpdate = 0;
    private final long speed;

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

        Direction dir;

        if(!stuck()) {
            if (now - lastUpdate >= speed) { // 1.5 seconds

                dir = randomDirection();
                if (currentLevel == game.getPlayer().getCurrentLevel() && !(game.getPlayer().getCurrentLevel() <= Math.ceil((double) game.getNbWorlds() / 2))) {
                    dir = focusPlayer();
                }

                if(dir != null) {
                    this.direction = dir;
                    doMove(dir);
                }
                lastUpdate = now;
            }
        }

    }

    private Direction randomDirection(){
        Direction dir = null;
        while (!stuck()) {
            dir = Direction.random();
            if (canMove(dir)) {
                break;
            }
        }
        return dir;
    }

    private Direction focusPlayer(){
        Direction dir = null;
        Position A = getPosition();
        Position B = game.getPlayer().getPosition();
        int[] vector = new int[2];
        vector[0] = B.x - A.x;
        vector[1] = B.y - A.y;

        //System.out.println(vector[0] + " ; " + vector[1]);

        if(vector[0] < 0){
            if (canMove(Direction.W))
                dir = Direction.W;
            else{
                dir = northOrSud(vector);
            }
        }
        if(vector[0] > 0){
            if (canMove(Direction.E))
                dir = Direction.E;
            else{
                dir = northOrSud(vector);
            }
        }

        if(vector[0] == 0){
            dir = northOrSud(vector);
        }

        if(dir == null){
            dir = randomDirection();
        }

        return dir;
    }

    private Direction northOrSud(int[] vector){
        Direction dir = null;
        if(vector[1] < 0){
            if(canMove(Direction.N))
                dir = Direction.N;
        }
        if(vector[1] > 0){
            if(canMove(Direction.S))
                dir = Direction.S;
        }
        return dir;
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
