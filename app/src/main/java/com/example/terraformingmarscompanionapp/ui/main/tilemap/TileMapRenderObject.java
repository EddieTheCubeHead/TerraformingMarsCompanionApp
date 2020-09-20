package com.example.terraformingmarscompanionapp.ui.main.tilemap;

import android.opengl.GLES20;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public abstract class TileMapRenderObject {
    private FloatBuffer vertexBuffer;
    private FloatBuffer textureCoordinateBuffer;
    private ShortBuffer drawListBuffer;

    private final String vertexShaderCode =
            // This matrix member variable provides a hook to manipulate
            // the coordinates of the objects that use this vertex shader
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "attribute vec2 vTexture;" +
                    "varying vec2 vTexCoord;" +
                    "void main() {" +
                    // the matrix must be included as a modifier of gl_Position
                    // Note that the uMVPMatrix factor *must be first* in order
                    // for the matrix multiplication product to be correct.
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "  vTexCoord = vTexture;" +
                    "}";

    // Use to access and set the view transformation
    private int vPMatrixHandle;


    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    private final String textureShaderCode =
            "precision mediump float;" +
                    "varying vec2 vTexCoord;" +
                    "uniform sampler2D sTexture;" +
                    "void main() {" +
                    "  gl_FragColor = texture2D(sTexture, vTexCoord);" +
                    "}";

    private int mProgram;

    // Set color with red, green, blue and alpha (opacity) values
    protected float color[];
    protected float x;
    protected float y;
    protected float scaleX = 1;
    protected float scaleY = 1;
    private float coords[];
    private short drawOrder[];
    private float texCoords[];
    private TileMapTexture texture;


    public TileMapRenderObject(float coords[], short drawOrder[], float texCoords[], float x, float y, float scaleX, float scaleY) {
        this.coords = coords;
        this.drawOrder = drawOrder;
        this.texCoords = texCoords;
        this.x = x;
        this.y = y;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        init();
    }

    public TileMapRenderObject(float coords[], short drawOrder[], float texCoords[], float x, float y) {
        this.coords = coords;
        this.drawOrder = drawOrder;
        this.texCoords = texCoords;
        this.x = x;
        this.y = y;
        init();
    }

    private void init() {
        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (number of coordinate values * 4 bytes per float)
                coords.length * 4);
        // use the device hardware's native byte order
        bb.order(ByteOrder.nativeOrder());

        // create a floating point buffer from the ByteBuffer
        vertexBuffer = bb.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        vertexBuffer.put(coords);
        // set the buffer to read the first coordinate
        vertexBuffer.position(0);

        // Create texture coordinate buffer
        ByteBuffer tbb = ByteBuffer.allocateDirect(
                texCoords.length * 4);
        tbb.order(ByteOrder.nativeOrder());
        textureCoordinateBuffer = tbb.asFloatBuffer();
        textureCoordinateBuffer.put(texCoords);
        textureCoordinateBuffer.position(0);

        // initialize byte buffer for the draw list
        ByteBuffer dlb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 2 bytes per short)
                drawOrder.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);

        int vertexShader = TileMapRender.loadShader(GLES20.GL_VERTEX_SHADER,
                vertexShaderCode);

        // create empty OpenGL ES Program
        mProgram = GLES20.glCreateProgram();

        // add the vertex shader to program
        GLES20.glAttachShader(mProgram, vertexShader);

        if (texture != null) {
            // add the texture shader to program
            int textureShader = TileMapRender.loadShader(GLES20.GL_FRAGMENT_SHADER,
                    textureShaderCode);
            GLES20.glAttachShader(mProgram, textureShader);
        } else {
            // add the fragment shader to program
            int fragmentShader = TileMapRender.loadShader(GLES20.GL_FRAGMENT_SHADER,
                    fragmentShaderCode);
            GLES20.glAttachShader(mProgram, fragmentShader);
        }

        // creates OpenGL ES program executables
        GLES20.glLinkProgram(mProgram);
    }

    public void attachTexture(TileMapTexture texture) {
        this.texture = texture;
        init();
    }

    private int colorHandle;

    // number of coordinates per vertex in this array
    private final int COORDS_PER_VERTEX = 3;
    private final int COORDS_PER_TEXTURE = 2;
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex
    private final int textureStride = COORDS_PER_TEXTURE * 4;

    public void draw(float[] mvpMatrix) {
        // Apply extra position matrix
        float[] move = new float[16];
        Matrix.setIdentityM(move, 0);
        Matrix.translateM(move, 0, x, y, 0f);
        Matrix.scaleM(move, 0, scaleX, scaleY, 0);
        float[] scratch = new float[16];
        Matrix.multiplyMM(scratch, 0, mvpMatrix, 0, move, 0);

        // Add program to OpenGL ES environment
        GLES20.glUseProgram(mProgram);

        // get handle to vertex shader's vPosition member
        int vPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(vPositionHandle);

        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(vPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);

        if (texture != null) {
            // get handle to vertex shader's vTexture member
            int vTextureHandle = GLES20.glGetAttribLocation(mProgram, "vTexture");
            // Enable a handle to the texture coordinates
            GLES20.glEnableVertexAttribArray(vTextureHandle);
            // Load the texture coordinate
            GLES20.glVertexAttribPointer ( vTextureHandle, COORDS_PER_TEXTURE, GLES20.GL_FLOAT,
                    false,
                    textureStride,
                    textureCoordinateBuffer );
            // Bind the texture
            GLES20.glActiveTexture ( GLES20.GL_TEXTURE0 );
            GLES20.glBindTexture ( GLES20.GL_TEXTURE_2D, texture.getTextureId() );

            // Prepare sampler
            int mSamplerLoc = GLES20.glGetUniformLocation ( mProgram, "sTexture" );
            GLES20.glUniform1i ( mSamplerLoc, 0 );
        } else {
            // get handle to fragment shader's vColor member
            colorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");

            // Set color for drawing the hexagon
            GLES20.glUniform4fv(colorHandle, 1, color, 0);
        }

        // get handle to shape's transformation matrix
        vPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");

        // Pass the projection and view transformation to the shader
        GLES20.glUniformMatrix4fv(vPMatrixHandle, 1, false, scratch, 0);

        // Draw the hexagon
        GLES20.glDrawElements(
                GLES20.GL_TRIANGLES, drawOrder.length,
                GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(vPositionHandle);
    }
}
