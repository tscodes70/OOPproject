package com.mygdx.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.interfaces.iMovable;

public class Entity implements iMovable {
    private float positionX;
    private float positionY;
    private int speed;
    private boolean aiControl;
    private float radius;
    private Color colour;
    private Texture tex;

    // For player entity
    public Entity(float positionX, float positionY, int speed, float radius, Color colour) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.speed = speed;
        this.radius = radius;
        this.colour = colour;
    }

    // For AI entity
    public Entity(String t, float positionX, float positionY, int speed, boolean aiControl) {
        this.tex = new Texture(Gdx.files.internal(t));
        this.positionX = positionX;
        this.positionY = positionY;
        this.speed = speed;
        this.aiControl = aiControl;
    }

    public void draw(ShapeRenderer shape) {
        shape.setColor(colour);
        shape.circle(getPositionX(), getPositionY(), getRadius());
    }

    public void draw(SpriteBatch batch) {
        batch.draw(tex, getPositionX(), getPositionY());
    }

    public void update(float deltaTime) {
        // Update entity (psps im not sure waht to put here)
    }

    public void render(SpriteBatch batch) {
        // Render the entity (and here)
    }

    public void moveEntity() {
        if (aiControl) {
            moveAiControlled();
        }
        else {
            movePlayerControlled();
        }
    }

    @Override
    public void moveAiControlled() {}

    @Override
    public void movePlayerControlled() {}

    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public float getRadius() { return radius; }

    public void setRadius(float radius) { this.radius = radius; }

    public Color getColour() { return colour; }

    public void setColour(Color colour) { this.colour = colour; }

    public boolean isAiControl() { return aiControl; }

    public void setAiControl(boolean aiControl) { this.aiControl = aiControl; }

    public Texture getTex() { return tex; }

    public void setTex(Texture t) { tex = t; }

}