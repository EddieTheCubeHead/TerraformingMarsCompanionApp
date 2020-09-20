package com.example.terraformingmarscompanionapp.ui.main.tilemap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.opengl.GLES20;
import android.opengl.GLUtils;

import java.io.IOException;
import java.io.InputStream;


public class TileMapTexture {
    int textureId;
    public TileMapTexture(Context context)
    {
        // Texture object handle
        int[] textureId = new int[1];

        Bitmap flippedTexture;
        try {
            InputStream fileStream = context.getAssets().open("tree.png");
            Bitmap texture = BitmapFactory.decodeStream(fileStream);
            // Flip texture
            Matrix flipMatrix = new Matrix();
            flipMatrix.postScale(1, -1);
            flippedTexture = Bitmap.createBitmap(texture, 0, 0, texture.getWidth(), texture.getHeight(), flipMatrix, true);
        } catch (IOException error) {
            System.out.println("Unable to load texture file");
            System.exit(1076);
            return;
        }

        // Use tightly packed data
        GLES20.glPixelStorei ( GLES20.GL_UNPACK_ALIGNMENT, 1 );

        //  Generate a texture object
        GLES20.glGenTextures ( 1, textureId, 0 );

        // Bind the texture object
        GLES20.glBindTexture ( GLES20.GL_TEXTURE_2D, textureId[0] );

        //  Load the texture
        GLUtils.texImage2D( GLES20.GL_TEXTURE_2D, 0, flippedTexture, 0);

        // Set the filtering mode
        GLES20.glTexParameteri ( GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST );
        GLES20.glTexParameteri ( GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST );

        this.textureId = textureId[0];
    }

    public int getTextureId() {
        return textureId;
    }
}
