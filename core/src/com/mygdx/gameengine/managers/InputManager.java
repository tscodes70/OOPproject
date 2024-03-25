package com.mygdx.gameengine.managers;

import java.util.HashMap;

import com.mygdx.gameengine.interfaces.iInput;

public class InputManager<T extends iInput> {
	// Integer to track input device id
	private HashMap<Integer, T> inputList;

	public InputManager() {
		inputList = new HashMap<>();
	}

	public void add(T inputType) {
	    int nextAvailableDeviceID = 1;
	    while (inputList.containsKey(nextAvailableDeviceID)) nextAvailableDeviceID++;
		inputList.put(nextAvailableDeviceID,inputType);
	}

	public void remove(int deviceId) {
		if (!inputList.containsKey(deviceId)) inputList.remove(deviceId);
		else System.out.println("ERROR - Removal of input device failed");
	}
	
	public T retrieve(int id) {
		return inputList.get(id);
	}

	public HashMap<Integer, T> getInputList() {
		return inputList;
	}

	public void setInputList(HashMap<Integer, T> inputList) {
		this.inputList = inputList;
	}

	
}
