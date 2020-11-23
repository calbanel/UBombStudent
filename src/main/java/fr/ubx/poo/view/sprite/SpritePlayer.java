/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.view.sprite;

import fr.ubx.poo.model.go.character.Player;
import fr.ubx.poo.view.image.ImageFactory;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.util.ArrayList;

public class SpritePlayer extends SpriteAlive {

    public SpritePlayer(Pane layer, Player player) {
        super(layer, player);
        updateImage();
    }

    @Override
    public void updateImage() {
        Player player = (Player) go;
        invincibilitySprite();
        setImage(ImageFactory.getInstance().getPlayer(player.getDirection()));
    }

    private static int passage = 1;
    public void invincibilitySprite(){
        Player player = (Player) go;
        if(player.isInvincible()) {
            effect = new ColorAdjust(0, -1, 0, 0);
        }
        else
            effect = new ColorAdjust();
    }
}
