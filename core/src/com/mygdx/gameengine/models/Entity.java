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
    private float speed;
    private boolean aiControl;
    private boolean collidable;
    private Rectangle boundingBox;
    private float radius;
    private Color colour;
    private Texture tex;

    // For Circular Shape Entities
    public Entity(float positionX, float positionY, int speed, float radius, Color colour,  boolean aiControl, boolean collidable) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.speed = speed;
        this.radius = radius;
        this.colour = colour;
        this.aiControl = aiControl;
        this.collidable = collidable;
        this.boundingBox = new Rectangle(positionX, positionY, radius * 2, radius * 2);
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
     * Updates positions of entity and bounding box
     * @param deltaTime
     */
    public void update(float deltaTime) {
    	// Update position based on speed and direction
//        positionX += speed * deltaTime;
        positionY += speed * deltaTime;

        // Update bounding box position
        boundingBox.setPosition(positionX, positionY);
    }
    
    /**
     * Disposal of entity
     */
    public void dispose() {
    	if(tex != null) tex.dispose();
    }
    
    // Getters Setters
    public Rectangle getBoundingBox() { return boundingBox; }
    
    public float getPositionX() { return positionX; }
    
    public void setPositionX(float positionX) { this.positionX = positionX; }
    
    public float getPositionY() { return positionY; }
    
    public void setPositionY(float positionY) {this.positionY = positionY;}
    
    public float getSpeed() { return speed; }
    
    public void setSpeed(float f) { this.speed = f; }
    
    public float getRadius() { return radius; }

    public void setRadius(float radius) { this.radius = radius; }

    public Color getColour() { return colour; }

    public void setColour(Color colour) { this.colour = colour; }

    public boolean isAiControl() { return aiControl; }

    public void setAiControl(boolean aiControl) { this.aiControl = aiControl; }

    public Texture getTex() { return tex; }

    public void setTex(Texture t) { tex = t; }

	public boolean isCollidable() { return collidable; }

	public void setCollidable(boolean collidable) { this.collidable = collidable; }

}