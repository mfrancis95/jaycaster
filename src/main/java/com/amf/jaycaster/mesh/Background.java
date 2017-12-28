package com.amf.jaycaster.mesh;

import java.nio.FloatBuffer;

public class Background extends Mesh2D {
    
    public static final int BUFFER_SIZE = 12;
    
    public void setBuffers(FloatBuffer... buffers) {
        super.setBuffers(buffers);
        positionData.put(-1).put(1);
        positionData.put(-1).put(-1);
        positionData.put(1).put(-1);
        
        positionData.put(1).put(1);
        positionData.put(-1).put(1);
        positionData.put(1).put(-1);
        
        textureData.put(0).put(1);
        textureData.put(0).put(0);
        textureData.put(1).put(0);
        
        textureData.put(1).put(1);
        textureData.put(0).put(1);
        textureData.put(1).put(0);        
    }
    
}