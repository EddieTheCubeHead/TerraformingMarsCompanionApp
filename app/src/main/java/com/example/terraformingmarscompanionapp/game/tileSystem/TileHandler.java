package com.example.terraformingmarscompanionapp.game.tileSystem;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.events.TileChoiceEvent;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.ui.main.TilePlacementActivity;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.packets.TileEventPacket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * A class for managing the map of the game
 *
 * @author Eetu Asikainen
 * @version 0.2
 * @since 0.2
 */
public class TileHandler {
    private final GameMap map;
    private final Tile[][] mars_tiles = new Tile[17][9];
    private final Tile[] space_tiles;
    private final Game game;
    /* Coordnates are x and y. Note that only y=4 is a full row. The tiles are staggered and in a
     * hex formation.
     */

    /**
     * Constructor. Generates one of the three maps based on the given value
     *
     * @param owner_game {@link Game} that the handler is associated with
     * @param game_map {@link Integer} declaring the map to be used
     */
    public TileHandler(Game owner_game, GameMap game_map) {
        game = owner_game;

        map = game_map;

        // Init with nulls
        for (int x = 0; x < 17; x++) {
            for (int y = 0; y < 9; y++) {
                mars_tiles[x][y] = null;
            }
        }

        // Configuring tile data manually
        switch (map) {
            case THARSIS:

                mars_tiles[4][8] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.STEEL, PlacementBonus.STEEL)), false, new Integer[]{4, 8});
                mars_tiles[6][8] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.STEEL, PlacementBonus.STEEL)), true, new Integer[]{6, 8});
                mars_tiles[8][8] = new Tile(game, null, false, new Integer[]{8, 8});
                mars_tiles[10][8] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.CARD)), true, new Integer[]{10, 8});
                mars_tiles[12][8] = new Tile(game, null, true, new Integer[]{12, 8});

                mars_tiles[3][7] = new Tile(game, null, false, new Integer[]{3, 7});
                mars_tiles[5][7] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.STEEL)), false, new Integer[]{5, 7}, true);
                mars_tiles[7][7] = new Tile(game, null, false, new Integer[]{7, 7});
                mars_tiles[9][7] = new Tile(game, null, false, new Integer[]{9, 7});
                mars_tiles[11][7] = new Tile(game, null, false, new Integer[]{11, 7});
                mars_tiles[13][7] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.CARD, PlacementBonus.CARD)), true, new Integer[]{13, 7});

                mars_tiles[2][6] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.CARD)), false, new Integer[]{2, 6}, true);
                mars_tiles[4][6] = new Tile(game, null, false, new Integer[]{4, 6});
                mars_tiles[6][6] = new Tile(game, null, false, new Integer[]{6, 6});
                mars_tiles[8][6] = new Tile(game, null, false, new Integer[]{8, 6});
                mars_tiles[10][6] = new Tile(game, null, false, new Integer[]{10, 6});
                mars_tiles[12][6] = new Tile(game, null, false, new Integer[]{12, 6});
                mars_tiles[14][6] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.STEEL)), false, new Integer[]{14, 6});

                mars_tiles[1][5] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.TITANIUM, PlacementBonus.PLANT)), false, new Integer[]{1, 5}, true);
                mars_tiles[3][5] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{3, 5});
                mars_tiles[5][5] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{5, 5});
                mars_tiles[7][5] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{7, 5});
                mars_tiles[9][5] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.PLANT)), false, new Integer[]{9, 5});
                mars_tiles[11][5] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{11, 5});
                mars_tiles[13][5] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{13, 5});
                mars_tiles[15][5] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.PLANT)), true, new Integer[]{15, 5});

                mars_tiles[0][4] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.PLANT)), false, new Integer[]{0, 4}, true);
                mars_tiles[2][4] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.PLANT)), false, new Integer[]{2, 4});
                mars_tiles[4][4] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.PLANT)), false, new Integer[]{4, 4});
                mars_tiles[6][4] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.PLANT)), true, new Integer[]{6, 4});
                mars_tiles[8][4] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.PLANT)), true, new Integer[]{8, 4});
                mars_tiles[10][4] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.PLANT)), true, new Integer[]{10, 4});
                mars_tiles[12][4] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.PLANT)), false, new Integer[]{12, 4});
                mars_tiles[14][4] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.PLANT)), false, new Integer[]{14, 4});
                mars_tiles[16][4] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.PLANT)), false, new Integer[]{16, 4});

                mars_tiles[1][3] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{1, 3});
                mars_tiles[3][3] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.PLANT)), false, new Integer[]{3, 3});
                mars_tiles[5][3] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{5, 3});
                mars_tiles[7][3] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{7, 3});
                mars_tiles[9][3] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{9, 3});
                mars_tiles[11][3] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), true, new Integer[]{11, 3});
                mars_tiles[13][3] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), true, new Integer[]{13, 3});
                mars_tiles[15][3] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), true, new Integer[]{15, 3});

                mars_tiles[2][2] = new Tile(game, null, false, new Integer[]{2, 2});
                mars_tiles[4][2] = new Tile(game, null, false, new Integer[]{4, 2});
                mars_tiles[6][2] = new Tile(game, null, false, new Integer[]{6, 2});
                mars_tiles[8][2] = new Tile(game, null, false, new Integer[]{8, 2});
                mars_tiles[10][2] = new Tile(game, null, false, new Integer[]{10, 2});
                mars_tiles[12][2] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{12, 2});
                mars_tiles[14][2] = new Tile(game, null, false, new Integer[]{14, 2});

                mars_tiles[3][1] = new Tile(game, null, false, new Integer[]{3, 1});
                mars_tiles[5][1] = new Tile(game, null, false, new Integer[]{5, 1});
                mars_tiles[7][1] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.CARD)), false, new Integer[]{7, 1});
                mars_tiles[9][1] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.CARD)), false, new Integer[]{9, 1});
                mars_tiles[11][1] = new Tile(game, null, false, new Integer[]{11, 1});
                mars_tiles[13][1] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.TITANIUM)), false, new Integer[]{13, 1});

                mars_tiles[4][0] = new Tile(game, null, false, new Integer[]{4, 0});
                mars_tiles[6][0] = new Tile(game, null, false, new Integer[]{6, 0});
                mars_tiles[8][0] = new Tile(game, null, false, new Integer[]{8, 0});
                mars_tiles[10][0] = new Tile(game, null, false, new Integer[]{10, 0});
                mars_tiles[12][0] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.TITANIUM, PlacementBonus.TITANIUM)), true, new Integer[]{12, 0});
                break;


            case HELLAS:

                mars_tiles[4][8] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.PLANT)), true, new Integer[]{4, 8});
                mars_tiles[5][8] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.PLANT)), false, new Integer[]{6, 8});
                mars_tiles[8][8] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.PLANT)), false, new Integer[]{8, 8});
                mars_tiles[10][8] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{10, 8});
                mars_tiles[12][8] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{12, 8});

                mars_tiles[3][7] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.PLANT)), true, new Integer[]{3, 7});
                mars_tiles[5][7] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.PLANT)), false, new Integer[]{5, 7});
                mars_tiles[7][7] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{7, 7});
                mars_tiles[9][7] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.STEEL)), false, new Integer[]{9, 7});
                mars_tiles[11][7] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{11, 7});
                mars_tiles[13][7] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{13, 7});

                mars_tiles[2][6] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), true, new Integer[]{2, 6});
                mars_tiles[4][6] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{4, 6});
                mars_tiles[6][6] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.STEEL)), false, new Integer[]{6, 6});
                mars_tiles[8][6] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.STEEL)), false, new Integer[]{8, 6});
                mars_tiles[10][6] = new Tile(game, null, false, new Integer[]{10, 6});
                mars_tiles[12][6] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.PLANT)), false, new Integer[]{12, 6});
                mars_tiles[14][6] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.TITANIUM)), false, new Integer[]{14, 6});

                mars_tiles[1][5] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), true, new Integer[]{1, 5});
                mars_tiles[3][5] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{3, 5});
                mars_tiles[5][5] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.STEEL)), false, new Integer[]{5, 5});
                mars_tiles[7][5] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.STEEL, PlacementBonus.STEEL)), false, new Integer[]{7, 5});
                mars_tiles[9][5] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.STEEL)), false, new Integer[]{9, 5});
                mars_tiles[11][5] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), true, new Integer[]{11, 5});
                mars_tiles[13][5] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), true, new Integer[]{13, 5});
                mars_tiles[15][5] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{15, 5});

                mars_tiles[0][4] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.CARD)), false, new Integer[]{0, 4});
                mars_tiles[2][4] = new Tile(game, null, false, new Integer[]{2, 4});
                mars_tiles[4][4] = new Tile(game, null, false, new Integer[]{4, 4});
                mars_tiles[6][4] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.STEEL, PlacementBonus.STEEL)), false, new Integer[]{6, 4});
                mars_tiles[8][4] = new Tile(game, null, false, new Integer[]{8, 4});
                mars_tiles[10][4] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.CARD)), true, new Integer[]{10, 4});
                mars_tiles[12][4] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.HEAT, PlacementBonus.HEAT, PlacementBonus.HEAT)), true, new Integer[]{12, 4});
                mars_tiles[14][4] = new Tile(game, null, true, new Integer[]{14, 4});
                mars_tiles[16][4] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{16, 4});

                mars_tiles[1][3] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.TITANIUM)), false, new Integer[]{1, 3});
                mars_tiles[3][3] = new Tile(game, null, false, new Integer[]{3, 3});
                mars_tiles[5][3] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.STEEL)), false, new Integer[]{5, 3});
                mars_tiles[7][3] = new Tile(game, null, false, new Integer[]{7, 3});
                mars_tiles[9][3] = new Tile(game, null, false, new Integer[]{9, 3});
                mars_tiles[11][3] = new Tile(game, null, true, new Integer[]{11, 3});
                mars_tiles[13][3] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.STEEL)), true, new Integer[]{13, 3});
                mars_tiles[15][3] = new Tile(game, null, false, new Integer[]{15, 3});

                mars_tiles[2][2] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.TITANIUM, PlacementBonus.TITANIUM)), true, new Integer[]{2, 2});
                mars_tiles[4][2] = new Tile(game, null, false, new Integer[]{4, 2});
                mars_tiles[6][2] = new Tile(game, null, false, new Integer[]{6, 2});
                mars_tiles[8][2] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.CARD)), false, new Integer[]{8, 2});
                mars_tiles[10][2] = new Tile(game, null, false, new Integer[]{10, 2});
                mars_tiles[12][2] = new Tile(game, null, false, new Integer[]{12, 2});
                mars_tiles[14][2] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.TITANIUM)), false, new Integer[]{14, 2});

                mars_tiles[3][1] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.STEEL)), false, new Integer[]{3, 1});
                mars_tiles[5][1] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.CARD)), false, new Integer[]{5, 1});
                mars_tiles[7][1] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.HEAT, PlacementBonus.HEAT)), false, new Integer[]{7, 1});
                mars_tiles[9][1] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.HEAT, PlacementBonus.HEAT)), false, new Integer[]{9, 1});
                mars_tiles[11][1] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.TITANIUM)), false, new Integer[]{11, 1});
                mars_tiles[13][1] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.TITANIUM)), false, new Integer[]{13, 1});

                mars_tiles[4][0] = new Tile(game, null, false, new Integer[]{4, 0});
                mars_tiles[6][0] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.HEAT, PlacementBonus.HEAT)), false, new Integer[]{6, 0});
                mars_tiles[8][0] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.OCEAN)), false, new Integer[]{8, 0});
                mars_tiles[10][0] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.HEAT, PlacementBonus.HEAT)), false, new Integer[]{10, 0});
                mars_tiles[12][0] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.TITANIUM, PlacementBonus.TITANIUM)), false, new Integer[]{12, 0});
                break;


            case ELYSIUM:

                mars_tiles[4][8] = new Tile(game, null, true, new Integer[]{4, 8});
                mars_tiles[5][8] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.TITANIUM)), true, new Integer[]{6, 8});
                mars_tiles[8][8] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.CARD)), true, new Integer[]{8, 8});
                mars_tiles[10][8] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.STEEL)), true, new Integer[]{10, 8});
                mars_tiles[12][8] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.CARD)), false, new Integer[]{12, 8});

                mars_tiles[3][7] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.TITANIUM)), false, new Integer[]{3, 7}, true);
                mars_tiles[5][7] = new Tile(game, null, false, new Integer[]{5, 7});
                mars_tiles[7][7] = new Tile(game, null, false, new Integer[]{7, 7});
                mars_tiles[9][7] = new Tile(game, null, true, new Integer[]{9, 7});
                mars_tiles[11][7] = new Tile(game, null, true, new Integer[]{11, 7});
                mars_tiles[13][7] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.STEEL, PlacementBonus.STEEL)), false, new Integer[]{13, 7});

                mars_tiles[2][6] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.TITANIUM, PlacementBonus.TITANIUM)), false, new Integer[]{2, 6}, true);
                mars_tiles[4][6] = new Tile(game, null, false, new Integer[]{4, 6});
                mars_tiles[6][6] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.CARD)), false, new Integer[]{6, 6});
                mars_tiles[8][6] = new Tile(game, null, false, new Integer[]{8, 6});
                mars_tiles[10][6] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), true, new Integer[]{10, 6});
                mars_tiles[12][6] = new Tile(game, null, true, new Integer[]{12, 6});
                mars_tiles[14][6] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.CARD, PlacementBonus.CARD, PlacementBonus.CARD)), false, new Integer[]{14, 6}, true);

                mars_tiles[1][5] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{1, 5});
                mars_tiles[3][5] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{3, 5});
                mars_tiles[5][5] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{5, 5});
                mars_tiles[7][5] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.PLANT)), true, new Integer[]{7, 5});
                mars_tiles[9][5] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{9, 5});
                mars_tiles[11][5] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), true, new Integer[]{11, 5});
                mars_tiles[13][5] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), true, new Integer[]{13, 5});
                mars_tiles[15][5] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.PLANT)), false, new Integer[]{15, 5}, true);

                mars_tiles[0][4] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.PLANT)), false, new Integer[]{0, 4});
                mars_tiles[2][4] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.PLANT)), false, new Integer[]{2, 4});
                mars_tiles[4][4] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.PLANT)), false, new Integer[]{4, 4});
                mars_tiles[6][4] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.PLANT)), true, new Integer[]{6, 4});
                mars_tiles[8][4] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.PLANT)), false, new Integer[]{8, 4});
                mars_tiles[10][4] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.PLANT, PlacementBonus.PLANT)), false, new Integer[]{10, 4});
                mars_tiles[12][4] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.PLANT)), false, new Integer[]{12, 4});
                mars_tiles[14][4] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.PLANT)), false, new Integer[]{14, 4});
                mars_tiles[16][4] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.PLANT, PlacementBonus.TITANIUM)), false, new Integer[]{16, 4});

                mars_tiles[1][3] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.STEEL)), false, new Integer[]{1, 3});
                mars_tiles[3][3] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{3, 3});
                mars_tiles[5][3] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{5, 3});
                mars_tiles[7][3] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{7, 3});
                mars_tiles[9][3] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{9, 3});
                mars_tiles[11][3] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{11, 3});
                mars_tiles[13][3] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.PLANT)), false, new Integer[]{13, 3});
                mars_tiles[15][3] = new Tile(game, null, false, new Integer[]{15, 3});

                mars_tiles[2][2] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.TITANIUM)), false, new Integer[]{2, 2});
                mars_tiles[4][2] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.STEEL)), false, new Integer[]{4, 2});
                mars_tiles[6][2] = new Tile(game, null, false, new Integer[]{6, 2});
                mars_tiles[8][2] = new Tile(game, null, false, new Integer[]{8, 2});
                mars_tiles[10][2] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.STEEL)), false, new Integer[]{10, 2});
                mars_tiles[12][2] = new Tile(game, null, false, new Integer[]{12, 2});
                mars_tiles[14][2] = new Tile(game, null, false, new Integer[]{14, 2});

                mars_tiles[3][1] = new Tile(game,  new ArrayList<>(Arrays.asList(PlacementBonus.STEEL, PlacementBonus.STEEL)), false, new Integer[]{3, 1});
                mars_tiles[5][1] = new Tile(game, null, false, new Integer[]{5, 1});
                mars_tiles[7][1] = new Tile(game, null, false, new Integer[]{7, 1});
                mars_tiles[9][1] = new Tile(game, null, false, new Integer[]{9, 1});
                mars_tiles[11][1] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.STEEL, PlacementBonus.STEEL)), false, new Integer[]{11, 1});
                mars_tiles[13][1] = new Tile(game, null, false, new Integer[]{13, 1});

                mars_tiles[4][0] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.STEEL)), false, new Integer[]{4, 0});
                mars_tiles[6][0] = new Tile(game, null, false, new Integer[]{6, 0});
                mars_tiles[8][0] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.CARD)), false, new Integer[]{8, 0});
                mars_tiles[10][0] = new Tile(game, new ArrayList<>(Collections.singletonList(PlacementBonus.CARD)), false, new Integer[]{10, 0});
                mars_tiles[12][0] = new Tile(game, new ArrayList<>(Arrays.asList(PlacementBonus.STEEL, PlacementBonus.STEEL)), false, new Integer[]{12, 0});
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + map);
        }

        // Space tiles don't have placement or adjacency bonuses. Venus-expansion adds 4 of these
        if (owner_game.modifiers.getVenus()) {
            space_tiles = new Tile[7];
        } else {
            space_tiles = new Tile[3];
        }

        // Init space tiles
        for (int i = 0; i < space_tiles.length; i++) {
            space_tiles[i] = new Tile(game);
        }
    }

    /**
     * A method to get a tile in the given coordinates
     *
     * @param x {@link Integer} the x coordinate of the tile
     * @param y {@link Integer} the y coordinate of the tile
     * @return {@link Tile} in the given coordinates. Can be null if coordinates are invalid (outside the hexagonal map, inside the square array)
     */
    public Tile getTile(Integer x, Integer y) {
        return mars_tiles[x][y];
    }

    /**
     * a method that hooks to the tile placement UI from the {@link TileChoiceEvent} -Event
     *
     * @param tile_type {@link Placeable} being placed
     * @param context {@link Context} UI context the event is called from
     */
    public void getCoordinatesFromPlayer(Placeable tile_type, Context context) {
        Intent intent = new Intent(context, TilePlacementActivity.class);
        intent.putExtra("tile", tile_type.toString());
        System.out.println("Starting tile placement activity");
        context.startActivity(intent);
    }

    /**
     * A method to place a placeable in the given tile.
     *
     * @param player {@link Player} placing the placeable
     * @param target_tile {@link Tile} that the placeable is being placed on
     * @param tile_type {@link Placeable} that is being placed
     * @return {@link ArrayList} of {@link String} if the placealbe is of type FLOOD_OCEAN. Represents the players flood money removal can target. Otherwise returns null
     */
    public ArrayList<String> placeTile(Player player, Tile target_tile, Placeable tile_type) {
        ArrayList<Placeable> to_city = new ArrayList<>(Arrays.asList(Placeable.CITY, Placeable.RESEARCH_OUTPOST, Placeable.NOCTIS, Placeable.VOLCANIC_CITY, Placeable.URBANIZED_AREA));
        ArrayList<Placeable> to_ocean = new ArrayList<>(Arrays.asList(Placeable.OCEAN, Placeable.LAND_OCEAN, Placeable.FLOOD_OCEAN));
        ArrayList<Placeable> to_greenery = new ArrayList<>(Arrays.asList(Placeable.GREENERY, Placeable.OCEAN_GREENERY));

        ArrayList<String> flood_neighbours = new ArrayList<>();
        boolean flood = false;

        if (tile_type.equals(Placeable.FLOOD_OCEAN)) {
            flood = true;
            for (Tile tile : getNeighbours(target_tile)) {
                if (tile.getOwner() != null) {
                    flood_neighbours.add(tile.getOwner().getName());
                    System.out.println(tile.getOwner().getName());
                }
            }
        }

        if (to_city.contains(tile_type)) {
            tile_type = Placeable.CITY;

        } else if (to_ocean.contains(tile_type)) {
            tile_type = Placeable.OCEAN;

        } else if (to_greenery.contains(tile_type)) {
            tile_type = Placeable.GREENERY;

        }

        for (Tile neighbour : getNeighbours(target_tile)) {
            if (neighbour != null) {
                if (neighbour.getPlacedHex() != null && neighbour.getPlacedHex() == Placeable.OCEAN) {
                    player.getResources().setMoney(player.getResources().getMoney() + 2 + player.getModifiers().getOceanAdjacencyBonusModifier());
                }
            }
        }

        TileEventPacket packet = new TileEventPacket(tile_type, GameController.getCurrentPlayer().getName(), target_tile.getX(), target_tile.getY());
        if (GameController.getServerMultiplayer()) {
            GameActions.sendTileEvent(packet);
        }
        packet.playPacket();

        if (flood) {
            return flood_neighbours;
        }
        return null;
    }


    /**
     * A method to get the neighbouring tiles for the given tile
     *
     * @param tile {@link Tile} that the neighbours needed belong to. Make sure the tile is not a space tile
     * @return {@link ArrayList} of {@link Tile} that contains all neighbours
     */
    public ArrayList<Tile> getNeighbours(Tile tile) {
        Integer x = tile.getX();
        Integer y = tile.getY();

        ArrayList<Tile> neighbours = new ArrayList<>();
        try {
            if (mars_tiles[x+2][y] != null) {
                neighbours.add(mars_tiles[x+2][y]);
            }
        } catch (Exception ignored) {
        }

        try {
            if (mars_tiles[x - 2][y] != null) {
                neighbours.add(mars_tiles[x - 2][y]);
            }
        } catch (Exception ignored) {
        }

        try {
            if (mars_tiles[x + 1][y + 1] != null) {
                neighbours.add(mars_tiles[x + 1][y + 1]);
            }
        } catch (Exception ignored) {
        }

        try {
            if (mars_tiles[x - 1][y + 1] != null) {
                neighbours.add(mars_tiles[x - 1][y + 1]);
            }
        } catch (Exception ignored) {
        }

        try {
            if (mars_tiles[x + 1][y - 1] != null) {
                neighbours.add(mars_tiles[x + 1][y - 1]);
            }
        } catch (Exception ignored) {
        }

        try {
            if (mars_tiles[x - 1][y - 1] != null) {
                neighbours.add(mars_tiles[x - 1][y - 1]);
            }
        } catch (Exception ignored) {
        }

        return neighbours;
    }

    /**
     * A method to check the validity of the action of palcing a placeable in a tile
     *
     * @param tile_type {@link Placeable} being placed
     * @param x {@link Integer} the x coordinate the placeable is being placed to
     * @param y {@link Integer} the y coordinate the placeable is being placed to
     * @return {@link Boolean} whether the placement action is valid with the current game rules
     */
    public Boolean checkPlacementValidity(Placeable tile_type, Integer x, Integer y) {
        ArrayList<Placeable> ocean_placement = new ArrayList<>(Arrays.asList(Placeable.OCEAN, Placeable.OCEAN_GREENERY, Placeable.MOHOLE, Placeable.FLOOD_OCEAN));

        ArrayList<Placeable> volcanic_placement = new ArrayList<>(Arrays.asList(Placeable.LAVA_FLOW, Placeable.VOLCANIC_CITY));

        Tile to_place = getTile(x, y);

        if (map.equals(GameMap.THARSIS) && x == 4 && y == 4) {
            Log.i("Tile placement", "Noctis tile");
            return tile_type.equals(Placeable.NOCTIS);
        }

        // Hellas doesn't have any volcanic tiles
        if ((volcanic_placement.contains(tile_type) && (map != GameMap.HELLAS)) && !to_place.getIsVolcanic()) {
            Log.i("Invalid tile placement", "Requires volcanic");
            return false;
        }

        // Whether the tile should be placed on land or on ocean
        if (ocean_placement.contains(tile_type) && !to_place.getIsOcean()) {
            Log.i("Invalid tile placement", "Requires ocean");
            return false;
        } else if (!ocean_placement.contains(tile_type) && to_place.getIsOcean()) {
            Log.i("Invalid tile placement", "Requires land");
            return false;
        }

        // Whether the placing spot already has tiles
        if (to_place.getPlacedHex() != null) {
            Log.i("Invalid tile placement", "Hex reserved");
            return false;
        }

        // Special cases for specific tile types
        // Note that greeneries require that you place them next to an owned tile if possible
        // At the moment the algorithm for that seems excessive enough to leave managing that to players
        switch (tile_type) {
            // Tiles requiring all neighbours to be empty
            case NATURAL_RESERVE:
            case RESEARCH_OUTPOST:
                for (Tile neighbour : getNeighbours(to_place)) {
                    if (neighbour.getPlacedHex() != null && neighbour.getPlacedHex() != Placeable.LAND_CLAIM) {
                        Log.i("Invalid tile placement", "Requires empty neighbours");
                        return false;
                    }
                }
                break;

            // Cities cannot get placed next to each other
            case NOCTIS:
                // Noctis is an exception, but only on tharsis. Falls through if map is not tharsis
                if (map == GameMap.THARSIS) {
                    break;
                }
            case CITY:
            case CAPITAL:
                for (Tile neighbour : getNeighbours(to_place)) {
                    if (neighbour.getPlacedHex() == Placeable.CITY | neighbour.getPlacedHex() == Placeable.CAPITAL) {
                        Log.i("Invalid tile placement", "Requires non-city neighbours");
                        return false;
                    }
                }
                break;

            // Ecological zone requires a greenery next to it
            case ECOLOGICAL_ZONE:
                boolean has_greenery = false;
                for (Tile neighbour : getNeighbours(to_place)) {
                    if (neighbour.getPlacedHex() == Placeable.GREENERY | neighbour.getPlacedHex() == Placeable.OCEAN_GREENERY) {
                        has_greenery = true;
                        break;
                    }
                }
                if (!has_greenery) {
                    Log.i("Invalid tile placement", "Requires greenery neighbours");
                    return false;
                }
                break;

            // Mining area and mining rights. Area has extra checks, but falls through into the common
            // checks for tile placement bonuses
            case MINING_AREA:
                boolean has_owner = false;
                for (Tile neighbour : getNeighbours(to_place)) {
                    if (neighbour.getOwner() == GameController.getCurrentPlayer()) {
                        has_owner = true;
                        break;
                    }
                }
                if (!has_owner) {
                    Log.i("Invalid tile placement", "Requires owned neighbour");
                    return false;
                }
            case MINING_RIGHTS:
                if (to_place.getPlacementBonuses() != null &&
                        (!to_place.getPlacementBonuses().contains(PlacementBonus.STEEL) &&
                        !to_place.getPlacementBonuses().contains(PlacementBonus.TITANIUM))) {
                    Log.i("Invalid tile placement", "Requires placement bonus");
                    return false;
                }
                break;

            case URBANIZED_AREA:
                int neighbour_cities = 0;
                for (Tile neighbour : getNeighbours(to_place)) {
                    if (neighbour.getPlacedHex() == Placeable.CITY | neighbour.getPlacedHex() == Placeable.CAPITAL) {
                        neighbour_cities++;
                    }
                }
                if (neighbour_cities < 2) {
                    return false;
                }
                break;

            default:
                break;
        }

        return true;
    }

    /**
     * A simple method for placing the city from the card {@link com.example.terraformingmarscompanionapp.cards.basegame.cards.GanymedeColony}
     * into a reserved space tile
     *
     * @param player {@link Player} placing the tile
     */
    public void placeGanymede(Player player) {
        game.update_manager.onCityPlaced(player, false);
        space_tiles[0].placeHex(player, Placeable.CITY, GameController.getContext());
    }

    /**
     * A simple method for placing the city from the card {@link com.example.terraformingmarscompanionapp.cards.basegame.cards.PhobosSpaceHaven}
     * into a reserved space tile
     *
     * @param player {@link Player} placing the tile
     */
    public void placePhobos(Player player) {
        game.update_manager.onCityPlaced(player, false);
        space_tiles[1].placeHex(player, Placeable.CITY, GameController.getContext());
    }
}
