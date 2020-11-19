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
        if (decor != null)
            canMove = decor.canWalkOn();

        return canMove;
    }

    public void doMove(Direction direction) {

        Position nextPos = direction.nextPosition(getPosition());
        setPosition(nextPos);

        World world = game.getWorld();

        Decor decor = world.get(nextPos);
        if(decor != null)
            decor.trigger(this, world);

        ArrayList<Monster> monsters = game.getMonster();
        for (Monster monster : monsters) {
            if (nextPos.equals(monster.getPosition()))
                walkOnMonster();
        }

        Player player = game.getPlayer();
        if(nextPos.equals(player.getPosition()))
            walkOnPlayer(player);

    }

    public abstract void walkOnMonster();

    public abstract void walkOnPlayer(Player player);

    public abstract void update(long now);

    public void die(){
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isPlayer() {return false;}
}
