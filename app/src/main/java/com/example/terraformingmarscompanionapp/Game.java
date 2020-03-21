package com.example.terraformingmarscompanionapp;

import java.util.ArrayList;
import java.util.HashMap;

class Game {
    private ArrayList<Player> players = new ArrayList<>();
    private HashMap<String, Card> deck;
    private HashMap<String, Card> preludes = new HashMap<>();
    private HashMap<String, Card> corporations = new HashMap<>();
    private GameConstructor constructor = new GameConstructor();
    private Integer global_temperature;
    private Integer global_oxygen;
    private Integer oceans_placed;
    private Integer venus_terraform;

    public Game(int player_count, boolean hellas_elysium, boolean corporate_era, boolean prelude, boolean colonies, boolean venus, boolean turmoil) {


        for (int i = 0; i < player_count; i++) {
            players.add(new Player());
        }

        deck= constructor.createDeck(corporate_era, prelude, colonies, venus, turmoil);

        if (prelude) {
            preludes = constructor.createPreludes();
        }

        global_temperature = -30;
        global_oxygen = 0;
        oceans_placed = 0;

        //TODO finish constructor
    }

    public boolean placeOcean(Player placing_player) {
        if (oceans_placed >= 9) {
            return false;
        }
        //TODO Lisää UpdateManager.ocean_placed
        //TODO Lisää pelaajan manipulointi (TR yms.)
        oceans_placed++;
        return true;
    }

    public boolean raiseTemperature(Player raising_player) {
        if (global_temperature >= 8) {
            return false;
        }
        //TODO tarkista onko lämmön nostolle triggeriä, lisää tarvittaessa update manager
        //TODO Lisää pelaajan manipulointi (TR yms.)
        global_temperature += 2;
        return true;
    }

    public boolean raiseOxygen(Player raising_player) {
        if (global_oxygen >= 14) {
            return false;
        }
        //TODO tarkista onko hapen nostolle triggeriä, lisää tarvittaessa update manager
        //TODO Lisää pelaajan manipulointi (TR yms.)
        global_oxygen++;
        return true;
    }

    public boolean raiseVenus(Player raising_player) {
        if (venus_terraform >= 30) { //TODO tarkista venuksen max TR
            return false;
        }
        //TODO tarkista onko venuksen nostolle triggeriä, lisää tarvittaessa update manager
        //TODO Lisää pelaajan manipulointi (TR yms.)
        venus_terraform++; //TODO tarkista nouseeko venus yhden vai kahden inkrementeissä.
        return true;
    }

    public Integer getGlobal_temperature() {return global_temperature;}

    public Integer getGlobal_oxygen() {return global_oxygen;}

    public Integer getOceans_placed() {return oceans_placed;}

    public Integer getVenus_terraform() {return venus_terraform;}
}
