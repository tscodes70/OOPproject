package com.mygdx.gameengine.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.gameengine.interfaces.iCollidable;
import com.mygdx.gameengine.interfaces.iMovable;

public class Entity {
    private float positionX;
    private float positionY;
    private float radius;
    private Color colour;
    private Texture tex;

    // For Circular Shape Entities
    public Entity(float positionX, float positionY, float radius, Color colour) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
        this.colour = colour;
    }

    // For Texture Entities
//    public Entity(String t, float positionX, float positionY, int speed, boolean aiControl) {
//        this.tex = new Texture(Gdx.files.internal(t));
//        this.positionX = positionX;
//        this.positionY = positionY;
//        this.speed = speed;
//        this.aiControl = aiControl;
//    }

    /**
     * Draw Shaped Entities using ShapeRenderer
     * @param shape
     */
    public void draw(ShapeRenderer shape) {
        shape.setColor(colour);
        shape.circle(getPositionX(), getPositionY(), getRadius());
    }

    /**
     * Draw Texture Entities using SpriteBatch
     * @param batch
     */
    public void draw(SpriteBatch batch) {
        batch.draw(tex, getPositionX(), getPositionY());
    }

    /**
     * Disposal of entity
     */
    public void dispose() {
    	if(tex != null) tex.dispose();
    }
    
    // Getters Setters
    
    public float getPositionX() { return positionX; }
    
    public void setPositionX(float positionX) { this.positionX = positionX; }
    
    public float getPositionY() { return positionY; }
    
    public void setPositionY(float positionY) {this.positionY = positionY;}
    
    public float getRadius() { return radius; }

    public void setRadius(float radius) { this.radius = radius; }

    public Color getColour() { return colour; }

    public void setColour(Color colour) { this.colour = colour; }

    public Texture getTex() { return tex; }

    public void setTex(Texture t) { tex = t; }

}