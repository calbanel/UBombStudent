package fr.ubx.poo.view.sprite;


import fr.ubx.poo.model.go.character.Monster;
import fr.ubx.poo.model.go.character.Player;
import fr.ubx.poo.view.image.ImageFactory;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.Pane;

public class SpriteMonster extends SpriteAlive {

    public SpriteMonster(Pane layer, Monster monster) {
        super(layer, monster);
        updateImage();
    }

    @Override
    public void updateImage() {
        Monster monster = (Monster) go;
        setImage(ImageFactory.getInstance().getMonster(monster.getDirection()));
    }
}
