package com.mygdx.game.managers;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.models.Button;

public class ButtonManager {
    private List<Button> buttonList;

    public ButtonManager() {
        buttonList = new ArrayList<Button>();
    }
    
    public void addButton(Button button) {
    	buttonList.add(button);
    }
    
    public void removeButton(Button button) {
    	//implement remove Button logic here
    	buttonList.remove(button);
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
}