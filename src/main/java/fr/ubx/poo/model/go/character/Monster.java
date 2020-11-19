package fr.ubx.poo.model.go.character;

import fr.ubx.poo.game.Direction;
import fr.ubx.poo.game.Game;
import fr.ubx.poo.game.Position;
import fr.ubx.poo.game.damage.PlayerDamage;

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

    public void walkOnMonster(){

    }

    public void walkOnPlayer(Player player){
        PlayerDamage damage = new PlayerDamage();
        damage.take(player);
    }


}
