package com.example.terraformingmarscompanionapp.game.tileSystem;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.PromptEvent;

import java.util.ArrayList;

//Luokka heksan ja sillä mahdollisesti olevan tiilen esittämiseen
public class Tile {
    private final Game game;
    private Boolean is_ocean = false;
    private ArrayList<PlacementBonus> placement_bonuses = new ArrayList<>();
    private Integer[] coordinates = new Integer[2];
    private Placeable placed_hex = null;
    private Boolean is_volcanic = false;
    private Player owner = null;

    public Integer getX() {return coordinates[0];}
    public Integer getY() {return coordinates[1];}
    Boolean getIsOcean() {return is_ocean;}
    Boolean getIsVolcanic() {return is_volcanic;}
    public Placeable getPlacedHex() {return placed_hex;}
    public Player getOwner() {return owner;}
    public ArrayList<PlacementBonus> getPlacementBonuses() {return placement_bonuses;}

    //Rakentajat
    Tile(Game tile_game, ArrayList<PlacementBonus> tile_placement_bonuses, Boolean tile_is_ocean, Integer[] tile_coordinates) {
        placement_bonuses = tile_placement_bonuses;
        coordinates = tile_coordinates;
        is_ocean = tile_is_ocean;
        game = tile_game;
    }

    Tile(Game tile_game, ArrayList<PlacementBonus> tile_placement_bonuses, Boolean tile_is_ocean, Integer[] tile_coordinates, Boolean tile_is_volcanic) {
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

    //Heksan asettaminen ko. tiileen
    void placeHex(Player player, Placeable hex_type) {
        if (placed_hex != null) {
            return;
        }
        placed_hex = hex_type;
        if (placement_bonuses != null) {
            for (PlacementBonus bonus : placement_bonuses) {
                switch (bonus) {
                    case STEEL:
                        player.changeSteel(1);
                        break;
                    case TITANIUM:
                        player.changeTitanium(1);
                        break;
                    case PLANT:
                        player.changePlants(1);
                        break;
                    case HEAT:
                        player.changeHeat(1);
                        break;
                    case OCEAN:
                        if (player.changeMoney(-6)) {
                            game.tile_handler.getCoordinatesFromPlayer(Placeable.OCEAN);
                        }
                    case CARD:
                        GameController.getInstance().addUiEvent(new PromptEvent("Please draw a card"));
                        player.changeHandSize(1);
                }
            }
            if (placement_bonuses.contains(PlacementBonus.STEEL)) {
                game.update_manager.onPlacementBonus(player);
                if (hex_type == Placeable.MINING_AREA || hex_type == Placeable.MINING_RIGHTS) {
                    player.changeSteelProduction(1);
                }
            } else if (placement_bonuses.contains(PlacementBonus.TITANIUM)) {
                game.update_manager.onPlacementBonus(player);
                if (hex_type == Placeable.MINING_AREA || hex_type == Placeable.MINING_RIGHTS) {
                    player.changeTitaniumProduction(1);
                }
            }
        }
        owner = player;
    }
}
