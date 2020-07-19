package com.example.terraformingmarscompanionapp.game.player;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;
import com.example.terraformingmarscompanionapp.game.tileSystem.Tile;

import java.util.ArrayList;
import java.util.Comparator;

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

    // All tiles this player owns a placeable on
    private ArrayList<Tile> owned_tiles = new ArrayList<>();

    // Compositing more dataclasses
    private PlayerModifiers modifiers = new PlayerModifiers();
    private PlayerResources resources = new PlayerResources(this);
    private PlayerTags tags = new PlayerTags();

    // Aids in turn order logic
    private Boolean drew_cards_this_gen = false;
    private Boolean played_preludes = false;

    /**
     * Constructor
     *
     * @param super_game {@link Game} associated with this player
     * @param player_name {@link String} the name of this player
     */
    public Player(Game super_game, String player_name) {
        game = super_game;
        name = player_name;
    }

    /**
     * A simple method to increment the amount of cities this player owns
     */
    public void addCity() {
        cities++;
    }

    /**
     * A simple method to increment the amount of greeneries this player owns
     */
    public void addGreenery() {
        greeneries++;
    }

    /**
     * A simple method to increment the amount of colonies this player owns
     */
    public void addColony() {
        colonies++;
    }


    /**
     * @param value {@link Boolean} whether this player already played {@link com.example.terraformingmarscompanionapp.cards.basegame.utilityCards.RoundStartDraw} this generation
     */
    public void setDrewCardsThisGen(Boolean value) {
        drew_cards_this_gen = value;
    }

    /**
     * @return {@link Boolean} whether this player already played {@link com.example.terraformingmarscompanionapp.cards.basegame.utilityCards.RoundStartDraw} this generation
     */
    public Boolean getDrewCardsThisGen() {
        return drew_cards_this_gen;
    }

    /**
     * @param value {@link Boolean} whether this player has played preludes in this game
     */
    public void setPlayedPreludes(Boolean value) {
        played_preludes = value;
    }

    /**
     * @return {@link Boolean} whether this player has played preludes this game
     */
    public Boolean getPlayedPreludes() {
        return played_preludes;
    }

    /**
     * @param card {@link Card} representing the corporation of this player. Should have a {@link Type} of CORPORATION
     */
    public void setCorporation(Card card) {
        corporation=card;
    }

    /**
     * A simple method to increment the amount of green project cards the player has played
     */
    public void addGreen() {
        green_cards++;
    }

    /**
     * A simple method to increment the amount of red project cards the player has played
     */
    public void addRed() {
        red_cards++;
    }

    /**
     * A simple method to increment the amount of blue project cards the player has played
     */
    public void addBlue() {
        blue_cards++;
    }


    /**
     * @return {@link PlayerModifiers} containing all data of the modifiers of this player
     */
    public PlayerModifiers getModifiers() {
        return modifiers;
    }

    /**
     * @return {@link PlayerResources} containing all data of the resources of this player
     */
    public PlayerResources getResources() {
        return resources;
    }

    /**
     * @return {@link PlayerTags} containing all data of the tags of this player
     */
    public PlayerTags getTags() {
        return tags;
    }


    /**
     * @return {@link String} the name of this player
     */
    public String getName() {
        return name;
    }

    /**
     * @return {@link Integer} the amount of cities this player owns
     */
    public Integer getCities() {
        return cities;
    }

    /**
     * @return {@link Integer} the amount of greeneries this player owns
     */
    public Integer getGreeneries() {
        return greeneries;
    }

    /**
     * @return {@link Integer} the amount of colonies this player owns
     */
    public Integer getColonies() {
        return colonies;
    }

    /**
     * @return {@link Integer} the amount of cards this player has in hand
     */
    public Integer getHandSize() {
        return hand_size;
    }

    /**
     * @return {@link ArrayList} of {@link Tile} containing all tiles this player owns
     */
    public ArrayList<Tile> getOwnedTiles() {
        return owned_tiles;
    }

    /**
     * @return {@link Card} representing the corporation of this player
     */
    public Card getCorporation() {
        return corporation;
    }

    /**
     * @return {@link Integer} the amount of green project cards this player has played
     */
    public Integer getGreen() {
        return green_cards;
    }

    /**
     * @return {@link Integer} the amount of red project cards this player has played
     */
    public Integer getRed() {
        return red_cards;
    }

    /**
     * @return {@link Integer} the amount of blue project cards this player has played
     */
    public Integer getBlue() {
        return blue_cards;
    }

    /**
     * @return {@link Integer} the amount of victory points this player has
     */
    public Integer getVictoryPoints() {
        return victory_points;
    }

    /**
     * @param value {@link Integer} the amount of victory points this player has
     */
    public void setVictoryPoints(Integer value) {
        victory_points = value;
    }

    /**
     * A method to modify the hand size of the player
     *
     * @param change_amount {@link Integer} the amount representing the change. Can be negative to reduce hand size
     */
    public void changeHandSize(Integer change_amount) {
        hand_size += change_amount;
        if (hand_size < 0) {
            Log.i("Player", "Trying to change into negative hand size!");
            hand_size = 0;
        }
    }

    /**
     * A method to add a tile to the list of tiles the player owns a placeable on
     *
     * @param tile {@link Tile} the should be added to the list of owned tiles
     */
    public void addTile(Tile tile) {
        owned_tiles.add(tile);
    }

    /**
     * A method to count the points the player gets from the data stored in this class or it's composited
     * classes.
     */
    public void countPoints() {

        // Scoring tiles
        for (Tile tile : owned_tiles) {
            switch (tile.getPlacedHex()) {
                case CAPITAL:
                    for (Tile neighbour : game.tile_handler.getNeighbours(tile)) {
                        if (neighbour != null && (neighbour.getPlacedHex() == Placeable.OCEAN || neighbour.getPlacedHex().equals(Placeable.GREENERY))) {
                            victory_points++;
                        }
                    }
                    break;
                case CITY:
                    for (Tile neighbour : game.tile_handler.getNeighbours(tile)) {
                        if (neighbour != null && (neighbour.getPlacedHex() == Placeable.GREENERY)) {
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

    // https://dzone.com/articles/sorting-java-arraylist
    /**
     * A comparator for sorting players by points at the end of the game
     */
    public static Comparator<Player> pointComparator = new Comparator<Player>() {
        @Override
        public int compare(Player p1, Player p2) {
            // Raw point comparison
            if (p2.getVictoryPoints() < p1.getVictoryPoints()) {
                return -1;
            } else if (p2.getVictoryPoints() > p1.getVictoryPoints()) {
                return 1;
            // Money as a tiebreaker
            } else return p2.getResources().getMoney().compareTo(p1.getResources().getMoney());
        }
    };

    /**
     * Overriding toString for spinner use
     *
     * @return {@link String} the name of this player
     */
    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
