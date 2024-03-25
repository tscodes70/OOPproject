package com.mygdx.gamelayer.factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.gameengine.managers.IOManager;
import com.mygdx.gameengine.models.Entity;
import com.mygdx.gamelayer.models.Debris;
import com.mygdx.gamelayer.models.Planet;
import com.mygdx.gamelayer.models.Player;
import com.mygdx.gamelayer.models.Projectile;
import com.mygdx.gamelayer.models.SpaceTexture;

public class SpaceEntityFactory {
	private final int DEFAULT_PLAYER_X = 400;
	private final int DEFAULT_PLAYER_Y = 250;
	private final int DEFAULT_PLAYER_SPEED = 3;
	private final int DEFAULT_PLAYER_WIDTH = 80;
	private final int DEFAULT_PLAYER_HEIGHT = 100;
	
	private final int DEFAULT_PROJECTILE_XOFFSET = 35;
	private final int DEFAULT_PROJECTILE_YOFFSET = 50;
	private final int DEFAULT_PROJECTILE_SPEED = 5;
	private final int DEFAULT_PROJECTILE_WIDTH = 5;
	private final int DEFAULT_PROJECTILE_HEIGHT = 30;
	
	private final int DEFAULT_DEBRIS_RADIUS = 40; // remove
	private final int DEFAULT_DEBRIS_WIDTH = 80;
	private final int DEFAULT_DEBRIS_HEIGHT = 100;
	private final int DEFAULT_DEBRIS_SPEED = 1;

	private final Color DEFAULT_AI_COLOR = Color.RED;
	private final boolean COLLIDABLE = true;
	private final boolean PLAYABLE = true;
	private final boolean AI_CONTROL = true;
	
    private final float PLANET_POSITION_X = 0;
    private final float PLANET_POSITION_Y = -(Gdx.graphics.getWidth() / 2f);
    private final float PLANET_WIDTH = Gdx.graphics.getWidth();
    private final float PLANET_HEIGHT = 0.75f * ((float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight()) * (float) Gdx.graphics.getHeight();
    private final boolean PLANET_COLLIDABLE = true;
	
	private IOManager ioManager;
	
	public SpaceEntityFactory(IOManager ioManager) {
        this.ioManager = ioManager;
    }
	
	public Entity createEntity(String entityType, String entityPlanet) {
		if (entityType == null) {
            return null;
		}
        else {
            switch (entityType) {
                case "Debris":
                    return new Debris(
                    		(SpaceTexture) ioManager.getOutputManager().retrieve(entityPlanet + "DebrisTexture"),
                    		DEFAULT_DEBRIS_WIDTH,
                    		DEFAULT_DEBRIS_HEIGHT,
                    		(float) (Math.random() * (Gdx.graphics.getWidth() - 200) + 100),
                    		(float)(Gdx.graphics.getHeight() + (float)(Math.random() * 600)),
                    		DEFAULT_DEBRIS_SPEED,
                    		AI_CONTROL,
                    		COLLIDABLE);	
                default:
                    return null;
            }
        }
	}
	
	public Entity createEntity(String entityType, int id, int left, int right, int up, int down) {
		if (entityType == null) {
            return null;
		}
        else {
            switch (entityType) {
                case "Player":
                	return new Player(
                					id,
        							(SpaceTexture) ioManager.getOutputManager().retrieve("PlayerTexture"),
                					DEFAULT_PLAYER_WIDTH, 
                					DEFAULT_PLAYER_HEIGHT,
                					DEFAULT_PLAYER_X, 
                					DEFAULT_PLAYER_Y, 
                					DEFAULT_PLAYER_SPEED, 
                					PLAYABLE,
                					COLLIDABLE,
                					left,
                					right,
                					up,
                					down);		
                default:
                    return null;
            }
        }
	}

	public Entity createDynamicEntity(String entityType, String dynamicString1, float dynamicValue1) {
        if (entityType == null || dynamicString1 == null || dynamicString1.isEmpty()) {
        	return null;
        }
        else {
        	dynamicString1 = dynamicString1.substring(0, 1).toUpperCase() + dynamicString1.substring(1).toLowerCase();
	        switch (entityType) {
	        case "Planet":
	        	return new Planet(
	        					dynamicString1,
	        					(SpaceTexture)ioManager.getOutputManager().retrieve(dynamicString1 + "PlanetTexture"), 
	        					PLANET_WIDTH, 
	        					PLANET_HEIGHT, 
	        					PLANET_POSITION_X,
	        					PLANET_POSITION_Y,
	        					dynamicValue1,
	        					PLANET_COLLIDABLE);
	        default:
	            return null;
	        }
        }
	}
        
    public Entity createDynamicEntity(String entityType, float dynamicValue1, float dynamicValue2) {
    	if (entityType == null) {
            return null;
		}
        else {
	    	switch (entityType) {
		        case "Projectile":
		        	return new Projectile(
		        			dynamicValue1 + DEFAULT_PROJECTILE_XOFFSET, 
		        			dynamicValue2 + DEFAULT_PROJECTILE_YOFFSET, 
		            		DEFAULT_PROJECTILE_WIDTH, 
		            		DEFAULT_PROJECTILE_HEIGHT, 
		            		Color.GREEN, // change later on
		            		COLLIDABLE, 
		            		AI_CONTROL, 
		            		DEFAULT_PROJECTILE_SPEED);
	            default:
	                return null;
	    	}
        }
    }
}
