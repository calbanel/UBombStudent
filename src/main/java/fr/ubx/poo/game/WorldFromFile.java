package fr.ubx.poo.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WorldFromFile extends World{

    public WorldFromFile(String file) {
        super(fromFile(file));
    }

    private static WorldEntity[][] fromFile(String file){
        WorldEntity[][] we = null;
        String line;
        int linecpt = 0;

        try(BufferedReader in = new BufferedReader(new FileReader(file))){
            we = new WorldEntity[(int)in.lines().count()][];
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }


        try(BufferedReader in = new BufferedReader(new FileReader(file))){
            while(true) {
                line = in.readLine();
                if(line == null){
                    break;
                }
                if (we != null) {
                    we[linecpt] = linewe(line);
                }
                linecpt++;

            }
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }

        return we;
    }

    private static WorldEntity[] linewe(String l){
        WorldEntity[] linewe = new WorldEntity[l.length()];
        for (int i = 0; i < l.length(); i++) {
            if(WorldEntity.fromCode(l.charAt(i)).isPresent()){
                linewe[i] = WorldEntity.fromCode(l.charAt(i)).get();
            }
            else{
                linewe[i] = WorldEntity.Empty;
            }
        }
        return linewe;
    }
}
