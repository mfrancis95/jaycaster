package com.amf.jaycaster.renderer;

import com.amf.jaycaster.core.Camera;
import com.amf.jaycaster.core.Light;
import com.amf.jaycaster.mesh.RaisedTile;
import com.amf.jaycaster.mesh.Tile;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.util.GLBuffers;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class TileRenderer extends Renderer {
    
    private final float aspectRatio;
    
    private int bufferSize, numVertices;
    
    public Camera camera;
    
    private int cameraDirection, cameraPosition;
    
    public Light light;
    
    private final int height;
    
    private int lightColor, lightDirection;
    
    private int projectionAspectRatio, projectionFar, projectionFOV, projectionNear;
    
    public Tile[] tiles;
    
    public TileRenderer(float aspectRatio, int height) {
        this.aspectRatio = aspectRatio;
        this.height = height;
    }
    
    public void init(GL4 gl) {
        super.init(gl);
        initBufferData(gl);
        initUniforms(gl);
    }
    
    private void initBufferData(GL4 gl) {
        FloatBuffer positionData = GLBuffers.newDirectFloatBuffer(height * tiles.length * RaisedTile.BUFFER_SIZE);
        FloatBuffer textureData = GLBuffers.newDirectFloatBuffer(height * tiles.length * RaisedTile.TEXTURE_BUFFER_SIZE);
        FloatBuffer normalData = GLBuffers.newDirectFloatBuffer(height * tiles.length * RaisedTile.BUFFER_SIZE);
        for (Tile tile : tiles) {
            tile.height = height;
            tile.texture = texture;
            tile.setBuffers(positionData, textureData, normalData);
        }
        
        IntBuffer buffers = GLBuffers.newDirectIntBuffer(3);
        gl.glGenBuffers(3, buffers);
        
        gl.glEnableVertexAttribArray(0);
        gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, buffers.get(0));
        gl.glVertexAttribPointer(0, 3, GL4.GL_FLOAT, false, 0, 0);
        gl.glBufferData(GL4.GL_ARRAY_BUFFER, 4 * (bufferSize = positionData.position()), positionData.flip(), GL4.GL_STATIC_DRAW);
        
        gl.glEnableVertexAttribArray(1);
        gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, buffers.get(1));
        gl.glVertexAttribPointer(1, 2, GL4.GL_FLOAT, false, 0, 0);
        gl.glBufferData(GL4.GL_ARRAY_BUFFER, 4 * textureData.position(), textureData.flip(), GL4.GL_STATIC_DRAW);
        
        gl.glEnableVertexAttribArray(2);
        gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, buffers.get(2));
        gl.glVertexAttribPointer(2, 3, GL4.GL_FLOAT, false, 0, 0);
        gl.glBufferData(GL4.GL_ARRAY_BUFFER, 4 * bufferSize, normalData.flip(), GL4.GL_STATIC_DRAW);
        numVertices = bufferSize / 3;
    }
    
    private void initCamera(GL4 gl) {
        cameraDirection = gl.glGetUniformLocation(program, "camera.direction");
        cameraPosition = gl.glGetUniformLocation(program, "camera.position");
    }
    
    private void initLight(GL4 gl) {
        lightColor = gl.glGetUniformLocation(program, "light.color");
        lightDirection = gl.glGetUniformLocation(program, "light.direction");
    }
    
    private void initProjection(GL4 gl) {
        projectionAspectRatio = gl.glGetUniformLocation(program, "projection.aspectRatio");
        projectionFar = gl.glGetUniformLocation(program, "projection.far");
        projectionFOV = gl.glGetUniformLocation(program, "projection.fov");
        projectionNear = gl.glGetUniformLocation(program, "projection.near");
    }
    
    private void initUniforms(GL4 gl) {
        initCamera(gl);
        initLight(gl);
        initProjection(gl);
    }
    
    public void render(GL4 gl) {
        texture.bind(gl);
        gl.glBindVertexArray(vertexArray);
        gl.glUseProgram(program);
        updateUniforms(gl);
        gl.glDrawArrays(GL4.GL_TRIANGLES, 0, numVertices);
    }
    
    private void updateCamera(GL4 gl) {
        gl.glUniform3f(cameraDirection, camera.direction.x, camera.direction.y, camera.direction.z);
        gl.glUniform3f(cameraPosition, camera.position.x, camera.position.y, camera.position.z);
    }
    
    private void updateLight(GL4 gl) {
        gl.glUniform4f(lightColor, light.color.x, light.color.y, light.color.z, light.alpha);
        gl.glUniform3f(lightDirection, light.direction.x, light.direction.y, light.direction.z);
    }
    
    private void updateProjection(GL4 gl) {
        gl.glUniform1f(projectionAspectRatio, aspectRatio);
        gl.glUniform1f(projectionFar, camera.farClipping);
        gl.glUniform1f(projectionFOV, camera.fov);
        gl.glUniform1f(projectionNear, camera.nearClipping);
    }
    
    private void updateUniforms(GL4 gl) {
        updateCamera(gl);
        updateLight(gl);
        updateProjection(gl);
    }
    
}