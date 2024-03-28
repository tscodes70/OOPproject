package com.mygdx.gamelayer.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.gameengine.models.Entity;

public class ColdStatus extends Entity{

	  private float timer;
	  private boolean active;
	  private float duration;
	    
	public ColdStatus(Texture texture, float positionX, float positionY, float width, float height) {
		super(texture, positionX, positionY, width, height);
		timer = 0;
        active = true;
        duration = 2f;
	}

	@Override
	public void update(float deltaTime) {
		if (active) {
            timer += deltaTime;
            if (timer >= duration) {
                active = false; // Deactivate the explosion after duration
            }
        }
	}

	@Override
	public boolean isAIControl() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setAIControl(boolean aiControl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(ShapeRenderer shape) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(SpriteBatch batch, float width, float height) {
		if (active) {
			batch.draw(super.getTex(), getPositionX(), getPositionY(), width, height);
		}
	}

}
