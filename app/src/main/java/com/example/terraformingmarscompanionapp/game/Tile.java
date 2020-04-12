package com.example.terraformingmarscompanionapp.game;

import java.util.ArrayList;

class Tile {
    private final Game game;
    private Boolean is_ocean = false;
    private ArrayList<String> placement_bonuses = new ArrayList<>();
    private Integer[] coordinates = new Integer[2];
    private String placed_hex = null;
    private Boolean is_volcanic = false;
    private Player owner = null;

    Integer getX() {return coordinates[0];}
    Integer getY() {return coordinates[1];}
    Boolean getIsOcean() {return is_ocean;}
    Boolean getIsVolcanic() {return is_volcanic;}
    String getPlacedHex() {return placed_hex;}
    ArrayList<String> getPlacementBonuses() {return placement_bonuses;}

    Tile(Game tile_game, ArrayList<String> tile_placement_bonuses, Boolean tile_is_ocean, Integer[] tile_coordinates) {
        placement_bonuses = tile_placement_bonuses;
        coordinates = tile_coordinates;
        is_ocean = tile_is_ocean;
        game = tile_game;
    }

    Tile(Game tile_game, ArrayList<String> tile_placement_bonuses, Boolean tile_is_ocean, Integer[] tile_coordinates, Boolean tile_is_volcanic) {
        placement_bonuses = tile_placement_bonuses;
        coordinates = tile_coordinates;
        is_ocean = tile_is_ocean;
        game = tile_game;
        is_volcanic = tile_is_volcanic;
    }

    Tile(Game tile_game) {
        game = tile_game;
        coordinates[0] = null;
        coordinates[1] = null;
    }


    void placeHex(Player player, String hex_type) {
        if (placed_hex != null) {
            return;
        }
        placed_hex = hex_type;
        if (placement_bonuses != null) {
            for (String bonus : placement_bonuses) {
                switch (bonus) {
                    case "steel":
                        player.changeSteel(1);
                        game.update_manager.onPlacementBonus(player);
                        break;
                    case "titanium":
                        player.changeTitanium(1);
                        game.update_manager.onPlacementBonus(player);
                        break;
                    case "plant":
                        player.changePlants(1);
                        break;
                    case "heat":
                        player.changeHeat(1);
                        break;
                    case "ocean":
                        if (player.changeMoney(-6)) {
                            game.tile_handler.placeOcean(player);
                        }
                    case "card":
                        //TODO UI prompt -ota kortti
                }
            }
        }
        owner = player;
    }
}
