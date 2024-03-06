package com.mygdx.gameengine.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;

public class Button {
    private float positionX;
    private float positionY;
    private float scale;
    private Rectangle boundingBox;
    private Texture tex;

    public Button(Texture tex, float positionX, float positionY, float scale) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.scale = scale; // scale the size of the texture
        this.tex = tex;
        this.boundingBox = new Rectangle(positionX, positionY, this.getWidth(), this.getHeight());
    }

    /**
     * Draws Texture Buttons using SpriteBatch
     * @param batch
     */
    public void draw(SpriteBatch batch) {
    	Sprite s = new Sprite(tex);
    	s.setScale(0.2f);
        batch.draw(tex, getPositionX(), getPositionY(), (int)(tex.getWidth() * scale), (int)(tex.getHeight() * scale));
    }
    
    /**
     * Disposal of Button Resources
     */
    public void dispose() {
    	if(tex!=null) tex.dispose();
    }
    
    /**
     * Updates position of bounding box
     */
    public void update() {
    	boundingBox.setPosition(positionX, positionY);
    }
    
    // Setter Getters
    public Rectangle getBoundingBox() { return boundingBox; }
    
    public float getWidth() { return tex.getWidth() * scale; }
    
    public float getHeight() { return tex.getHeight() * scale; }

    public float getPositionX() { return positionX; }

    public void setPositionX(float positionX) { this.positionX = positionX; }

    public float getPositionY() { return positionY; }

    public void setPositionY(float positionY) { this.positionY = positionY; }

    public Texture getTex() { return tex; }

    public void setTex(Texture t) { tex = t; }
}
