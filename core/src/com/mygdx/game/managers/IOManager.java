package com.mygdx.game.managers;


import java.util.ArrayList;
import java.util.List;

import com.mygdx.game.interfaces.iInputOutput;
import com.mygdx.game.interfaces.iManager;


public class IOManager<T extends iInputOutput> {
	
	private List<Integer> inputCodeList;
	private T inputOutputDevice;

	public IOManager(T inputOutputDevice) {
		inputCodeList = new ArrayList<Integer>();
		this.inputOutputDevice = inputOutputDevice;
	}

	public void add(Integer obj) {
		inputCodeList.add(obj);
	}

	public void remove(Integer obj) {
		inputCodeList.remove(obj);
	}
	
	public boolean pollInputHold(int keyCode) {
	    return inputOutputDevice.pollInputHold(keyCode);
	}

	public boolean pollInputPress(int keyCode) {
	    return inputOutputDevice.pollInputPress(keyCode);
	}
	
	public List<Integer> getInputCodeList() {
        return inputCodeList;
    }
	
	public T getinputOutputDevice() {
		return inputOutputDevice;
	}
	    
}

