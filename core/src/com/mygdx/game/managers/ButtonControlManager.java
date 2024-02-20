package com.mygdx.game.managers;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.mygdx.game.globals.Globals;
import com.mygdx.game.models.Button;
import com.mygdx.game.models.Entity;

public class ButtonControlManager extends IOManager {
	
    private List<Button> buttonList;
    private Button spawnAIButton;
    private Button spawnPlayerButton;
    
    /**
     * Constructor that manages a list of Button instances
     * @param buttonList
     */
	public ButtonControlManager(List<Button> buttonList){
		super();
		this.buttonList = buttonList;
		spawnAIButton = buttonList.get(0);
		spawnPlayerButton = buttonList.get(1);
	}

	/**
	 * Main code for handling the logic of the clicked buttons.
	 * Check which buttons is clicked, run its code and calls the update function for
	 * each of the Manager Classes affected.
	 * @param clickedButton
	 * @param entityManager
	 * @param aiControlManager
	 * @param playerControlManager
	 * @param collisionManager
	 */
	public void buttonClick(int clickedButton, EntityManager entityManager, AIControlManager aiControlManager, PlayerControlManager playerControlManager, CollisionManager collisionManager) {
		float x = (float)getMouseX();
		float y = Gdx.graphics.getHeight() - (float)getMouseY(); // texture y-axis coordinates are inverted
			
            if(spawnAIButton.getBoundingBox().contains(x, y)) {
                    	entityManager.addEntity(new Entity(
                    			(float) (Math.random() * Gdx.graphics.getWidth()),
                    			(float) (Math.random() * Gdx.graphics.getHeight()),
                    			Globals.DEFAULT_ENTITY_SPEED,
                        		Globals.DEFAULT_ENTITY_RADIUS,
                        		Globals.DEFAULT_AI_COLOR, 
                        		Globals.AI_CONTROL, 
                        		Globals.COLLIDABLE));
                    	System.out.println("AI Spawned");
            	}            		 
            
            if(spawnPlayerButton.getBoundingBox().contains(x, y)) {
                    	entityManager.addEntity(new Entity(
                    			(float) (Math.random() * Gdx.graphics.getWidth()),
                    			(float) (Math.random() * Gdx.graphics.getHeight()),
                    			Globals.DEFAULT_ENTITY_SPEED,
                        		Globals.DEFAULT_ENTITY_RADIUS,
                        		Globals.DEFAULT_PLAYER_COLOR, 
                        		!Globals.AI_CONTROL, 
                        		Globals.COLLIDABLE));
                    	System.out.println("Player Spawned");
            }
            
        	List<Entity> entityList = entityManager.getEntityList();
        	aiControlManager.update(entityList);
        	playerControlManager.update(entityList);
        	collisionManager.update(entityList);
        }
    
	/**
	 * Main onclick poll method
	 * @param entityManager
	 * @param aiControlManager
	 * @param playerControlManager
	 * @param collisionManager
	 */
	public void handleClickEvents(EntityManager entityManager, AIControlManager aiControlManager, PlayerControlManager playerControlManager, CollisionManager collisionManager) {
		if(super.pollLeftMouseButton()) this.buttonClick(Buttons.LEFT,entityManager, aiControlManager, playerControlManager, collisionManager);
	}
	
	/**
	 * Disposal of ButtonManager Resources
	 */
    public void dispose() {
    	for (Button b : buttonList) b.dispose();
    	if(spawnAIButton != null) spawnAIButton.dispose();
    	if(spawnPlayerButton != null)  spawnPlayerButton.dispose();
		System.out.println("ButtonManager Resources Disposed");
    }

}
