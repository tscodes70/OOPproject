package com.mygdx.game.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Entity {
    float positionX;
    float positionY;
    int speed;
    boolean aiControl;

    Entity(float positionX, float positionY, int speed, boolean aiControl) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.speed = speed;
        this.aiControl = aiControl;
    }

    public void draw(ShapeRenderer shape) {
    }

    public void draw(SpriteBatch batch) {
    }

    public void update(float deltaTime) {
        // Update entity
    }

    public void render(SpriteBatch batch) {
        // Render the entity
    }

    abstract void moveEntity();

    abstract void updateEntity();

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

    public boolean isAiControl() {
        return aiControl;
    }

    public void setAiControl(boolean aiControl) {
        this.aiControl = aiControl;
    }
}