package com.amf.jaycaster.renderer;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.util.GLBuffers;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.IntBuffer;

public abstract class Renderer {
    
    protected int fragmentShader, program, vertexArray, vertexShader;
    
    private String[] fragmentShaderSource, vertexShaderSource;
    
    protected Texture texture;
    
    private File textureSource;
    
    public void dispose(GL4 gl) {
        gl.glDetachShader(program, vertexShader);
        gl.glDetachShader(program, fragmentShader);
        gl.glDeleteProgram(program);
        texture.destroy(gl);
    }
    
    public void init(GL4 gl) {
        initShaders(gl);
        initTexture(gl);
        initVertexArray(gl);
    }
    
    private void initShaders(GL4 gl) {
        vertexShader = gl.glCreateShader(GL4.GL_VERTEX_SHADER);
        gl.glShaderSource(vertexShader, 1, vertexShaderSource, null);
        gl.glCompileShader(vertexShader);
        
        fragmentShader = gl.glCreateShader(GL4.GL_FRAGMENT_SHADER);
        gl.glShaderSource(fragmentShader, 1, fragmentShaderSource, null);
        gl.glCompileShader(fragmentShader);
        
        program = gl.glCreateProgram();
        gl.glAttachShader(program, vertexShader);
        gl.glAttachShader(program, fragmentShader);
        gl.glLinkProgram(program);        
    }
    
    private void initTexture(GL4 gl) {
        try {
            texture = TextureIO.newTexture(textureSource, false);
            texture.setTexParameteri(gl, GL4.GL_TEXTURE_MIN_FILTER, GL4.GL_NEAREST);
            texture.setTexParameteri(gl, GL4.GL_TEXTURE_MAG_FILTER, GL4.GL_NEAREST);
        } catch (Exception ex) {}
    }
    
    private void initVertexArray(GL4 gl) {
        IntBuffer buffer = GLBuffers.newDirectIntBuffer(1);
        gl.glGenVertexArrays(1, buffer);
        gl.glBindVertexArray(vertexArray = buffer.get(0));
    }
    
    private static String loadShader(String file) throws IOException  {
        StringBuilder shader = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.lines().forEach(line -> {
                shader.append(line).append("\n");
            });
        }
        return shader.toString();
    }
    
    public abstract void render(GL4 gl);
    
    public void setShaders(String shader) throws IOException {
        setShaders(shader + ".vert", shader + ".frag");
    }
    
    public void setShaders(String vertexShader, String fragmentShader) throws IOException {
        vertexShaderSource = new String[] {loadShader(vertexShader)};
        fragmentShaderSource = new String[] {loadShader(fragmentShader)};
    }
    
    public void setTexture(String texture) {
        textureSource = new File(texture);
    }
    
}