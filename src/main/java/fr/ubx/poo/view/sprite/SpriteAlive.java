package fr.ubx.poo.view.sprite;

import fr.ubx.poo.model.go.character.Alive;
import fr.ubx.poo.model.go.character.Player;
import javafx.scene.layout.Pane;

public abstract class SpriteAlive extends SpriteGameObject{
    public SpriteAlive(Pane layer, Alive alive) {
        super(layer, null, alive);
    }

    @Override
    public abstract void updateImage();
}
