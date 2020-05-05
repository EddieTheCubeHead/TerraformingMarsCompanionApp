package com.example.terraformingmarscompanionapp.game.tileSystem;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.ui.main.TilePlacementActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TileHandler {
    private final Integer map;
    private final Tile[][] mars_tiles = new Tile[17][9];
    private final Tile[] space_tiles;
    private final Game game;
    /* koordinaatit x, y, huomioitavaa, että vain y=4 on täysi rivi heksoja
     * Esimerkiksi 0, 3 on tyhjä
     */

    public TileHandler(Game owner_game, Integer game_map, Boolean venus_in_game) {
        game = owner_game;

        //Map: 0 = perus, 1 = hellas, 2 = elysium
        map = game_map;
        //Alustetaan kartta null-arvoilla
        for (int x = 0; x < 17; x++) {
            for (int y = 0; y < 9; y++) {
                mars_tiles[x][y] = null;
            }
        }

        //Manuaalisesti asetetaan karttaa vastaavat tiilet. Hardkoodattu, koska karttoja on vain kolme
        //Suosittelen minimoimaan
        switch (map) {
            case 0:
                //Tharsis/peruspeli
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


            case 1:
                //Hellas
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


            case 2:
                //Elysium
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

        //Avaruustiilillä ei ole asettamisbonusta tai viereisyysbonuksia. Venus-lisäri lisää 4 avaruustiiltä
        if (venus_in_game) {
            space_tiles = new Tile[7];
        } else {
            space_tiles = new Tile[3];
        }

        //Avaruustiilten alustaminen
        for (int i = 0; i < space_tiles.length; i++) {
            space_tiles[i] = new Tile(game);
        }
    }

    public void placeGanymede(Player player) {
        if (space_tiles[0] != null) {
            //TODO error handling
            return;
        }
        game.update_manager.onCityPlaced(player, false);
        space_tiles[0].placeHex(player, Placeable.CITY);
    }

    public void placePhobos(Player player) {
        if (space_tiles[1] != null) {
            //TODO error handling
            return;
        }
        game.update_manager.onCityPlaced(player, false);
        space_tiles[1].placeHex(player, Placeable.CITY);
    }

    public void placeTile(Player player, Tile to_place, Placeable tile_type) {
        ArrayList<Placeable> to_city = new ArrayList<>(Arrays.asList(Placeable.CITY, Placeable.RESEARCH_OUTPOST, Placeable.NOCTIS, Placeable.VOLCANIC_CITY, Placeable.URBANIZED_AREA));
        ArrayList<Placeable> to_ocean = new ArrayList<>(Arrays.asList(Placeable.OCEAN, Placeable.LAND_OCEAN, Placeable.FLOOD_OCEAN));
        ArrayList<Placeable> to_greenery = new ArrayList<>(Arrays.asList(Placeable.GREENERY, Placeable.OCEAN_GREENERY));

        ArrayList<Player> flood_neighbours = new ArrayList<>();
        boolean flood = false;

        if (tile_type.equals(Placeable.FLOOD_OCEAN)) {
            flood = true;
            for (Tile tile : getNeighbours(to_place)) {
                if (tile.getOwner() != null) {
                    flood_neighbours.add(tile.getOwner());
                }
            }
        }

        if (to_city.contains(tile_type)) {
            player.addCity();
            game.update_manager.onCityPlaced(player, false);
            tile_type = Placeable.CITY;
        } else if (to_ocean.contains(tile_type)) {
            tile_type = Placeable.OCEAN;
            game.update_manager.onOceanPlaced(player);
        } else if (to_greenery.contains(tile_type)) {
            tile_type = Placeable.GREENERY;
            player.addGreenery();
            game.update_manager.onGreeneryPlaced(player);
        } else if (tile_type.equals(Placeable.CAPITAL)) {
            game.update_manager.onCityPlaced(player, false);
            player.addCity();
        }

        for (Tile neighbour : getNeighbours(to_place)) {
            if (neighbour != null) {
                if (neighbour.getPlacedHex() != null && neighbour.getPlacedHex() == Placeable.OCEAN) {
                    player.changeMoney(2 + player.getOceanAdjacencyBonusModifier());
                }
            }
        }
        to_place.placeHex(player, tile_type);
        player.addTile(to_place);

        if (flood && flood_neighbours.size() > 0) {
            //TODO pelaajanvalintaUI tähän flood_neighbours -listalla
        }
    }


    //Yksityinen funktio tiilen naapurien saamiseen
    private ArrayList<Tile> getNeighbours(Tile tile) {
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

    //UI-hook funktio. Tulee kysymään GUI:n avulla mihin koordinaatteihin tiili asetetaan
    public void getCoordinatesFromPlayer(Placeable tile_type) {
        Context context = GameController.getInstance().getContext();
        Intent intent = new Intent(context, TilePlacementActivity.class);
        intent.putExtra("tile", tile_type.toString());
        context.startActivity(intent);
    }

    public Boolean checkPlacementValidity(Placeable tile_type, Integer x, Integer y) {
        ArrayList<Placeable> ocean_placement = new ArrayList<>(Arrays.asList(Placeable.OCEAN, Placeable.OCEAN_GREENERY, Placeable.MOHOLE));

        ArrayList<Placeable> volcanic_placement = new ArrayList<>(Arrays.asList(Placeable.LAVA_FLOW, Placeable.VOLCANIC_CITY));

        Tile to_place = getTile(x, y);

        if (map == 0 && x == 4 && y == 4) {
            Log.i("Tile placement", "Noctis tile");
            return tile_type.equals(Placeable.NOCTIS);
        }

        //Hellas ei sisällä tuliperäisiä tiiliä
        if ((volcanic_placement.contains(tile_type) && (map != 1)) && !to_place.getIsVolcanic()) {
            Log.i("Invalid tile placement", "Requires volcanic");
            return false;
        }

        //Onko mereen vai maalle asetettava tiili
        if (ocean_placement.contains(tile_type) && !to_place.getIsOcean()) {
            Log.i("Invalid tile placement", "Requires ocean");
            return false;
        } else if (!ocean_placement.contains(tile_type) && to_place.getIsOcean()) {
            Log.i("Invalid tile placement", "Requires land");
            return false;
        }

        //Onko asetuspaikassa jo tiiltä
        if (to_place.getPlacedHex() != null) {
            Log.i("Invalid tile placement", "Hex reserved");
            return false;
        }

        //Erikoistapaukset:
        //Huomioitavaa, että viheriöt vaativat naapuriksi kyseisen pelaajan omistaman heksan JOS MAHDOLLISTA.
        //Tämän implementointi olisi sellainen algoritmin sekasorto että jätettäköön herrasmiessäännöksi
        switch (tile_type) {
            //Tiilet jotka vaativat naapuritiilten olevan tyhjiä
            case NATURAL_RESERVE:
            case RESEARCH_OUTPOST:
                for (Tile neighbour : getNeighbours(to_place)) {
                    if (neighbour.getPlacedHex() != null) {
                        Log.i("Invalid tile placement", "Requires empty neighbours");
                        return false;
                    }
                }
                break;

            //Kaupunkeja ei saa asettaa vierekkäin
            case NOCTIS:
                //Jos kartta ei sisällä noctikselle varattua tiiltä, tippuu läpi normi kaupunkicheckkiin.
                if (map == 0) {
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

            //Ecological zone tarvitsee viheriön viereen
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

            //Mining area ja mining rights. Areassa lisätarkistus, tippuu rightsin tarkistukseen
            case MINING_AREA:
                boolean has_owner = false;
                for (Tile neighbour : getNeighbours(to_place)) {
                    if (neighbour.getOwner() == GameController.getInstance().getCurrentPlayer()) {
                        has_owner = true;
                        break;
                    }
                }
                if (!has_owner) {
                    Log.i("Invalid tile placement", "Requires owned neighbour");
                    return false;
                }
            case MINING_RIGHTS:
                if (!to_place.getPlacementBonuses().contains(PlacementBonus.STEEL) | !to_place.getPlacementBonuses().contains(PlacementBonus.TITANIUM)) {
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

    public Tile getTile(Integer x, Integer y) {
        return mars_tiles[x][y];
    }
}
