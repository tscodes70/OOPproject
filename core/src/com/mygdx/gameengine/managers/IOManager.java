package com.mygdx.gameengine.managers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mygdx.gameengine.interfaces.iInputOutput;
import com.mygdx.gameengine.interfaces.iManager;


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

    //this allows use to store our music key(the file) and object(the music), so we can just keep calling the same methods without making multiple instances ontop of the files for call
	private static HashMap<String, Music> musicList = new HashMap<>();
	//This method helps to use sound anywhere in the engine just use the following format...
	//can add more logic if needed, or add stuff like lists or something though i don't think needed here yet
	//use SoundManager.play(put/file/path/here/yes/yes.mp3)
	//use anywhere, loads a file to be up and ready to play
	public static void play(String filePath) {
		Music music = musicList.get(filePath);
		if (music == null) {
			music = Gdx.audio.newMusic(Gdx.files.internal(filePath));
			music.setLooping(true);
			musicList.put(filePath, music);
		}
		//if lets say you use this in render you'll need this, just so it won't render by frame, feel free to use this in if statements for collision sounds :)
		if (!music.isPlaying()) {
			music.play();
		}
	}

	//pauses music file that has been played(assuming its in the hashmap already else theres nothing to pause)
	public static void pause(String filePath) {
		Music music = musicList.get(filePath);
		music.pause();
	}

	//using hashmap so to dispose all of it you'll need to use this instead
	public void disposeMusic() {
		musicList.clear();
	}
	    
}

