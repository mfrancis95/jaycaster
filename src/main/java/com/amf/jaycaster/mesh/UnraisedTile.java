package com.amf.jaycaster.mesh;

import java.nio.FloatBuffer;

public class UnraisedTile extends Tile {
    
    public final boolean ceiling, floor;
    
    public int ceilingTextureX, ceilingTextureY, floorTextureX, floorTextureY;
    
    public UnraisedTile(int x, int y, boolean ceiling, boolean floor) {
        super(x, y);
        this.ceiling = ceiling;
        this.floor = floor;
    } 
    
    private void createCeiling() {
        float y = 0.5f + height - 1;
        positionData.put(x - 0.5f).put(y).put(this.y + 0.5f);
        positionData.put(x - 0.5f).put(y).put(this.y - 0.5f);
        positionData.put(x + 0.5f).put(y).put(this.y - 0.5f);
        
        positionData.put(x + 0.5f).put(y).put(this.y + 0.5f);
        positionData.put(x - 0.5f).put(y).put(this.y + 0.5f);
        positionData.put(x + 0.5f).put(y).put(this.y - 0.5f);
        
        float width = texture.getWidth() / (float) (textureSize * texture.getWidth());
        float height = texture.getHeight() / (float) (textureSize * texture.getHeight());
        
        textureData.put(width * ceilingTextureX).put(height * ceilingTextureY);
        textureData.put(width * ceilingTextureX).put(height + height * ceilingTextureY);
        textureData.put(width + width * ceilingTextureX).put(height + height * ceilingTextureY);
        
        textureData.put(width + width * ceilingTextureX).put(height * ceilingTextureY);
        textureData.put(width * ceilingTextureX).put(height * ceilingTextureY);
        textureData.put(width + width * ceilingTextureX).put(height + height * ceilingTextureY);
        
        for (int i = 0; i < 6; i++) {
            normalData.put(0).put(-1).put(0);
        }
    }
    
    private void createFloor() {
        positionData.put(x - 0.5f).put(-0.5f).put(y - 0.5f);
        positionData.put(x - 0.5f).put(-0.5f).put(y + 0.5f);
        positionData.put(x + 0.5f).put(-0.5f).put(y + 0.5f);
        
        positionData.put(x + 0.5f).put(-0.5f).put(y - 0.5f);
        positionData.put(x - 0.5f).put(-0.5f).put(y - 0.5f);
        positionData.put(x + 0.5f).put(-0.5f).put(y + 0.5f);
        
        float width = texture.getWidth() / (float) (textureSize * texture.getWidth());
        float height = texture.getHeight() / (float) (textureSize * texture.getHeight());
        
        textureData.put(width * floorTextureX).put(height + height * floorTextureY);
        textureData.put(width * floorTextureX).put(height * floorTextureY);
        textureData.put(width + width * floorTextureX).put(height * floorTextureY);
        
        textureData.put(width + width * floorTextureX).put(height + height * floorTextureY);
        textureData.put(width * floorTextureX).put(height + height * floorTextureY);
        textureData.put(width + width * floorTextureX).put(height * floorTextureY);
        
        for (int i = 0; i < 6; i++) {
            normalData.put(0).put(1).put(0);
        }
    }
    
    public void setBuffers(FloatBuffer... buffers) {
        super.setBuffers(buffers);
        if (ceiling) {
            createCeiling();
        }
        if (floor) {
            createFloor();
        }
    }
    
    public void setCeilingTextureIndices(int x, int y) {
        ceilingTextureX = x;
        ceilingTextureY = y;
    }
    
    public void setFloorTextureIndices(int x, int y) {
        floorTextureX = x;
        floorTextureY = y;
    }
    
    public void setTextureIndices(int x, int y) {
        ceilingTextureX = floorTextureX = x;
        ceilingTextureY = floorTextureY = y;
    }
    
}