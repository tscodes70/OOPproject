package com.mygdx.gameengine.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.gameengine.interfaces.iCollidable;
import com.mygdx.gameengine.interfaces.iEntity;
import com.mygdx.gameengine.interfaces.iMovable;

public abstract class Entity implements iEntity{
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

    // For Textured Entities
    public Entity(Texture texture, float positionX, float positionY, float width, float height) {
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear); // Set texture filtering for better quality
        texture.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge); // Set texture wrapping
        this.texture = texture;
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
    }

    /**
     * Disposal of entity
     */
    public void dispose() {
    	//if(texture != null) texture.dispose();
    }
    
    /**
     * Abstract Draw Method for Shaped Entities using ShapeRenderer
     * @param shape
     */
    public abstract void draw(ShapeRenderer shape);

    /**
     * Abstract Draw Method for Textured Entities using SpriteBatch
     * @param batch
     */
    public abstract void draw(SpriteBatch batch, float width, float height);

    // Setters & Getters
    public float getPositionX() { return positionX; }
    public void setPositionX(float positionX) { this.positionX = positionX; }
    public float getPositionY() { return positionY; }
    public void setPositionY(float positionY) { this.positionY = positionY; }
    public float getRadius() { return radius; }
    public void setRadius(float radius) { this.radius = radius; }
    public Color getColour() { return colour; }
    public void setColour(Color colour) { this.colour = colour; }
    public Texture getTex() { return texture; }
    public void setTex(Texture t) { texture = t; }
    public float getWidth() { return width; }
    public void setWidth(float width) { this.width = width; }
    public float getHeight() { return height; }
    public void setHeight(float height) { this.height = height; }


}