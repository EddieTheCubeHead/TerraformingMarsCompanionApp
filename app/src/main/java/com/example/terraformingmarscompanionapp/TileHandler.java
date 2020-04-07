package com.example.terraformingmarscompanionapp;

import android.net.wifi.p2p.WifiP2pManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TileHandler {
    private Integer map;
    private Tile[][] mars_tiles = new Tile[17][9];
    private Tile[] space_tiles;
    private Game game;
    /* koordinaatit x, y, huomioitavaa, että vain y=4 on täysi rivi heksoja
     * Esimerkiksi 0, 3 on tyhjä
     */


    TileHandler(Game owner_game, Integer game_map, Boolean venus_in_game) {
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
        switch (map) {
            case 0:
                //Peruspelin kartta
                mars_tiles[4][8] = new Tile(game, new ArrayList<>(Arrays.asList("steel", "steel")), false, new Integer[]{4, 8});
                mars_tiles[5][8] = new Tile(game, new ArrayList<>(Arrays.asList("steel", "steel")), true, new Integer[]{6, 8});
                mars_tiles[8][8] = new Tile(game, null, false, new Integer[]{8, 8});
                mars_tiles[10][8] = new Tile(game, new ArrayList<>(Collections.singletonList("card")), true, new Integer[]{10, 8});
                mars_tiles[12][8] = new Tile(game, null, true, new Integer[]{12, 8});

                mars_tiles[3][7] = new Tile(game, null, false, new Integer[]{3, 7});
                mars_tiles[5][7] = new Tile(game, new ArrayList<>(Collections.singletonList("steel")), false, new Integer[]{5, 7}, true);
                mars_tiles[7][7] = new Tile(game, null, false, new Integer[]{7, 7});
                mars_tiles[9][7] = new Tile(game, null, false, new Integer[]{9, 7});
                mars_tiles[11][7] = new Tile(game, null, false, new Integer[]{11, 7});
                mars_tiles[13][7] = new Tile(game, new ArrayList<>(Arrays.asList("card", "card")), true, new Integer[]{13, 7});

                mars_tiles[2][6] = new Tile(game, new ArrayList<>(Collections.singletonList("card")), false, new Integer[]{2, 6}, true);
                mars_tiles[4][6] = new Tile(game, null, false, new Integer[]{4, 6});
                mars_tiles[6][6] = new Tile(game, null, false, new Integer[]{6, 6});
                mars_tiles[8][6] = new Tile(game, null, false, new Integer[]{8, 6});
                mars_tiles[10][6] = new Tile(game, null, false, new Integer[]{10, 6});
                mars_tiles[12][6] = new Tile(game, null, false, new Integer[]{12, 6});
                mars_tiles[14][6] = new Tile(game, new ArrayList<>(Collections.singletonList("steel")), false, new Integer[]{14, 6});

                mars_tiles[1][5] = new Tile(game, new ArrayList<>(Arrays.asList("titanium", "plant")), false, new Integer[]{1, 5}, true);
                mars_tiles[3][5] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{3, 5});
                mars_tiles[5][5] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{5, 5});
                mars_tiles[7][5] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{7, 5});
                mars_tiles[9][5] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "plant")), false, new Integer[]{9, 5});
                mars_tiles[11][5] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{11, 5});
                mars_tiles[13][5] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{13, 5});
                mars_tiles[15][5] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "plant")), true, new Integer[]{15, 5});

                mars_tiles[0][4] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "plant")), false, new Integer[]{0, 4}, true);
                mars_tiles[2][4] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "plant")), false, new Integer[]{2, 4});
                mars_tiles[4][4] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "plant")), false, new Integer[]{4, 4});
                mars_tiles[6][4] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "plant")), true, new Integer[]{6, 4});
                mars_tiles[8][4] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "plant")), true, new Integer[]{8, 4});
                mars_tiles[10][4] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "plant")), true, new Integer[]{10, 4});
                mars_tiles[12][4] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "plant")), false, new Integer[]{12, 4});
                mars_tiles[14][4] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "plant")), false, new Integer[]{14, 4});
                mars_tiles[16][4] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "plant")), false, new Integer[]{16, 4});

                mars_tiles[1][3] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{1, 3});
                mars_tiles[3][3] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "plant")), false, new Integer[]{3, 3});
                mars_tiles[5][3] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{5, 3});
                mars_tiles[7][3] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{7, 3});
                mars_tiles[9][3] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{9, 3});
                mars_tiles[11][3] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), true, new Integer[]{11, 3});
                mars_tiles[13][3] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), true, new Integer[]{13, 3});
                mars_tiles[15][3] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), true, new Integer[]{15, 3});

                mars_tiles[2][2] = new Tile(game, null, false, new Integer[]{2, 2});
                mars_tiles[4][2] = new Tile(game, null, false, new Integer[]{4, 2});
                mars_tiles[6][2] = new Tile(game, null, false, new Integer[]{6, 2});
                mars_tiles[8][2] = new Tile(game, null, false, new Integer[]{8, 2});
                mars_tiles[10][2] = new Tile(game, null, false, new Integer[]{10, 2});
                mars_tiles[12][2] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{12, 2});
                mars_tiles[14][2] = new Tile(game, null, false, new Integer[]{14, 2});

                mars_tiles[3][1] = new Tile(game, null, false, new Integer[]{3, 1});
                mars_tiles[5][1] = new Tile(game, null, false, new Integer[]{5, 1});
                mars_tiles[7][1] = new Tile(game, new ArrayList<>(Collections.singletonList("card")), false, new Integer[]{7, 1});
                mars_tiles[9][1] = new Tile(game, new ArrayList<>(Collections.singletonList("card")), false, new Integer[]{9, 1});
                mars_tiles[11][1] = new Tile(game, null, false, new Integer[]{11, 1});
                mars_tiles[13][1] = new Tile(game, new ArrayList<>(Collections.singletonList("titanium")), false, new Integer[]{13, 1});

                mars_tiles[4][0] = new Tile(game, null, false, new Integer[]{4, 0});
                mars_tiles[6][0] = new Tile(game, null, false, new Integer[]{6, 0});
                mars_tiles[8][0] = new Tile(game, null, false, new Integer[]{8, 0});
                mars_tiles[10][0] = new Tile(game, null, false, new Integer[]{10, 0});
                mars_tiles[12][0] = new Tile(game, new ArrayList<>(Arrays.asList("titanium", "titanium")), true, new Integer[]{12, 0});


            case 1:
                //Hellas
                mars_tiles[4][8] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "plant")), true, new Integer[]{4, 8});
                mars_tiles[5][8] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "plant")), false, new Integer[]{6, 8});
                mars_tiles[8][8] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "plant")), false, new Integer[]{8, 8});
                mars_tiles[10][8] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{10, 8});
                mars_tiles[12][8] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{12, 8});

                mars_tiles[3][7] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "plant")), true, new Integer[]{3, 7});
                mars_tiles[5][7] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "plant")), false, new Integer[]{5, 7});
                mars_tiles[7][7] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{7, 7});
                mars_tiles[9][7] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "steel")), false, new Integer[]{9, 7});
                mars_tiles[11][7] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{11, 7});
                mars_tiles[13][7] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{13, 7});

                mars_tiles[2][6] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), true, new Integer[]{2, 6});
                mars_tiles[4][6] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{4, 6});
                mars_tiles[6][6] = new Tile(game, new ArrayList<>(Collections.singletonList("steel")), false, new Integer[]{6, 6});
                mars_tiles[8][6] = new Tile(game, new ArrayList<>(Collections.singletonList("steel")), false, new Integer[]{8, 6});
                mars_tiles[10][6] = new Tile(game, null, false, new Integer[]{10, 6});
                mars_tiles[12][6] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "plant")), false, new Integer[]{12, 6});
                mars_tiles[14][6] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "titanium")), false, new Integer[]{14, 6});

                mars_tiles[1][5] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), true, new Integer[]{1, 5});
                mars_tiles[3][5] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{3, 5});
                mars_tiles[5][5] = new Tile(game, new ArrayList<>(Collections.singletonList("steel")), false, new Integer[]{5, 5});
                mars_tiles[7][5] = new Tile(game, new ArrayList<>(Arrays.asList("steel", "steel")), false, new Integer[]{7, 5});
                mars_tiles[9][5] = new Tile(game, new ArrayList<>(Collections.singletonList("steel")), false, new Integer[]{9, 5});
                mars_tiles[11][5] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), true, new Integer[]{11, 5});
                mars_tiles[13][5] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), true, new Integer[]{13, 5});
                mars_tiles[15][5] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{15, 5});

                mars_tiles[0][4] = new Tile(game, new ArrayList<>(Collections.singletonList("card")), false, new Integer[]{0, 4});
                mars_tiles[2][4] = new Tile(game, null, false, new Integer[]{2, 4});
                mars_tiles[4][4] = new Tile(game, null, false, new Integer[]{4, 4});
                mars_tiles[6][4] = new Tile(game, new ArrayList<>(Arrays.asList("steel", "steel")), false, new Integer[]{6, 4});
                mars_tiles[8][4] = new Tile(game, null, false, new Integer[]{8, 4});
                mars_tiles[10][4] = new Tile(game, new ArrayList<>(Collections.singletonList("card")), true, new Integer[]{10, 4});
                mars_tiles[12][4] = new Tile(game, new ArrayList<>(Arrays.asList("heat", "heat", "heat")), true, new Integer[]{12, 4});
                mars_tiles[14][4] = new Tile(game, null, true, new Integer[]{14, 4});
                mars_tiles[16][4] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{16, 4});

                mars_tiles[1][3] = new Tile(game, new ArrayList<>(Collections.singletonList("titanium")), false, new Integer[]{1, 3});
                mars_tiles[3][3] = new Tile(game, null, false, new Integer[]{3, 3});
                mars_tiles[5][3] = new Tile(game, new ArrayList<>(Collections.singletonList("steel")), false, new Integer[]{5, 3});
                mars_tiles[7][3] = new Tile(game, null, false, new Integer[]{7, 3});
                mars_tiles[9][3] = new Tile(game, null, false, new Integer[]{9, 3});
                mars_tiles[11][3] = new Tile(game, null, true, new Integer[]{11, 3});
                mars_tiles[13][3] = new Tile(game, new ArrayList<>(Collections.singletonList("steel")), true, new Integer[]{13, 3});
                mars_tiles[15][3] = new Tile(game, null, false, new Integer[]{15, 3});

                mars_tiles[2][2] = new Tile(game, new ArrayList<>(Arrays.asList("titanium", "titanium")), true, new Integer[]{2, 2});
                mars_tiles[4][2] = new Tile(game, null, false, new Integer[]{4, 2});
                mars_tiles[6][2] = new Tile(game, null, false, new Integer[]{6, 2});
                mars_tiles[8][2] = new Tile(game, new ArrayList<>(Collections.singletonList("card")), false, new Integer[]{8, 2});
                mars_tiles[10][2] = new Tile(game, null, false, new Integer[]{10, 2});
                mars_tiles[12][2] = new Tile(game, null, false, new Integer[]{12, 2});
                mars_tiles[14][2] = new Tile(game, new ArrayList<>(Collections.singletonList("titanium")), false, new Integer[]{14, 2});

                mars_tiles[3][1] = new Tile(game, new ArrayList<>(Collections.singletonList("steel")), false, new Integer[]{3, 1});
                mars_tiles[5][1] = new Tile(game, new ArrayList<>(Collections.singletonList("card")), false, new Integer[]{5, 1});
                mars_tiles[7][1] = new Tile(game, new ArrayList<>(Arrays.asList("heat", "heat")), false, new Integer[]{7, 1});
                mars_tiles[9][1] = new Tile(game, new ArrayList<>(Arrays.asList("heat", "heat")), false, new Integer[]{9, 1});
                mars_tiles[11][1] = new Tile(game, new ArrayList<>(Collections.singletonList("titanium")), false, new Integer[]{11, 1});
                mars_tiles[13][1] = new Tile(game, new ArrayList<>(Collections.singletonList("titanium")), false, new Integer[]{13, 1});

                mars_tiles[4][0] = new Tile(game, null, false, new Integer[]{4, 0});
                mars_tiles[6][0] = new Tile(game, new ArrayList<>(Arrays.asList("heat", "heat")), false, new Integer[]{6, 0});
                mars_tiles[8][0] = new Tile(game, new ArrayList<>(Collections.singletonList("ocean")), false, new Integer[]{8, 0});
                mars_tiles[10][0] = new Tile(game, new ArrayList<>(Arrays.asList("heat", "heat")), false, new Integer[]{10, 0});
                mars_tiles[12][0] = new Tile(game, new ArrayList<>(Arrays.asList("titanium", "titanium")), false, new Integer[]{12, 0});


            case 2:
                //Elysium
                mars_tiles[4][8] = new Tile(game, null, true, new Integer[]{4, 8});
                mars_tiles[5][8] = new Tile(game, new ArrayList<>(Collections.singletonList("titanium")), true, new Integer[]{6, 8});
                mars_tiles[8][8] = new Tile(game, new ArrayList<>(Collections.singletonList("card")), true, new Integer[]{8, 8});
                mars_tiles[10][8] = new Tile(game, new ArrayList<>(Collections.singletonList("steel")), true, new Integer[]{10, 8});
                mars_tiles[12][8] = new Tile(game, new ArrayList<>(Collections.singletonList("card")), false, new Integer[]{12, 8});

                mars_tiles[3][7] = new Tile(game, new ArrayList<>(Collections.singletonList("titanium")), false, new Integer[]{3, 7}, true);
                mars_tiles[5][7] = new Tile(game, null, false, new Integer[]{5, 7});
                mars_tiles[7][7] = new Tile(game, null, false, new Integer[]{7, 7});
                mars_tiles[9][7] = new Tile(game, null, true, new Integer[]{9, 7});
                mars_tiles[11][7] = new Tile(game, null, true, new Integer[]{11, 7});
                mars_tiles[13][7] = new Tile(game, new ArrayList<>(Arrays.asList("steel", "steel")), false, new Integer[]{13, 7});

                mars_tiles[2][6] = new Tile(game, new ArrayList<>(Arrays.asList("titanium", "titanium")), false, new Integer[]{2, 6}, true);
                mars_tiles[4][6] = new Tile(game, null, false, new Integer[]{4, 6});
                mars_tiles[6][6] = new Tile(game, new ArrayList<>(Collections.singletonList("card")), false, new Integer[]{6, 6});
                mars_tiles[8][6] = new Tile(game, null, false, new Integer[]{8, 6});
                mars_tiles[10][6] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), true, new Integer[]{10, 6});
                mars_tiles[12][6] = new Tile(game, null, true, new Integer[]{12, 6});
                mars_tiles[14][6] = new Tile(game, new ArrayList<>(Arrays.asList("card", "card", "card")), false, new Integer[]{14, 6}, true);

                mars_tiles[1][5] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{1, 5});
                mars_tiles[3][5] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{3, 5});
                mars_tiles[5][5] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{5, 5});
                mars_tiles[7][5] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "plant")), true, new Integer[]{7, 5});
                mars_tiles[9][5] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{9, 5});
                mars_tiles[11][5] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), true, new Integer[]{11, 5});
                mars_tiles[13][5] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), true, new Integer[]{13, 5});
                mars_tiles[15][5] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "steel")), false, new Integer[]{15, 5}, true);

                mars_tiles[0][4] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "plant")), false, new Integer[]{0, 4});
                mars_tiles[2][4] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "plant")), false, new Integer[]{2, 4});
                mars_tiles[4][4] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "plant")), false, new Integer[]{4, 4});
                mars_tiles[6][4] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "plant")), true, new Integer[]{6, 4});
                mars_tiles[8][4] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "plant")), false, new Integer[]{8, 4});
                mars_tiles[10][4] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "plant", "plant")), false, new Integer[]{10, 4});
                mars_tiles[12][4] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "plant")), false, new Integer[]{12, 4});
                mars_tiles[14][4] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "plant")), false, new Integer[]{14, 4});
                mars_tiles[16][4] = new Tile(game, new ArrayList<>(Arrays.asList("plant", "titanium")), false, new Integer[]{16, 4});

                mars_tiles[1][3] = new Tile(game, new ArrayList<>(Collections.singletonList("steel")), false, new Integer[]{1, 3});
                mars_tiles[3][3] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{3, 3});
                mars_tiles[5][3] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{5, 3});
                mars_tiles[7][3] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{7, 3});
                mars_tiles[9][3] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{9, 3});
                mars_tiles[11][3] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{11, 3});
                mars_tiles[13][3] = new Tile(game, new ArrayList<>(Collections.singletonList("plant")), false, new Integer[]{13, 3});
                mars_tiles[15][3] = new Tile(game, null, false, new Integer[]{15, 3});

                mars_tiles[2][2] = new Tile(game, new ArrayList<>(Collections.singletonList("titanium")), false, new Integer[]{2, 2});
                mars_tiles[4][2] = new Tile(game, new ArrayList<>(Collections.singletonList("steel")), false, new Integer[]{4, 2});
                mars_tiles[6][2] = new Tile(game, null, false, new Integer[]{6, 2});
                mars_tiles[8][2] = new Tile(game, null, false, new Integer[]{8, 2});
                mars_tiles[10][2] = new Tile(game, new ArrayList<>(Collections.singletonList("steel")), false, new Integer[]{10, 2});
                mars_tiles[12][2] = new Tile(game, null, false, new Integer[]{12, 2});
                mars_tiles[14][2] = new Tile(game, null, false, new Integer[]{14, 2});

                mars_tiles[3][1] = new Tile(game,  new ArrayList<>(Arrays.asList("steel", "steel")), false, new Integer[]{3, 1});
                mars_tiles[5][1] = new Tile(game, null, false, new Integer[]{5, 1});
                mars_tiles[7][1] = new Tile(game, null, false, new Integer[]{7, 1});
                mars_tiles[9][1] = new Tile(game, null, false, new Integer[]{9, 1});
                mars_tiles[11][1] = new Tile(game, new ArrayList<>(Arrays.asList("steel", "steel")), false, new Integer[]{11, 1});
                mars_tiles[13][1] = new Tile(game, null, false, new Integer[]{13, 1});

                mars_tiles[4][0] = new Tile(game, new ArrayList<>(Collections.singletonList("steel")), false, new Integer[]{4, 0});
                mars_tiles[6][0] = new Tile(game, null, false, new Integer[]{6, 0});
                mars_tiles[8][0] = new Tile(game, new ArrayList<>(Collections.singletonList("card")), false, new Integer[]{8, 0});
                mars_tiles[10][0] = new Tile(game, new ArrayList<>(Collections.singletonList("card")), false, new Integer[]{10, 0});
                mars_tiles[12][0] = new Tile(game, new ArrayList<>(Arrays.asList("steel", "steel")), false, new Integer[]{12, 0});
        }

        if (venus_in_game) {
            space_tiles = new Tile[7];
        } else {
            space_tiles = new Tile[3];
        }

        for (int i = 0; i < space_tiles.length; i++) {
            space_tiles[i] = new Tile(game);
        }
    }



    public Boolean placeGreenery(Player player) {
        Tile placing_tile = getCoordinatesFromPlayer("greenery");

        if (placing_tile.getIsOcean()) {
            return false;
        }
        if (placeTile(player, placing_tile, "greenery")) {
            game.raiseOxygen(player);
            return true;
        }
        return false;
    }

    public Boolean placeCity(Player player) {
        Tile placing_tile = getCoordinatesFromPlayer("city");

        for (Tile neighbour : getNeighbours(placing_tile)) {
            if (neighbour.getPlacedHex().equals("city") | neighbour.getPlacedHex().equals("capital")) {
                return false;
            }
        }
        if (placing_tile.getIsOcean()) {
            return false;
        }
        if (placeTile(player, placing_tile, "city")) {
            game.update_manager.onCityPlaced(player, true);
            return true;
        }
        return false;
    }

    public Boolean placeOcean(Player player) {
        Tile placing_tile = getCoordinatesFromPlayer("ocean");

        if (!placing_tile.getIsOcean() | game.getOceansPlaced() >= 9) {
            return false;
        }
        if (placeTile(player, placing_tile, "ocean")) {
            player.changeTerraformingRating(1);
            game.update_manager.onOceanPlaced(player);
            return true;
        }
        return false;
    }

    public Boolean placeLandOcean(Player player) {
        Tile placing_tile = getCoordinatesFromPlayer("ocean");

        if (placing_tile.getIsOcean()) {
            return false;
        }
        if (placeTile(player, placing_tile, "ocean")) {
            player.changeTerraformingRating(1);
            game.update_manager.onOceanPlaced(player);
            return true;
        }
        return false;
    }

    public Boolean placeOceanGreenery(Player player) {
        Tile placing_tile = getCoordinatesFromPlayer("greenery");

        if (placing_tile.getIsOcean()) {
            return false;
        }
        if (placeTile(player, placing_tile, "greenery")) {
            game.raiseOxygen(player);
            return true;
        }
        return false;
    }

    public Boolean placeNoctis(Player player) {
        Tile placing_tile = getCoordinatesFromPlayer("noctis");

        if (map == 0 && (placing_tile.getX() != 4 | placing_tile.getY() != 4)) {
            return false;
        } else {
            for (Tile neighbour : getNeighbours(placing_tile)) {
                if (neighbour.getPlacedHex().equals("city") | neighbour.getPlacedHex().equals("capital")) {
                    return false;
                }
            }
            if (placing_tile.getIsOcean()) {
                return false;
            }
        }
        if (placeTile(player, placing_tile, "city")) {
            game.update_manager.onCityPlaced(player, true);
            return true;
        }
        return false;
    }

    public Boolean placeLavaFlow(Player player) {
        Tile placing_tile = getCoordinatesFromPlayer("lava_flow");

        if (map != 1 && !placing_tile.getIsVolcanic()) {
            return false;
        }
        return placeTile(player, placing_tile, "lava_flow");
    }

    public Boolean placeCapital(Player player) {
        Tile placing_tile = getCoordinatesFromPlayer("capital");

        for (Tile neighbour : getNeighbours(placing_tile)) {
            if (neighbour.getPlacedHex().equals("city") | neighbour.getPlacedHex().equals("capital")) {
                return false;
            }
        }
        if (placing_tile.getIsOcean()) {
            return false;
        }

        if (placeTile(player, placing_tile, "capital")) {
            game.update_manager.onCityPlaced(player, true);
            return true;
        }
        return false;
    }

    public void placeGanymede(Player player) {
        if (space_tiles[0] != null) {
            //TODO error handling
            return;
        }
        game.update_manager.onCityPlaced(player, false);
        space_tiles[0].placeHex(player, "city");
    }

    public Boolean placeEcologicalZone(Player player) {
        Tile placing_tile = getCoordinatesFromPlayer("ecological_zone");
        boolean has_greenery_neighbour = false;

        for (Tile neighbour : getNeighbours(placing_tile)) {
            if (neighbour.getPlacedHex().equals("greenery")) {
                has_greenery_neighbour = true;
                break;
            }
        }
        if (placing_tile.getIsOcean() | !has_greenery_neighbour) {
            return false;
        }
        return placeTile(player, placing_tile, "ecological_zone");
    }

    public Boolean placeMiningRights(Player player) {
        Tile placing_tile = getCoordinatesFromPlayer("mining_area");

        if (placing_tile.getIsOcean()) {
            return false;
        }
        if (placing_tile.getPlacementBonuses().contains("steel")) {
            player.changeSteelProduction(1);
        } else if (placing_tile.getPlacementBonuses().contains("titanium")) {
            player.changeTitaniumProduction(1);
        } else {
            return false;
        }
        return placeTile(player, placing_tile, "mining_area");
    }

    public Boolean placeMohole(Player player) {
        Tile placing_tile = getCoordinatesFromPlayer("mohole");

        if (!placing_tile.getIsOcean()) {
            return false;
        }
        return placeTile(player, placing_tile, "mohole");
    }

    public Boolean placeNaturalReserve(Player player) {
        Tile placing_tile = getCoordinatesFromPlayer("natural_reserve");

        if (placing_tile.getIsOcean()) {
            return false;
        }

        for (Tile neighbour : getNeighbours(placing_tile)) {
            if (neighbour.getPlacedHex() != null) {
                return false;
            }
        }

        return placeTile(player, placing_tile, "natural_reserve");
    }

    public void placePhobos(Player player) {
        if (space_tiles[1] != null) {
            //TODO error handling
            return;
        }
        game.update_manager.onCityPlaced(player, false);
        space_tiles[1].placeHex(player, "city");
    }

    public Boolean placeResearchOutpost(Player player) {
        Tile placing_tile = getCoordinatesFromPlayer("city");

        if (placing_tile.getIsOcean()) {
            return false;
        }

        for (Tile neighbour : getNeighbours(placing_tile)) {
            if (neighbour.getPlacedHex() != null) {
                return false;
            }
        }

        game.update_manager.onCityPlaced(player, true);
        return placeTile(player, placing_tile, "city");
    }

    public Boolean placeUrbanizedArea(Player player) {
        Tile placing_tile = getCoordinatesFromPlayer("city");

        if (placing_tile.getIsOcean()) {
            return false;
        }

        int neighbour_cities = 0;
        for (Tile neighbour : getNeighbours(placing_tile)) {
            if (neighbour.getPlacedHex().equals("city")) {
                neighbour_cities++;
            }
        }
        if (neighbour_cities < 2) {
            return false;
        }

        game.update_manager.onCityPlaced(player, true);
        return placeTile(player, placing_tile, "city");
    }

    private Boolean placeTile(Player player, Tile to_place, String tile_type) {

        //Erikoiskäsittely peruskartan noctis-city -tiilelle
        if (map == 0 && to_place.getX() == 4 && to_place.getY() == 4) {
            if (tile_type.equals("noctis")) {
                tile_type = "city";
            } else {
                return false;
            }
        }
        if (to_place == null) {
            return false;
        }
        if (to_place.getPlacedHex() != null) {
            return false;
        }
        for (Tile neighbour : getNeighbours(to_place)) {
            if (neighbour != null) {
                if (neighbour.getPlacedHex().equals("ocean")) {
                    player.changeMoney(2 + player.getOceanAdjacencyBonusModifier());
                }
            }
        }
        to_place.placeHex(player, tile_type);
        return true;
    }

    private ArrayList<Tile> getNeighbours(Tile tile) {
        Integer x = tile.getX();
        Integer y = tile.getY();

        ArrayList<Tile> neighbours = new ArrayList<>();
        if (mars_tiles[x+2][y] != null) {
            neighbours.add(mars_tiles[x+2][y]);
        }
        if (mars_tiles[x-2][y] != null) {
            neighbours.add(mars_tiles[x-2][y]);
        }
        if (mars_tiles[x+1][y+1] != null) {
            neighbours.add(mars_tiles[x+1][y+1]);
        }
        if (mars_tiles[x-1][y+1] != null) {
            neighbours.add(mars_tiles[x-1][y+1]);
        }
        if (mars_tiles[x+1][y-1] != null) {
            neighbours.add(mars_tiles[x+1][y-1]);
        }
        if (mars_tiles[x-1][y-1] != null) {
            neighbours.add(mars_tiles[x-1][y-1]);
        }
        return neighbours;
    }

    private Tile getCoordinatesFromPlayer(String tile_type) {
        Tile tile = mars_tiles[8][4];
        //TODO tänne UI tiilen asettamispaikan saamiseksi
        return tile;
    }
}
