package com.example.terraformingmarscompanionapp.webSocket.events;

public class TileEventPacket {
    private String tile_type;
    private Integer x_coord;
    private Integer y_coord;

    public String getTileType() {return tile_type;}
    public Integer getXCoord() {return x_coord;}
    public Integer getYCoord() {return y_coord;}

    public TileEventPacket(String tile_type, Integer x_coord, Integer y_coord) {
        this.tile_type = tile_type;
        this.x_coord = x_coord;
        this.y_coord = y_coord;
    }
}
