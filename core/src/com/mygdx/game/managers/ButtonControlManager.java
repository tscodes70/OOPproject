package com.mygdx.game.managers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.models.Button;
import com.mygdx.game.models.Entity;

public class ButtonControlManager extends IOManager {
	
    private List<Button> buttonList;
    private Button spawnAIButton;
    private Button spawnPlayerButton;

	public ButtonControlManager(List<Button> buttonList){
		super();
		this.buttonList = buttonList;
	}

	public void clicked(int clickedButton, EntityManager entityManager, AIControlManager aiControlManager, PlayerControlManager playerControlManager, CollisionManager collisionManager) {
		float x = (float)getMouseX();
		float y = Gdx.graphics.getHeight() - (float)getMouseY(); // texture y-axis coordinates are inverted
		
		spawnAIButton = buttonList.get(0);
		spawnPlayerButton = buttonList.get(1);
			// check if clicked fell within the button's bounding box
            if(spawnAIButton.getBoundingBox().contains(x, y)) {
            	switch(clickedButton) {
            		case Buttons.LEFT:
            			spawnAIButton.leftClicked();
                    	float positionX = (float) (Math.random() * Gdx.graphics.getWidth());
                    	float positionY = (float) (Math.random() * Gdx.graphics.getHeight());
                    	entityManager.addEntity(new Entity(positionX ,positionY ,2,40,Color.RED, true, true));
                    	List<Entity> entityList = entityManager.getEntityList();
                    	aiControlManager.update(entityList);
                    	playerControlManager.update(entityList);
                    	collisionManager.update(entityList);
                    	break;
            		case Buttons.RIGHT:
            			spawnAIButton.rightClicked();
            	}            		 
            }
            
            if(spawnPlayerButton.getBoundingBox().contains(x, y)) {
            	switch(clickedButton) {
            		case Buttons.LEFT:
            			spawnPlayerButton.leftClicked();
                    	float positionX = (float) (Math.random() * Gdx.graphics.getWidth());
                    	float positionY = (float) (Math.random() * Gdx.graphics.getHeight());
                    	entityManager.addEntity(new Entity(positionX ,positionY, 2, 40, Color.BLUE, false, true));
                    	List<Entity> entityList = entityManager.getEntityList();
                    	aiControlManager.update(entityList);
                    	playerControlManager.update(entityList);
                    	collisionManager.update(entityList);
                    	break;
            		case Buttons.RIGHT:
            			spawnPlayerButton.rightClicked();
            	}            		 
            }
        }
    

	@Override
	public void checkClickEvents(EntityManager entityManager, AIControlManager aiControlManager, PlayerControlManager playerControlManager, CollisionManager collisionManager) {
		// TODO Auto-generated method stub
		if(super.pollLeftMouseButton()) this.clicked(Buttons.LEFT,entityManager, aiControlManager, playerControlManager, collisionManager);
		if(super.pollRightMouseButton()) this.clicked(Buttons.RIGHT,entityManager, aiControlManager, playerControlManager, collisionManager);
	}

	@Override
	public void checkKeyEvents() {
		// TODO Auto-generated method stub
		
	}
}
