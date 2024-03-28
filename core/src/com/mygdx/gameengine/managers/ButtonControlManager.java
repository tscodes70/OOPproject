package com.mygdx.gameengine.managers;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.mygdx.gameengine.models.Button;
import com.mygdx.gameengine.models.Mouse;

public class ButtonControlManager {
	
    private List<Button> buttonList;
    private Button spawnAIButton;
    private Button spawnPlayerButton;
    private Mouse mouseDevice;
    
	private final int LEFTCLICKBUTTON = Buttons.LEFT;
    
    /**
     * Constructor that manages a list of Button instances
     * @param buttonList
     */
	public ButtonControlManager(List<Button> buttonList,Mouse mouseDevice){
		super();
		this.buttonList = buttonList;
		this.mouseDevice = mouseDevice;
	}
	
	/**
	 * Main code for handling the logic of the clicked buttons.
	 * Check which buttons is clicked, run its code and calls the update function for
	 * each of the Manager Classes affected.
	 * @param clickedButton
	 */
	// iterates through buttons, checks if click occurred within their bounding boxes
	// game logic moved into their respective game scene
	public Button buttonClick(int clickedButton) {
		float x = mouseDevice.getMouseX();
		float y = Gdx.graphics.getHeight() - mouseDevice.getMouseY(); // texture y-axis coordinates are inverted
		
		// return the button if the click occurred inside its bounding box
		for(Button b : buttonList) {
			if (b.getBoundingBox().contains(x, y)) return b;
		}
		
		return null;
    }
	
	/**
	 * Main onclick poll method
	 * @param entityManager
	 * @param aiControlManager
	 * @param playerControlManager
	 * @param collisionManager
	 */
	public Button handleClickEvents() {
		return mouseDevice.pollInputPress(LEFTCLICKBUTTON) ? this.buttonClick(Buttons.LEFT) : null;
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

	public List<Button> getButtonList() {
		return buttonList;
	}

	public void setButtonList(List<Button> buttonList) {
		this.buttonList = buttonList;
	}

	public Mouse getMouseDevice() {
		return mouseDevice;
	}

	public void setMouseDevice(Mouse mouseDevice) {
		this.mouseDevice = mouseDevice;
	}
    
    
}
