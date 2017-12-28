package com.amf.jaycaster.mesh;

import com.jogamp.opengl.util.texture.Texture;
import java.nio.FloatBuffer;

public abstract class Mesh2D {
    
    public FloatBuffer positionData, textureData;
    
    public Texture texture;
    
    public void setBuffers(FloatBuffer... buffers) {
        positionData = buffers[0];
        textureData = buffers[1];
    }
    
}