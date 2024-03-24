package com.mygdx.gameengine.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.gameengine.interfaces.iCollidable;
import com.mygdx.gameengine.interfaces.iMovable;

public class Entity {
    private float positionX;
    private float positionY;
    private float radius;
    private float width;
    private float height;
    private Color colour;
    private Texture texture;

    // For Circular Shape Entities
    public Entity(float positionX, float positionY, float radius, Color colour) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
        this.colour = colour;
    }
    
    // For Rectangle Shape Entities
    public Entity(float positionX, float positionY, float width, float height, Color colour) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
        this.colour = colour;
    }

    // For Texture Entities
    public Entity(Texture texture, float positionX, float positionY, float width, float height) {
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear); // Optional: set texture filtering for better quality
        texture.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge); // Optional: set texture wrapping
        this.texture = texture;
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
    }

    /**
     * Draw Shaped Entities using ShapeRenderer
     * @param shape
     */
    public void draw(ShapeRenderer shape) {
        shape.setColor(colour);
        if(radius != 0) {
        	shape.circle(getPositionX(), getPositionY(), getRadius());
        }else if(width != 0) {
        	shape.rect(getPositionX(), getPositionY(), getWidth(), getHeight());
        }
        
    }

    /**
     * Draw Texture Entities using SpriteBatch
     * @param batch
     */
    public void draw(SpriteBatch batch, float width, float height) {
        batch.draw(texture, getPositionX(), getPositionY(), width, height);
    }

    /**
     * Disposal of entity
     */
    public void dispose() {
    	if(texture != null) texture.dispose();
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

    public Texture getTex() { return texture; }

    public void setTex(Texture t) { texture = t; }

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

}