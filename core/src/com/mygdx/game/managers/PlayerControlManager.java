package com.mygdx.game.managers;

import com.badlogic.gdx.Input;

public class PlayerControlManager extends IOManager {

	public PlayerControlManager(){
		super();
	}
	
//not done yet ~
    @Override
    public boolean keyUp(int keycode) {
    	if(keycode == Input.Keys.LEFT) System.out.println("Left arrow released");
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
    	if(keycode == Input.Keys.LEFT) System.out.println("Left arrow pressed");
        return false;
    }

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}

    //

}
