package com.example.terraformingmarscompanionapp;

import java.util.HashMap;

public abstract class Card {
    private Game owner_game;
    private Integer price;
    private Integer owner_player; //Pelaajan numero pelin players-ArrayListissä
    private HashMap<String, Integer> tags;
    private HashMap<String, Integer> requirements;

    public abstract void onPlay ();
    public abstract void cardEffect ();
    public abstract void cardAction ();
}
