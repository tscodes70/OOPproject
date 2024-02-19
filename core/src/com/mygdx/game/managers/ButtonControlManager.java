package com.mygdx.game.managers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.mygdx.game.models.Button;

public class ButtonControlManager extends IOManager {
	
    private List<Button> buttonList;

	public ButtonControlManager(List<Button> buttonList){
		super();
		this.buttonList = buttonList;
	}

	public void clicked(int clickedButton) {
		float x = (float)getMouseX();
		float y = Gdx.graphics.getHeight() - (float)getMouseY(); // texture y-axis coordinates are inverted
		
		for (Button button : this.buttonList) {
			// check if clicked fell within the button's bounding box
            if(button.getBoundingBox().contains(x, y)) {
            	switch(clickedButton) {
            		case Buttons.LEFT:
                    	button.leftClicked();
                    	break;
            		case Buttons.RIGHT:
                    	button.rightClicked();
            	}            		 
            }
        }
    }

	@Override
	public void checkClickEvents() {
		// TODO Auto-generated method stub
		if(super.pollLeftMouseButton()) this.clicked(Buttons.LEFT);
		if(super.pollRightMouseButton()) this.clicked(Buttons.RIGHT);
	}

	@Override
	public void checkKeyEvents() {
		// TODO Auto-generated method stub
		
	}
}
