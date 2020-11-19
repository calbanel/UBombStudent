package fr.ubx.poo.model.go.character;

import fr.ubx.poo.game.Direction;
import fr.ubx.poo.game.Game;
import fr.ubx.poo.game.Position;
import fr.ubx.poo.game.World;
import fr.ubx.poo.game.damage.PlayerDamage;
import fr.ubx.poo.model.decor.Decor;

import java.util.ArrayList;

public class Monster extends Alive {

    private long lastUpdate = 0;

    @Override
    public String toString() {
        return "Monster";
    }

    public Monster(Game game, Position position) {
        super(game, position, 1);
    }

    public void update(long now){
        Direction random = direction.random();
        if(now - lastUpdate >= 1000000000){
            while(!canMove(random))
                random = direction.random();

            this.direction = random;
            doMove(random);
            lastUpdate = now;
        }

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
        PlayerDamage damage = new PlayerDamage();
        damage.take(player);
    }

    public boolean isMonster() {return true;}


}
