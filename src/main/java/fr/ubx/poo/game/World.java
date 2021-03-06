/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.game;

import fr.ubx.poo.model.decor.Decor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.function.BiConsumer;

public class World {
    private final Map<Position, Decor> grid;
    private final WorldEntity[][] raw;
    public final Dimension dimension;
    private boolean hasChange;

    public World(WorldEntity[][] raw) {
        this.raw = raw;
        dimension = new Dimension(raw.length, raw[0].length);
        grid = WorldBuilder.build(raw, dimension);
        hasChange = true;
    }

    public Position findPlayer() throws PositionNotFoundException {
        for (int x = 0; x < dimension.width; x++) {
            for (int y = 0; y < dimension.height; y++) {
                if (raw[y][x] == WorldEntity.Player) {
                    return new Position(x, y);
                }
            }
        }
        throw new PositionNotFoundException("Player");
    }

    public Position findDoorOpened(String door) throws PositionNotFoundException {
        if(door == "next") {
            for (int x = 0; x < dimension.width; x++) {
                for (int y = 0; y < dimension.height; y++) {
                    if (raw[y][x] == WorldEntity.DoorPrevOpened) {
                        return new Position(x, y);
                    }
                }
            }
        }
        if(door == "prev"){
            for (int x = 0; x < dimension.width; x++) {
                for (int y = 0; y < dimension.height; y++) {
                    if (raw[y][x] == WorldEntity.DoorNextClosed) {
                        return new Position(x, y);
                    }
                }
            }
        }
        throw new PositionNotFoundException(door);
    }

    public ArrayList<Position> findMonsters() {
        ArrayList<Position> positions = new ArrayList<>();
        for (int x = 0; x < dimension.width; x++) {
            for (int y = 0; y < dimension.height; y++) {
                if (raw[y][x] == WorldEntity.Monster) {
                    positions.add(new Position(x,y));
                }
            }
        }
        return positions;
    }

    public Decor get(Position position) {
        return grid.get(position);
    }

    public void set(Position position, Decor decor) {
        grid.put(position, decor);
        hasChange = true;
    }

    public void clear(Position position) {
        grid.remove(position);
        hasChange = true;
    }

    public void forEach(BiConsumer<Position, Decor> fn) {
        grid.forEach(fn);
    }

    public Collection<Decor> values() {
        return grid.values();
    }

    public boolean isInside(Position position) {
        return position.inside(dimension);
    }

    public boolean isEmpty(Position position) {
        return grid.get(position) == null;
    }

    public void changeDone(){
        hasChange = false;
    }

    public boolean hasChanged(){
        return hasChange;
    }
}
