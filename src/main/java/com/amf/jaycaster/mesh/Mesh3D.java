package com.amf.jaycaster.mesh;

import java.nio.FloatBuffer;

public abstract class Mesh3D extends Mesh2D {
    
    public FloatBuffer normalData;
    
    public void setBuffers(FloatBuffer... buffers) {
        super.setBuffers(buffers);
        normalData = buffers[2];
    }
    
}