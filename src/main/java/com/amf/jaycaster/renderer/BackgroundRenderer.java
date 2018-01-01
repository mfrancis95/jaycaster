package com.amf.jaycaster.renderer;

import com.amf.jaycaster.mesh.Background;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.util.GLBuffers;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class BackgroundRenderer extends Renderer {
    
    public void init(GL4 gl) {
        super.init(gl);
        
        FloatBuffer positionData = GLBuffers.newDirectFloatBuffer(Background.BUFFER_SIZE);
        FloatBuffer textureData = GLBuffers.newDirectFloatBuffer(Background.BUFFER_SIZE);
        
        Background background = new Background();
        background.setBuffers(positionData, textureData);
        
        IntBuffer buffers = GLBuffers.newDirectIntBuffer(2);
        gl.glGenBuffers(2, buffers);
        
        gl.glEnableVertexAttribArray(0);
        gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, buffers.get(0));
        gl.glVertexAttribPointer(0, 2, GL4.GL_FLOAT, false, 0, 0);
        gl.glBufferData(GL4.GL_ARRAY_BUFFER, 4 * Background.BUFFER_SIZE, positionData.flip(), GL4.GL_STATIC_DRAW);
        
        gl.glEnableVertexAttribArray(1);
        gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, buffers.get(1));
        gl.glVertexAttribPointer(1, 2, GL4.GL_FLOAT, false, 0, 0);
        gl.glBufferData(GL4.GL_ARRAY_BUFFER, 4 * Background.BUFFER_SIZE, textureData.flip(), GL4.GL_STATIC_DRAW);
    }
    
    public void render(GL4 gl) {
        gl.glDisable(GL4.GL_DEPTH_TEST);
        texture.bind(gl);
        gl.glBindVertexArray(vertexArray);
        gl.glUseProgram(program);
        gl.glDrawArrays(GL4.GL_TRIANGLES, 0, Background.NUM_VERTICES);
        gl.glEnable(GL4.GL_DEPTH_TEST);
    }
    
}