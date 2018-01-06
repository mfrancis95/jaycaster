package com.amf.jaycaster.mesh;

public abstract class Tile extends Mesh3D {
    
    public boolean solid;
    
    public int textureSize = 1, x, y;
    
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
}