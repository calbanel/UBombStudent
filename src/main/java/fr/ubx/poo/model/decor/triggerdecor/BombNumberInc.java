package fr.ubx.poo.model.decor.triggerdecor;
import fr.ubx.poo.game.World;
import fr.ubx.poo.model.decor.Decor;
import fr.ubx.poo.model.go.character.Alive;
import fr.ubx.poo.model.go.character.Player;
import fr.ubx.poo.view.sprite.Sprite;


public class BombNumberInc extends TriggerDecor{
    @Override
    public String toString() {
        return "BombNumberInc";
    }

    @Override
    public void trigger(Alive alive, World world) {
        if (alive.isPlayer()){
            Player player = (Player) alive;
            player.setBombNb(player.getBombNb()+1);
            System.out.println(player.getPosition());
            world.clear(player.getPosition());

        }
    }
}
