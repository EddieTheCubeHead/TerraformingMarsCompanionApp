package com.example.terraformingmarscompanionapp.ui.main.tilemap;

public class TileMapRenderSquare extends TileMapRenderObject {
    // https://stackoverflow.com/questions/28356619/opengl-es-2-0-hexagon
    private static final float squareCoords[] = {
            -1.0f,   1.0f,   0.0f, // top left
            1.0f,   1.0f,   0.0f, // top right
            1.0f,  -1.0f, 0.0f, // bottom right
            -1.0f,  -1.0f, 0.0f, }; // bottom left
    private static final short squareDrawOrder[] = {0,1,2,0,2,3};
    private static final float squareTexCoords[] = {
            0.0f,   1.0f, // top left
            1.0f,   1.0f, // top right
            1.0f,   0.0f, // bottom right
            0.0f,   0.0f,}; // bottom left
    private float squareColor[] = {0f, 0f, 1f, 1.0f};


    public TileMapRenderSquare(float x, float y) {
        super(squareCoords, squareDrawOrder, squareTexCoords, x, y);
        scaleX = 1.0f;
        scaleY = 1.0f;
        color = squareColor;
    }
}
