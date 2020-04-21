package com.example.terraformingmarscompanionapp.webSocket.events;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public class TileEventPacket implements PlayablePacket{
    private Placeable tile_type;
    private String player_name;
    private Integer x_coord;
    private Integer y_coord;

    public Placeable getTileType() {return tile_type;}
    public Integer getXCoord() {return x_coord;}
    public Integer getYCoord() {return y_coord;}

    public TileEventPacket(Placeable tile_type, String player_name, Integer x_coord, Integer y_coord) {
        this.tile_type = tile_type;
        this.player_name = player_name;
        this.x_coord = x_coord;
        this.y_coord = y_coord;
    }

    @Override
    public void playPacket() {
        Game game = GameController.getInstance().getGame();
        game.tile_handler.placeTile(game.getPlayer(player_name), game.tile_handler.getTile(x_coord, y_coord), tile_type);
    }
}
