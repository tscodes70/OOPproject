package com.mygdx.game.managers;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.models.Button;
import com.mygdx.game.models.Entity;
import com.mygdx.game.models.KeyboardInput;
import com.mygdx.game.models.MouseInput;

public class ButtonControlManager {
	
    private List<Button> buttonList;
    private Button spawnAIButton;
    private Button spawnPlayerButton;
    private IOManager<MouseInput> mouseDevice;
    
	private final int LEFTCLICKBUTTON = Buttons.LEFT;
	private final int RIGHTCLICKBUTTON = Buttons.RIGHT;
	
	private final int DEFAULT_ENTITY_SPEED = 2;
	private final int DEFAULT_ENTITY_RADIUS = 40;
	private final Color DEFAULT_PLAYER_COLOR = Color.BLUE;
	private final Color DEFAULT_AI_COLOR = Color.RED;
	private final boolean COLLIDABLE = true;
	private final boolean AI_CONTROL = true;

    
    /**
     * Constructor that manages a list of Button instances
     * @param buttonList
     */
	public ButtonControlManager(List<Button> buttonList,IOManager<MouseInput> mouseDevice){
		super();
		this.buttonList = buttonList;
		this.mouseDevice = mouseDevice;
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
		float x = mouseDevice.getinputOutputDevice().getMouseX();
		float y = Gdx.graphics.getHeight() - mouseDevice.getinputOutputDevice().getMouseY(); // texture y-axis coordinates are inverted
			
            if(spawnAIButton.getBoundingBox().contains(x, y)) {
                    	entityManager.add(new Entity(
                    			(float) (Math.random() * Gdx.graphics.getWidth()),
                    			(float) (Math.random() * Gdx.graphics.getHeight()),
                    			DEFAULT_ENTITY_SPEED,
                        		DEFAULT_ENTITY_RADIUS,
                        		DEFAULT_AI_COLOR, 
                        		AI_CONTROL, 
                        		COLLIDABLE));
                    	System.out.println("AI Spawned");
            	}            		 
            
            if(spawnPlayerButton.getBoundingBox().contains(x, y)) {
                    	entityManager.add(new Entity(
                    			(float) (Math.random() * Gdx.graphics.getWidth()),
                    			(float) (Math.random() * Gdx.graphics.getHeight()),
                    			DEFAULT_ENTITY_SPEED,
                        		DEFAULT_ENTITY_RADIUS,
                        		DEFAULT_PLAYER_COLOR, 
                        		!AI_CONTROL, 
                        		COLLIDABLE));
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
		if(mouseDevice.pollInputPress(LEFTCLICKBUTTON)) this.buttonClick(Buttons.LEFT,entityManager, aiControlManager, playerControlManager, collisionManager);
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
