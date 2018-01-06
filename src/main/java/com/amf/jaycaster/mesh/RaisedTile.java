package com.amf.jaycaster.mesh;

import java.nio.FloatBuffer;

public class RaisedTile extends Tile {
    
    public static final int BUFFER_SIZE = 72;
    public static final int TEXTURE_BUFFER_SIZE = 48;
    
    public int textureX, textureY;
    
    public RaisedTile(int x, int y) {
        super(x, y);
    }
    
    private void createWallBack() {
        positionData.put(x - 0.5f).put(0.5f).put(y + 0.5f);
        positionData.put(x - 0.5f).put(-0.5f).put(y + 0.5f);
        positionData.put(x + 0.5f).put(-0.5f).put(y + 0.5f);
        
        positionData.put(x + 0.5f).put(0.5f).put(y + 0.5f);
        positionData.put(x - 0.5f).put(0.5f).put(y + 0.5f);
        positionData.put(x + 0.5f).put(-0.5f).put(y + 0.5f);
        
        float width = texture.getWidth() / (float) (textureSize * texture.getWidth());
        float height = texture.getHeight() / (float) (textureSize * texture.getHeight());
        
        textureData.put(width * textureX).put(height + height * textureY);
        textureData.put(width * textureX).put(height * textureY);
        textureData.put(width + width * textureX).put(height * textureY);
        
        textureData.put(width + width * textureX).put(height + height * textureY);
        textureData.put(width * textureX).put(height + height * textureY);
        textureData.put(width + width * textureX).put(height * textureY);
        
        for (int i = 0; i < 6; i++) {
            normalData.put(0).put(0).put(1);
        }
    }
    
    private void createWallFront() {
        positionData.put(x + 0.5f).put(0.5f).put(y - 0.5f);
        positionData.put(x + 0.5f).put(-0.5f).put(y - 0.5f);
        positionData.put(x - 0.5f).put(-0.5f).put(y - 0.5f);
        
        positionData.put(x - 0.5f).put(0.5f).put(y - 0.5f);
        positionData.put(x + 0.5f).put(0.5f).put(y - 0.5f);
        positionData.put(x - 0.5f).put(-0.5f).put(y - 0.5f);
        
        float width = texture.getWidth() / (float) (textureSize * texture.getWidth());
        float height = texture.getHeight() / (float) (textureSize * texture.getHeight());
        
        textureData.put(width + width * textureX).put(height + height * textureY);
        textureData.put(width + width * textureX).put(height * textureY);
        textureData.put(width * textureX).put(height * textureY);
        
        textureData.put(width * textureX).put(height + height * textureY);
        textureData.put(width + width * textureX).put(height + height * textureY);
        textureData.put(width * textureX).put(height * textureY);
        
        for (int i = 0; i < 6; i++) {
            normalData.put(0).put(0).put(-1);
        }
    }
    
    private void createWallLeft() {
        positionData.put(x - 0.5f).put(0.5f).put(y - 0.5f);
        positionData.put(x - 0.5f).put(-0.5f).put(y - 0.5f);
        positionData.put(x - 0.5f).put(-0.5f).put(y + 0.5f);
        
        positionData.put(x - 0.5f).put(0.5f).put(y + 0.5f);
        positionData.put(x - 0.5f).put(0.5f).put(y - 0.5f);
        positionData.put(x - 0.5f).put(-0.5f).put(y + 0.5f);
        
        float width = texture.getWidth() / (float) (textureSize * texture.getWidth());
        float height = texture.getHeight() / (float) (textureSize * texture.getHeight());
        
        textureData.put(width * textureX).put(height + height * textureY);
        textureData.put(width * textureX).put(height * textureY);
        textureData.put(width + width * textureX).put(height * textureY);
        
        textureData.put(width + width * textureX).put(height + height * textureY);
        textureData.put(width * textureX).put(height + height * textureY);
        textureData.put(width + width * textureX).put(height * textureY);
        
        for (int i = 0; i < 6; i++) {
            normalData.put(-1).put(0).put(0);
        }
    }
    
    private void createWallRight() {
        positionData.put(x + 0.5f).put(0.5f).put(y + 0.5f);
        positionData.put(x + 0.5f).put(-0.5f).put(y + 0.5f);
        positionData.put(x + 0.5f).put(-0.5f).put(y - 0.5f);
        
        positionData.put(x + 0.5f).put(0.5f).put(y - 0.5f);
        positionData.put(x + 0.5f).put(0.5f).put(y + 0.5f);
        positionData.put(x + 0.5f).put(-0.5f).put(y - 0.5f);
        
        float width = texture.getWidth() / (float) (textureSize * texture.getWidth());
        float height = texture.getHeight() / (float) (textureSize * texture.getHeight());
        
        textureData.put(width * textureX).put(height + height * textureY);
        textureData.put(width * textureX).put(height * textureY);
        textureData.put(width + width * textureX).put(height * textureY);
        
        textureData.put(width + width * textureX).put(height + height * textureY);
        textureData.put(width * textureX).put(height + height * textureY);
        textureData.put(width + width * textureX).put(height * textureY);
        
        for (int i = 0; i < 6; i++) {
            normalData.put(1).put(0).put(0);
        }
    }
    
    public void setBuffers(FloatBuffer... buffers) {
        super.setBuffers(buffers);
        createWallBack();
        createWallFront();
        createWallLeft();
        createWallRight();        
    }
    
    public void setTextureIndices(int x, int y) {
        textureX = x;
        textureY = y;
    }
    
}