package com.mygdx.gameengine.managers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.gameengine.interfaces.iAI;
import com.mygdx.gameengine.interfaces.iCollidable;
import com.mygdx.gameengine.interfaces.iMovable;
import com.mygdx.gameengine.interfaces.iPlayer;
import com.mygdx.gameengine.models.Entity;
import com.mygdx.gameengine.models.Keyboard;

public class PlayerControlManager{
	
    private List<iPlayer> playerList;
    

    private Keyboard keyboardDevice;

	/**
	 * Constructor that takes a list of entities, extracts each entity
	 * that has variable aiControl=false.
	 * @param entityList
	 */
	public PlayerControlManager(List<Entity> entityList, Keyboard keyboardDevice){
		super();
		this.keyboardDevice = keyboardDevice;
		playerList = new ArrayList<iPlayer>();
		
		extractPlayer(entityList);
	}
	
	/**
     * Adds a Player entity into PlayerControlManager list
     * @param entity
     */
	public void add(iPlayer player) {
		playerList.add(player);
	}
	
	/**
     * Removes a Player entity from PlayerControlManager list
     * @param entity
     */
	public void remove(iPlayer player) {
		playerList.remove(player);
		player.dispose();
	}
	
	/**
     * Retrieves updated entityList when entityList is modified
     * @param entityList
     */
	 public void update(List<Entity> entityList) {
			extractPlayer(entityList);
	 }
	 
	 public void extractPlayer(List<Entity> entityList) {
			List<iPlayer> updatedPlayerList = new ArrayList<iPlayer>();
			
			// Iterate over entities and add playable ones to playerList
		    for (Entity entity : entityList) {
		        if (entity instanceof iPlayer) {
		        	iPlayer playableEntity = (iPlayer) entity;
		            if (playableEntity.isPlayable()) {
		            	updatedPlayerList.add(playableEntity);
		            }
		        }
		    }
		    playerList = updatedPlayerList;
	 }

	 /**
	  * Main movement handler methods that calls movement methods
	  * based on respective movement key presses
	  * @param deltaTime
	  */
	public void move(float deltaTime) {
		for (iPlayer player : playerList) {
		if(keyboardDevice.pollInputHold(player.getLeftKeybind())) player.moveLeft(deltaTime);
		if(keyboardDevice.pollInputHold(player.getRightKeybind())) player.moveRight(deltaTime);
		if(keyboardDevice.pollInputHold(player.getUpKeybind())) player.moveUp(deltaTime);
		if(keyboardDevice.pollInputHold(player.getDownKeybind())) player.moveDown(deltaTime);
		}
	}
	
	/**
	 *  Disposal of PlayerControlManager Resources
	 */
	public void dispose() {
		for (iPlayer p : playerList) p.dispose();
		System.out.println("PlayerControlManager Resources Disposed");
	}

}
