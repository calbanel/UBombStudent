package fr.ubx.poo.game;

import fr.ubx.poo.model.decor.*;
import fr.ubx.poo.model.decor.obstructdecor.Box;
import fr.ubx.poo.model.decor.obstructdecor.DoorNextClosed;
import fr.ubx.poo.model.decor.obstructdecor.Stone;
import fr.ubx.poo.model.decor.obstructdecor.Tree;
import fr.ubx.poo.model.decor.triggerdecor.*;

import java.util.Hashtable;
import java.util.Map;

public class WorldBuilder {
    private final Map<Position, Decor> grid = new Hashtable<>();

    private WorldBuilder() {
    }

    public static Map<Position, Decor> build(WorldEntity[][] raw, Dimension dimension) {
        WorldBuilder builder = new WorldBuilder();
        for (int x = 0; x < dimension.width; x++) {
            for (int y = 0; y < dimension.height; y++) {
                Position pos = new Position(x, y);
                Decor decor = processEntity(raw[y][x]);
                if (decor != null)
                    builder.grid.put(pos, decor);
            }
        }
        return builder.grid;
    }

    private static Decor processEntity(WorldEntity entity) {
        switch (entity) {
            case Stone:
                return new Stone();
            case Tree:
                return new Tree();
            case Box:
                return new Box();
            case Heart:
                return new Heart();
            case Key:
                return new Key();
            case DoorPrevOpened:
                return new DoorPrevOpened();
            case DoorNextOpened:
                return new DoorNextOpened();
            case DoorNextClosed:
                return new DoorNextClosed();
            case Princess:
                return new Princess();
            case BombRangeInc:
                return new BombRangeInc();
            case BombRangeDec:
                return new BombRangeDec();
            case BombNumberInc:
                return new BombNumberInc();
            case BombNumberDec:
                return new BombNumberDec();
            default:
                return null;
        }
    }
}
