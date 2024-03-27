package com.mygdx.gamelayer.managers;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.gameengine.interfaces.iPlayer;
import com.mygdx.gameengine.managers.PlayerControlManager;
import com.mygdx.gameengine.models.Entity;
import com.mygdx.gameengine.models.Keyboard;
import com.mygdx.gamelayer.interfaces.iSpacePlayer;
import com.mygdx.gamelayer.models.Player;

public class SpacePlayerControlManager extends PlayerControlManager {
	
	private List<iSpacePlayer> playerList;
	
	/**
     * Adds a Player entity into PlayerControlManager list
     * @param entity
     */
	public void add(iSpacePlayer player) {
		playerList.add(player);
	}
	
	/**
     * Removes a Player entity from PlayerControlManager list
     * @param entity
     */
	public void remove(iSpacePlayer player) {
		playerList.remove(player);
		((Entity)player).dispose();
	}
	
	 @Override
	 public void extractPlayer(List<Entity> entityList) {
			List<iSpacePlayer> updatedPlayerList = new ArrayList<iSpacePlayer>();
			
			// Iterate over entities and add playable ones to playerList
		    for (Entity entity : entityList) {
		        if (entity instanceof iSpacePlayer) {
		        	iSpacePlayer playableEntity = (iSpacePlayer) entity;
		            if (playableEntity.isPlayable()) {
		            	updatedPlayerList.add(playableEntity);
		            }
		        }
		    }
		    playerList = updatedPlayerList;
	 }

	 
    public SpacePlayerControlManager(List<Entity> entityList, Keyboard keyboardDevice) {
        super(entityList, keyboardDevice);
    }

    public void move(float deltaTime, float gravity) {
        for (iSpacePlayer player : getSpacePlayerList()) {
            if(getKeyboardDevice().pollInputHold(player.getLeftKeybind())) {
            	if(getKeyboardDevice().pollInputHold(((Player)player).getShiftKeybind())) player.moveLeft(deltaTime, gravity, true);
            	else player.moveLeft(deltaTime, gravity, false);
            }
            else if(getKeyboardDevice().pollInputHold(player.getRightKeybind())) {
            	if(getKeyboardDevice().pollInputHold(((Player)player).getShiftKeybind())) player.moveRight(deltaTime, gravity, true);
            	else player.moveRight(deltaTime, gravity, false);
            }
            else if(getKeyboardDevice().pollInputHold(player.getUpKeybind())) {
            	if(getKeyboardDevice().pollInputHold(((Player)player).getShiftKeybind())) player.moveUp(deltaTime, gravity, true);
            	else player.moveUp(deltaTime, gravity, false);
            }
            else if(getKeyboardDevice().pollInputHold(player.getDownKeybind())) {
            	if(getKeyboardDevice().pollInputHold(((Player)player).getShiftKeybind())) player.moveDown(deltaTime, gravity, true);
            	else player.moveDown(deltaTime, gravity, false);
            }
            else {
    			((Player)player).regenStaminaBar(deltaTime);
            }
        }
    }

	public List<iSpacePlayer> getSpacePlayerList() {
		return playerList;
	}

	public void setSpacePlayerList(List<iSpacePlayer> playerList) {
		this.playerList = playerList;
	}
    
    
}
