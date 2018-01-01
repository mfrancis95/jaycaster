package com.amf.jaycaster.mesh;

import java.nio.FloatBuffer;

public class Background extends Mesh2D {
    
    public static final int BUFFER_SIZE = 12;
    public static final int NUM_VERTICES = 6;
    
    public void setBuffers(FloatBuffer... buffers) {
        super.setBuffers(buffers);
        setPositionData();
        setTextureData();
    }
    
    private void setPositionData() {
        positionData.put(-1).put(1);
        positionData.put(-1).put(-1);
        positionData.put(1).put(-1);
        
        positionData.put(1).put(1);
        positionData.put(-1).put(1);
        positionData.put(1).put(-1);
    }
    
    private void setTextureData() {
        textureData.put(0).put(1);
        textureData.put(0).put(0);
        textureData.put(1).put(0);
        
        textureData.put(1).put(1);
        textureData.put(0).put(1);
        textureData.put(1).put(0);         
    }
    
}