package com.amf.jaycaster.core;

import com.amf.jaycaster.renderer.Renderer;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;

public class Game implements GLEventListener {
    
    private final FPSAnimator animator;
    
    private final GLCanvas canvas;
    
    private final List<Renderer> renderers = new LinkedList<>();
    
    private final JFrame window;
    
    public Game(String title, int targetFPS, int width, int height) {
        animator = new FPSAnimator(targetFPS);
        animator.add(canvas = new GLCanvas(new GLCapabilities(GLProfile.get(GLProfile.GL4))));
        canvas.addGLEventListener(this);
        window = new JFrame(title);
        window.getContentPane().add(canvas);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(width, height);
    }
    
    public void addRenderer(Renderer renderer) {
        renderers.add(renderer);
    }
    
    public void display(GLAutoDrawable drawable) {
        GL4 gl = drawable.getGL().getGL4();
        renderers.forEach(r -> r.render(gl));
    }
    
    public void dispose(GLAutoDrawable drawable) {
        GL4 gl = drawable.getGL().getGL4();
        renderers.forEach(r -> r.dispose(gl));
    }
    
    public void init(GLAutoDrawable drawable) {
        GL4 gl = drawable.getGL().getGL4();
        renderers.forEach(r -> r.init(gl));
    }
    
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}
    
    public void start() {
        window.setVisible(true);
        animator.start();
    }
    
}