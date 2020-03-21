package com.example.terraformingmarscompanionapp;

import java.util.ArrayList;
import java.util.HashMap;

class GameConstructor {
    GameConstructor() {

    }

    HashMap<String, Card> createDeck(boolean corporate_era,
                                     boolean prelude,
                                     boolean colonies,
                                     boolean venus,
                                     boolean turmoil) {
        HashMap<String, Card> deck = new HashMap<>();

        //Peruspelin kortit:
        //TODO lisää kaikki peruspelin kortit tähän

        //Corporate eran kortit:
        if (corporate_era) {
            //TODO lisää Corporate eran kortit tähän
        }

        //Preludin kortit:
        if (prelude) {
            //TODO lisää Preludin kortit (ei preludit) tähän
        }

        //Colonies kortit:
        if (colonies) {
            //TODO Lisää Colonies-kortit tähän
        }

        //Venus kortit:
        if (venus) {
            //TODO Lisää Venus-kortit tähän
        }

        //Turmoil kortit:
        if (turmoil) {
            //TODO Lisää Turmoil-korit tähän
        }

        return deck;
    }

    public HashMap<String, Card> createPreludes() {
        HashMap<String, Card> preludes = new HashMap<>();
        //TODO Lisää preludit tähän
        return preludes;
    }

    public HashMap<String, Card> createCorporations() {
        HashMap<String, Card> corporations = new HashMap<>();

        return corporations;
    }
}
