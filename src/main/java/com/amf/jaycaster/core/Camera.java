package com.amf.jaycaster.core;

public class Camera {
    
    public final Vector direction = new Vector(1, 0, 0), position = new Vector();
    
    public float farClipping, fov, nearClipping;
    
    public Camera(float fov, float nearClipping, float farClipping) {
        this.fov = fov;
        this.farClipping = farClipping;
        this.nearClipping = nearClipping;
    }
    
}