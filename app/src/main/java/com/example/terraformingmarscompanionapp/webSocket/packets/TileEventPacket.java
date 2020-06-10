package com.example.terraformingmarscompanionapp.webSocket.packets;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;
import com.example.terraformingmarscompanionapp.game.tileSystem.PlacementBonus;

/**
 * Event representing playing a card
 */
public class TileEventPacket implements ServerPacket {
    private Placeable tile_type;
    private String player_name;
    private Integer x_coord;
    private Integer y_coord;

    public TileEventPacket(Placeable tile_type, String player_name, Integer x_coord, Integer y_coord) {
        this.tile_type = tile_type;
        this.player_name = player_name;
        this.x_coord = x_coord;
        this.y_coord = y_coord;
    }

    @Override
    public void playPacket() {
        Game game = GameController.getGame();
        game.tile_handler.placeTile(GameController.getPlayer(player_name), game.tile_handler.getTile(x_coord, y_coord), tile_type);
        Player player = GameController.getPlayer(player_name);
        switch (tile_type) {
            case OCEAN:
                player.getResources().setTerraformingRating(player.getResources().getTerraformingRating() + 1);
                break;
            case GREENERY:
                game.raiseOxygen(GameController.getPlayer(player_name));
                break;
            case MINING_AREA:
                if (game.tile_handler.getTile(x_coord, y_coord).getPlacementBonuses().contains(PlacementBonus.TITANIUM)) {
                    player.getResources().setTitaniumProduction(player.getResources().getTitaniumProduction() + 1);
                } else {
                    player.getResources().setSteelProduction(player.getResources().getSteelProduction() + 1);
                }
            default:
                break;
        }
    }
}
