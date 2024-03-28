package com.mygdx.gameengine.managers;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.gameengine.models.Button;

public class ButtonManager {
    private List<Button> buttonList;
    private List<Button> updatedButtonList;

    public ButtonManager() {
        buttonList = new ArrayList<Button>();
    }
    
    public void add(Button button) {
    	buttonList.add(button);
    }
    
    public void remove(Button button) {
    	buttonList.remove(button);
    	button.dispose();
    }
    
    public void update(List<Button> buttonList) {
    	updatedButtonList = new ArrayList<Button>();
    	for (Button button : buttonList) updatedButtonList.add(button);
    	this.buttonList = updatedButtonList;
    }
    

	public void drawButtons(SpriteBatch batch) {
		for (Button button : this.buttonList) {
			if (button.getTex() != null) button.draw(batch);
		}
	}

	public void setButtonList(List<Button> buttonList) {
		this.buttonList = buttonList;
	}
	
	public List<Button> getButtonList() {
		return buttonList;
	}
	
    public void dispose() {
    	for (Button b : buttonList) b.dispose();
		System.out.println("ButtonManager Resources Disposed");
    }
}