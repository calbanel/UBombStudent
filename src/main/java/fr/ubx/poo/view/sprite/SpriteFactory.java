/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.view.sprite;

import static fr.ubx.poo.view.image.ImageResource.*;

import fr.ubx.poo.game.Position;
import fr.ubx.poo.model.decor.*;
import fr.ubx.poo.model.decor.obstructdecor.Box;
import fr.ubx.poo.model.decor.obstructdecor.DoorNextClosed;
import fr.ubx.poo.model.decor.obstructdecor.Stone;
import fr.ubx.poo.model.decor.obstructdecor.Tree;
import fr.ubx.poo.model.decor.triggerdecor.*;
import fr.ubx.poo.model.go.Bomb;
import fr.ubx.poo.model.go.GameObject;
import fr.ubx.poo.model.go.character.Monster;
import fr.ubx.poo.model.go.character.Player;
import fr.ubx.poo.view.image.ImageFactory;
import javafx.scene.layout.Pane;


public final class SpriteFactory {

    public static Sprite createDecor(Pane layer, Position position, Decor decor) {
        ImageFactory factory = ImageFactory.getInstance();
        if (decor instanceof Stone)
            return new SpriteDecor(layer, factory.get(STONE), position);
        if (decor instanceof Tree)
            return new SpriteDecor(layer, factory.get(TREE), position);
        if (decor instanceof Box)
            return new SpriteDecor(layer, factory.get(BOX), position);
        if (decor instanceof Heart)
            return new SpriteDecor(layer, factory.get(HEART), position);
        if (decor instanceof Key)
            return new SpriteDecor(layer, factory.get(KEY), position);
        if (decor instanceof DoorPrevOpened)
            return new SpriteDecor(layer, factory.get(DOOR_PREV_OPENED), position);
        if (decor instanceof DoorNextOpened)
            return new SpriteDecor(layer, factory.get(DOOR_NEXT_OPENED), position);
        if (decor instanceof DoorNextClosed)
            return new SpriteDecor(layer, factory.get(DOOR_NEXT_CLOSED), position);
        if (decor instanceof Princess)
            return new SpriteDecor(layer, factory.get(PRINCESS), position);
        if (decor instanceof BombRangeInc)
            return new SpriteDecor(layer, factory.get(BOMB_RANGE_INC), position);
        if (decor instanceof BombRangeDec)
            return new SpriteDecor(layer, factory.get(BOMB_RANGE_DEC), position);
        if (decor instanceof BombNumberInc)
            return new SpriteDecor(layer, factory.get(BOMB_NUMBER_INC), position);
        if (decor instanceof BombNumberDec)
            return new SpriteDecor(layer, factory.get(BOMB_NUMBER_DEC), position);
        if (decor instanceof Explosion)
            return new SpriteDecor(layer, factory.get(EXPLOSION), position);
        throw new RuntimeException("Unsupported sprite for decor " + decor);
    }

    public static SpriteGameObject createGO(Pane layer, GameObject go) {
        if (go instanceof Player) {
            Player player = (Player) go;
            return new SpritePlayer(layer, player);
        }
        if (go instanceof Monster){
            Monster monster = (Monster) go;
            return new SpriteMonster(layer, monster);
        }
        if (go instanceof Bomb){
            Bomb bomb = (Bomb) go;
            return new SpriteBomb(layer, bomb);
        }
        else
            return null; //error
    }
}
