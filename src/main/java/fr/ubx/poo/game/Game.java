/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.game;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import fr.ubx.poo.model.go.Bomb;
import fr.ubx.poo.model.go.GameObject;
import fr.ubx.poo.model.go.character.Monster;
import fr.ubx.poo.model.go.character.Player;

public class Game {

    private final ArrayList<World> worlds = new ArrayList<>();
    private final Player player;
    private final ArrayList<Monster> monsters = new ArrayList<>();
    private final String worldPath;
    public int initPlayerLives;
    private int nbWorlds;
    private static String prefix;
    private World currentWorld;


    public Game(String worldPath) {
        this.worldPath = worldPath;
        loadConfig(worldPath);

        World world;
        ArrayList<Position> positionMonsters;
        for(int i = 1; i <= nbWorlds; i++){
            worlds.add(new WorldFromFile(worldPath + "/" + prefix + i + ".txt"));
            world = worlds.get(i-1);
            positionMonsters = world.findMonsters();
            for (Position pos : positionMonsters) {
                monsters.add(new Monster(this, pos,i));
            }
        }
        currentWorld = worlds.get(0);

        Position positionPlayer;
        try {
            positionPlayer = currentWorld.findPlayer();
            player = new Player(this, positionPlayer, 1);
        } catch (PositionNotFoundException e) {
            System.err.println("Position not found : " + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    public int getInitPlayerLives() {
        return initPlayerLives;
    }

    private void loadConfig(String path) {
        try (InputStream input = new FileInputStream(new File(path, "config.properties"))) {
            Properties prop = new Properties();
            // load the configuration file
            prop.load(input);
            initPlayerLives = Integer.parseInt(prop.getProperty("lives", "3"));
            nbWorlds = Integer.parseInt(prop.getProperty("levels", "3"));
            prefix = prop.getProperty("prefix", "level");
        } catch (IOException ex) {
            System.err.println("Error loading configuration");
        }
    }

    public ArrayList<World> getWorlds() {
        return worlds;
    }

    public World getCurrentWorld() {
        return currentWorld;
    }

    public Player getPlayer() {
        return this.player;
    }

    public ArrayList<Monster> getCurrentWorldMonsters() {
        ArrayList<Monster> inCurrentWorld = new ArrayList<>();
        monsters.stream().filter(m -> m.getCurrentWorld().equals(currentWorld)).forEach(inCurrentWorld::add);
        return inCurrentWorld;
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public ArrayList<GameObject> getGameObjectAtPos(Position pos, World world){
         ArrayList<GameObject> go = new ArrayList<>();
        if(player.getCurrentWorld().equals(world) && player.getPosition().equals(pos))
            go.add(player);

        monsters.stream().filter(m -> m.getCurrentWorld().equals(world) && m.getPosition().equals(pos)).forEach(m->go.add(m));

        player.getCurrentWorldBombs().stream().filter(b -> b.getCurrentWorld().equals(world) && b.getPosition().equals(pos)).forEach(b->go.add(b));

        return go;
    }

    public void playerChangeLevel(int level,String door) {
        this.currentWorld = worlds.get(level-1);
        try {
            player.setPosition(currentWorld.findDoorOpened(door));
        } catch (PositionNotFoundException e) {
            System.err.println("Position not found : " + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
        player.setLevelChangement(true);
    }

    public int getNbWorlds() {
        return nbWorlds;
    }
}
