package fr.ubx.poo.model.go.character;

import fr.ubx.poo.game.Direction;
import fr.ubx.poo.game.Game;
import fr.ubx.poo.game.Position;
import fr.ubx.poo.game.World;
import fr.ubx.poo.game.damage.PlayerDamage;
import fr.ubx.poo.model.Movable;
import fr.ubx.poo.model.decor.Decor;
import fr.ubx.poo.model.go.GameObject;

import java.util.ArrayList;

public abstract class Alive extends GameObject implements Movable {
    private boolean alive = true;
    Direction direction;
    private int lives;


    public Alive(Game game, Position position, int lives) {
        super(game, position);
        this.direction = Direction.S;
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean canMove(Direction direction) {
        boolean canMove;

        Position nextPos = direction.nextPosition(getPosition());

        canMove = nextPos.inside(game.getWorld().dimension);

        Decor decor = game.getWorld().get(nextPos);
        if (decor != null) {
            if(this.isPlayer())
                canMove = decor.canWalkOn();
            else
                canMove = decor.nonPlayerCanWalkOn();

        }


        return canMove;
    }

    public void doMove(Direction direction) {

        Position nextPos = direction.nextPosition(getPosition());
        setPosition(nextPos);

        moveConsequence();
    }

    private void moveConsequence(){

        World world = game.getWorld();
        if (this.isPlayer()) {

            Decor decor = world.get(this.getPosition());
            if (decor != null) {
                Player player = (Player) this;
                decor.trigger(player, world);
            }

            ArrayList<Monster> monsters = game.getMonster();
            for (Monster monster : monsters) {
                if (this.getPosition().equals(monster.getPosition()))
                    walkOnMonster();
            }
        }

        if(this.isMonster()) {
            Player player = game.getPlayer();
            if (this.getPosition().equals(player.getPosition()))
                walkOnPlayer(player);
        }
    }

    private void walkOnMonster(){
        Player player = (Player) this;
        PlayerDamage damage = new PlayerDamage();
        damage.take(player);
    }

    private void walkOnPlayer(Player player){
        PlayerDamage damage = new PlayerDamage();
        damage.take(player);
    }

    public abstract void update(long now);

    public void die(){
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isPlayer() {return false;}
    public boolean isMonster() {return false;}
}
