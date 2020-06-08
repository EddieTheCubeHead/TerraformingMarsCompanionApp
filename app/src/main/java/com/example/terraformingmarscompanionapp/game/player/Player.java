package com.example.terraformingmarscompanionapp.game.player;
import androidx.annotation.NonNull;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;
import com.example.terraformingmarscompanionapp.game.tileSystem.Tile;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {

    private final Game game;
    private final String name;

    private Integer cities = 0;
    private Integer greeneries = 0;
    private Integer colonies = 0;
    private Integer victory_points = 0;

    private Integer hand_size = 0; //Even though actual cards are not tracked, hand size is used for milestone requirements
    private Card corporation;
    private Integer green_cards = 0;
    private Integer red_cards = 0;
    private Integer blue_cards = 0;

    private ArrayList<Tile> owned_tiles = new ArrayList<>();
    private final ArrayList<ResourceCard> resource_holders = new ArrayList<>();

    private PlayerModifiers modifiers = new PlayerModifiers();
    private PlayerResources resources = new PlayerResources();
    private PlayerTags tags = new PlayerTags();

    // Aids in turn order logic
    private Boolean drew_cards_this_gen = false;

    public Player(Game super_game, String player_name) {
        game = super_game;
        name = player_name;
    }

    public void addCity() {
        cities++;
    }

    public void addGreenery() {
        greeneries++;
    }

    public void addColony() {
        colonies++;
    }


    public void setDrewCardsThisGen(Boolean value) {
        drew_cards_this_gen = value;
    }

    public Boolean getDrewCardsThisGen() {
        return drew_cards_this_gen;
    }


    public void setCorporation(Card card) {
        corporation=card;
    }

    public void addGreen() {
        green_cards++;
    }

    public void addRed() {
        red_cards++;
    }

    public void addBlue() {
        blue_cards++;
    }

    public void addResourceHolder(ResourceCard card) {
        resource_holders.add(card);
    }


    public PlayerModifiers getModifiers() {
        return modifiers;
    }

    public PlayerResources getResources() {
        return resources;
    }

    public PlayerTags getTags() {
        return tags;
    }


    public String getName() {
        return name;
    }


    public Integer getCities() {
        return cities;
    }

    public Integer getGreeneries() {
        return greeneries;
    }

    public Integer getColonies() {
        return colonies;
    }

    public Integer getHandSize() {
        return hand_size;
    }

    public ArrayList<Tile> getOwnedTiles() {
        return owned_tiles;
    }


    public Card getCorporation() {
        return corporation;
    }


    public Integer getGreen() {
        return green_cards;
    }

    public Integer getRed() {
        return red_cards;
    }

    public Integer getBlue() {
        return blue_cards;
    }

    public ArrayList<ResourceCard> getResourceHolders() {
        return resource_holders;
    }

    public Integer getVictoryPoints() {
        return victory_points;
    }

    public void setVictoryPoints(Integer value) {
        victory_points = value;
    }

    public Boolean changeHandSize(Integer change_amount) {
        if (hand_size + change_amount < 0) {
            return false;
        }
        hand_size += change_amount;
        return true;
    }

    public void addTile(Tile tile) {
        owned_tiles.add(tile);
    }

    void countPoints() {

        //Tiilien pisteet
        for (Tile tile : owned_tiles) {
            switch (tile.getPlacedHex()) {
                case CAPITAL:
                    for (Tile neighbour : game.tile_handler.getNeighbours(tile)) {
                        if (neighbour.getPlacedHex().equals(Placeable.OCEAN) || neighbour.getPlacedHex().equals(Placeable.GREENERY)) {
                            victory_points++;
                        }
                    }
                    break;
                case CITY:
                    for (Tile neighbour : game.tile_handler.getNeighbours(tile)) {
                        if (neighbour.getPlacedHex().equals(Placeable.GREENERY)) {
                            victory_points++;
                        }
                    }
                    break;
                case GREENERY:
                    victory_points++;
                    break;
            }
        }

        victory_points += resources.getTerraformingRating();
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
