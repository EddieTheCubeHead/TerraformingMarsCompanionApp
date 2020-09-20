package com.example.terraformingmarscompanionapp.ui.main.tilemap;
import android.opengl.GLES20;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

class TileMapRenderHex extends TileMapRenderObject {

    // https://stackoverflow.com/questions/28356619/opengl-es-2-0-hexagon
    private static final float hexCoords[] = {
            0.0f,   0.0f,   0.0f, // center
            0.0f,   0.5f,   0.0f, // top
            -0.443f,  0.25f, 0.0f, // top left
            -0.443f,  -0.25f, 0.0f, // bottom left
            0.0f,   -0.5f,   0.0f, // bottom
            0.443f, -0.25f, 0.0f, // bottom right
            0.443f, 0.25f, 0.0f };  // top right
    private static final short hexDrawOrder[] = {0,1,2,2,3,0,0,3,4,4,5,0,0,5,6,6,1,0};
    private static final float hexTexCoords[] = {
            0.5f,   0.5f, // center
            0.5f,   1.0f, // top
            0.057f,  0.75f, // top left
            0.057f,  0.25f, // bottom left
            0.5f,   0.0f, // bottom
            0.943f, 0.25f, // bottom right
            0.943f, 0.75f, };  // top right
    private float hexColor[] = {0f, 1f, 0f, 1.0f};


    public TileMapRenderHex(float x, float y) {
        super(hexCoords, hexDrawOrder, hexTexCoords, x, y);
        scaleX = 0.1f;
        scaleY = 0.1f;
        color = hexColor;
    }

    public TileMapRenderHex(float x, float y, float scaleX, float scaleY) {
        super(hexCoords, hexDrawOrder, hexTexCoords, x, y, scaleX, scaleY);
        color = hexColor;
    }
}