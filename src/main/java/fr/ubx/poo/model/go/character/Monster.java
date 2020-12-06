package fr.ubx.poo.model.go.character;

import fr.ubx.poo.game.Direction;
import fr.ubx.poo.game.Game;
import fr.ubx.poo.game.Position;
import fr.ubx.poo.game.World;
import fr.ubx.poo.game.damage.DamageOnPlayer;
import fr.ubx.poo.model.decor.Decor;

public class Monster extends Alive {

    private long lastUpdate = 0;

    @Override
    public String toString() {
        return "Monster";
    }

    public Monster(Game game, Position position, World currentWorld) {
        super(game, position, currentWorld, 1);
    }

    public void update(long now){
        boolean stuck = stuck();

        if(!stuck) {
            Direction random = direction.random();
            if (now - lastUpdate >= 1500000000L) { // 1.5 seconds
                while (!canMove(random))
                    random = direction.random();

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
            decor = game.getCurrentWorld().get(dir.nextPosition(getPosition()));
            if(decor == null || decor.nonPlayerCanWalkOn()){
                stuck = false;
            }
        }
        return stuck;
    }

    protected void moveConsequence(){

            Player player = game.getPlayer();
            if (this.getPosition().equals(player.getPosition()))
                walkOnPlayer(player);

    }

    @Override
    protected boolean canMoveOnDecor(Decor decor) {
        return decor.nonPlayerCanWalkOn();
    }

    private void walkOnPlayer(Player player){
        DamageOnPlayer damage = new DamageOnPlayer();
        damage.take(player);
    }

    public boolean isMonster() {return true;}


}
