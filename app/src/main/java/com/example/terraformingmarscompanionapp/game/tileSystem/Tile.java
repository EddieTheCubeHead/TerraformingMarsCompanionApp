package com.example.terraformingmarscompanionapp.game.tileSystem;

import android.content.Context;

import com.example.terraformingmarscompanionapp.cards.basegame.cards.MiningRights;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.MiningArea;
import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.PromptEvent;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class that represents a hex tile on the map. Contains information of the placement bonuses and
 * placed hex on the tile.
 *
 * @author Eetu Asikainen
 * @version 0.3
 * @since 0.2
 */
public class Tile implements Serializable {
    private Boolean is_ocean = false;
    private ArrayList<PlacementBonus> placement_bonuses = new ArrayList<>();
    private Integer[] coordinates = new Integer[2];
    private Placeable placed_hex = null;
    private Boolean is_volcanic = false;
    private Player owner = null;

    /**
     * Default constructor
     *
     * @param tile_placement_bonuses {@link ArrayList} of {@link PlacementBonus} the tile has.
     * @param tile_is_ocean {@link Boolean} whether the tile is ocean tile or not
     * @param tile_coordinates {@link java.lang.reflect.Array} of two {@link Integer} representing the x and y coordinate of the tile.
     */
    Tile(ArrayList<PlacementBonus> tile_placement_bonuses, Boolean tile_is_ocean, Integer[] tile_coordinates) {
        placement_bonuses = tile_placement_bonuses;
        coordinates = tile_coordinates;
        is_ocean = tile_is_ocean;
    }

    /**
     * Custom constructor for creating volcanic tiles
     *
     * @param tile_placement_bonuses {@link ArrayList} of {@link PlacementBonus} the tile has.
     * @param tile_is_ocean {@link Boolean} whether the tile is ocean tile or not
     * @param tile_coordinates {@link java.lang.reflect.Array} of two {@link Integer} representing the x and y coordinate of the tile.
     * @param tile_is_volcanic {@link Boolean}
     */
    Tile (ArrayList<PlacementBonus> tile_placement_bonuses, Boolean tile_is_ocean, Integer[] tile_coordinates, Boolean tile_is_volcanic) {
        placement_bonuses = tile_placement_bonuses;
        coordinates = tile_coordinates;
        is_ocean = tile_is_ocean;
        is_volcanic = tile_is_volcanic;
    }

    /**
     * Custom constructor for creating space tiles. Space tiles have no coordinates or placement bonuses
     * and are stored separately in a one-dimensional array instead of a two-dimensional one
     */
    Tile() {
        coordinates[0] = null;
        coordinates[1] = null;
    }

    /**
     * @return {@link Integer} the x-coordinate of the tile
     */
    Integer getX() {
        return coordinates[0];
    }

    // Because of polar explorer this actually needs larger scope than getX()
    /**
     * @return {@link Integer} the y-coordinate of the tile
     */
    public Integer getY() {
        return coordinates[1];
    }

    /**
     * @return {@link Boolean} whether the tile is an ocean tile
     */
    Boolean getIsOcean() {
        return is_ocean;
    }

    /**
     * @return {@link Boolean} whether the tile is a volcanic tile
     */
    Boolean getIsVolcanic() {
        return is_volcanic;
    }

    /**
     * @return {@link Placeable} the placeable on the tile. Returns null if there is no placeable
     */
    public Placeable getPlacedHex() {
        return placed_hex;
    }

    /**
     * @return {@link Player} the player who owns the placeable on the tile. Returns null if there is no placeable
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * @return {@link ArrayList} of {@link PlacementBonus} that contains all placement bonuses of the tile. Can be empty but never null.
     */
    public ArrayList<PlacementBonus> getPlacementBonuses() {
        return placement_bonuses;
    }

    /**
     * A method to place a placeable in the tile. Applies the placement bonuses to the given player.
     *
     * @param player {@link Player} placing the placeable
     * @param hex_type {@link Placeable} that is being placed
     * @param context {@link Context} the UI context for the given action. Needed to generate a prompt for drawing cards.
     */
    public void placeHex(Player player, Placeable hex_type, Context context) throws InvalidResourcesException {

        Game game = GameController.getGame();

        if (placed_hex != null) {
            return;
        }
        placed_hex = hex_type;
        if (placement_bonuses != null) {
            for (PlacementBonus bonus : placement_bonuses) {
                switch (bonus) {
                    case STEEL:
                        player.getResources().setSteel(player.getResources().getSteel() + 1);
                        break;

                    case TITANIUM:
                        player.getResources().setTitanium(player.getResources().getTitanium() + 1);
                        break;

                    case PLANT:
                        player.getResources().setPlants(player.getResources().getPlants() + 1);
                        break;

                    case HEAT:
                        player.getResources().setHeat(player.getResources().getHeat() + 1);
                        break;

                    case OCEAN:
                        if (player.getResources().getMoney() < 6) {
                            player.getResources().setMoney(player.getResources().getMoney() - 6);
                            game.tile_handler.getCoordinatesFromPlayer(Placeable.OCEAN, context);
                        }

                    case CARD:
                        EventScheduler.addEvent(new PromptEvent("Please draw a card"));
                        player.changeHandSize(1);
                }
            }
            if (placement_bonuses.contains(PlacementBonus.STEEL)) {
                game.update_manager.onPlacementBonus(player);

                // Robotic workforce creates unique challenges for playing mining area and/or mining
                // rights. This atrocity works around that problem and enables saving the production
                // type gained into the production box of the card.
                if (hex_type == Placeable.MINING_AREA && game.modifiers.getCorporateEra()) {
                    ((MiningArea)game.getDeck().get("Mining area")).setPlacementBonus(true);
                } else if (hex_type == Placeable.MINING_RIGHTS) {
                    ((MiningRights)game.getDeck().get("Mining rights")).setPlacementBonus(true);
                }

            } else if (placement_bonuses.contains(PlacementBonus.TITANIUM)) {
                game.update_manager.onPlacementBonus(player);

                // Again for robotic workforce
                if (hex_type == Placeable.MINING_AREA && game.modifiers.getCorporateEra()) {
                    ((MiningArea)game.getDeck().get("Mining area")).setPlacementBonus(false);
                } else if (hex_type == Placeable.MINING_RIGHTS) {
                    ((MiningRights)game.getDeck().get("Mining rights")).setPlacementBonus(false);
                }
            }
        }
        owner = player;
    }
}
