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

    private final World world;
    private final Player player;
    private final ArrayList<Monster> monsters = new ArrayList<>();
    private final String worldPath;
    public int initPlayerLives;

    public Game(String worldPath) {
        world = new WorldStatic();
        this.worldPath = worldPath;
        loadConfig(worldPath);
        Position positionPlayer;
        ArrayList<Position> positionMonsters;
        try {
            positionPlayer = world.findPlayer();
            positionMonsters = world.findMonsters();
            player = new Player(this, positionPlayer);
            for (Position pos : positionMonsters) {
                monsters.add(new Monster(this,pos));
            }
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
        } catch (IOException ex) {
            System.err.println("Error loading configuration");
        }
    }

    public World getWorld() {
        return world;
    }

    public Player getPlayer() {
        return this.player;
    }

    public ArrayList<Monster> getMonsters() {
        return this.monsters;
    }

    public GameObject getGameObjectAtPos(Position pos){

        if(player.getPosition().equals(pos))
            return player;

        Monster monster = monsters.stream().filter(m -> m.getPosition().equals(pos)).findAny().orElse(null);
        if(monster != null)
            return monster;

        Bomb bomb = player.getBombs().stream().filter(m -> m.getPosition().equals(pos)).findAny().orElse(null);
        return bomb;
    }


}
