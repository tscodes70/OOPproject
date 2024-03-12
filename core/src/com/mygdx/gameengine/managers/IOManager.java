package com.mygdx.gameengine.managers;

import com.mygdx.gameengine.interfaces.iInput;
import com.mygdx.gameengine.interfaces.iOutput;

public class IOManager {
	
	private InputManager<iInput> inputManager;
	private OutputManager<iOutput> outputManager;
	
	public IOManager(InputManager<iInput> inputManager,OutputManager<iOutput> outputManager) {
		this.inputManager = inputManager;
		this.outputManager = outputManager;
	}

	public InputManager<iInput> getInputManager() {
		return inputManager;
	}

	public void setInputManager(InputManager<iInput> inputManager) {
		this.inputManager = inputManager;
	}

	public OutputManager<iOutput> getOutputManager() {
		return outputManager;
	}

	public void setOutputManager(OutputManager<iOutput> outputManager) {
		this.outputManager = outputManager;
	}

	  
}

