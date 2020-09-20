package com.example.terraformingmarscompanionapp.ui.main.tilemap;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;


class TileMapRender implements GLSurfaceView.Renderer {

    // vPMatrix is an abbreviation for "Model View Projection Matrix"
    private final float[] vPMatrix = new float[16];
    private final float[] projectionMatrix = new float[16];
    private final float[] viewMatrix = new float[16];
    private float ratio;

    private Context context;

    private TileMapRenderHex mHex;
    private TileMapRenderHex mHex2;
    private TileMapRenderHex mHex3;
    private TileMapRenderSquare mSquare;
    private TileMapTexture mTexture;

    public TileMapRender(Context context) {
        this.context = context;
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // Set the background frame color
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        mHex = new TileMapRenderHex(0,0, 1, 1);
        mHex2 = new TileMapRenderHex(-0.5f,-0.5f);
        mHex3 = new TileMapRenderHex(0.5f,1.0f);
        mSquare = new TileMapRenderSquare(0.0f,0.0f);
        mTexture = new TileMapTexture(context);
        mHex.attachTexture(mTexture);
        mHex2.attachTexture(mTexture);

    }

    public void onDrawFrame(GL10 unused) {
        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        // Set the camera position (View matrix)
        // Move camera down to get map view up
        // Leave some space on top
        Matrix.setLookAtM(viewMatrix, 0, 0, 0, -4, 0f, -ratio * 1.5f, 0f, 0f, 1.0f, 0.0f);

        // Calculate the projection and view transformation
        Matrix.multiplyMM(vPMatrix, 0, projectionMatrix, 0, viewMatrix, 0);

        mSquare.draw(vPMatrix);
        mHex.draw(vPMatrix);
        mHex2.draw(vPMatrix);
        mHex3.draw(vPMatrix);
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        ratio = (float) width / height;

        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method
        // Matrix.orthoM(projectionMatrix, 0, -ratio, ratio, -1, 1, 1, 10);
        // Orthogonal view is not in a normal fashion. The reason for this is that it is easier to draw
        // When coordinates are in sync with the width (-1 - 1) instead of a height.
        // If landscape support is added matrix above should be used instead
        Matrix.orthoM(projectionMatrix, 0, -1, 1, -1 / ratio, 1 / ratio, 1, 10);

        // Scale for simpler coordinate system
        // Matrix.scaleM(projectionMatrix, 0, ratio, ratio, 0);

        // Move camera down to get actual draw area up
        // Matrix.translateM(projectionMatrix, 0, 0, -ratio, 0);
    }

    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

}
