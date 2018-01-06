package com.amf.jaycaster.core;

public class Light {
    
    public float alpha;
    
    public Vector color, direction;
    
    public Light(Vector color, float alpha, Vector direction) {
        this.color = color;
        this.alpha = alpha;
        this.direction = direction;
    }
    
}