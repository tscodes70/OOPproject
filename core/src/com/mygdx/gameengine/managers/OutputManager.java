package com.mygdx.gameengine.managers;

import java.util.HashMap;

import com.mygdx.gameengine.interfaces.iOutput;

public class OutputManager <T extends iOutput> {

	//this allows use to store our music key(the file) and object(the music), so we can just keep calling the same methods without making multiple instances ontop of the files for call
		private HashMap<String, T> outputList;
		//This method helps to use sound anywhere in the engine just use the following format...
		//can add more logic if needed, or add stuff like lists or something though i don't think needed here yet
		//use SoundManager.play(put/file/path/here/yes/yes.mp3)
		//use anywhere, loads a file to be up and ready to play
		
		public OutputManager() {
			outputList = new HashMap<>();
		}
		
		public void add(String filePath, T outputType) {
			if (outputList.get(filePath) == null) outputList.put(filePath,outputType);
		}

		public void remove(String filePath) {
			outputList.remove(filePath);
		}

		//using hashmap so to dispose all of it you'll need to use this instead
		public void dispose() {
			outputList.clear();
		}
}
