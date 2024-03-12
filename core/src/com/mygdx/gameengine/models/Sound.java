package com.mygdx.gameengine.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mygdx.gameengine.interfaces.iOutput;

public class Sound implements iOutput {
	
	private Music music;
	
	public Sound(String filePath) {
		music = Gdx.audio.newMusic(Gdx.files.internal(filePath));
		music.setLooping(true);
		music.setVolume(0.15f);
	}
	
	public void output() {
		//if lets say you use this in render you'll need this, just so it won't render by frame, feel free to use this in if statements for collision sounds :)
		if (!music.isPlaying()) {
			music.setPosition(0);
			music.play();
		}
	}

	//pauses music file that has been played(assuming its in the hashmap already else theres nothing to pause)
	public void pause() {
		if (music.isPlaying()) {
			music.pause();
		}
	}
	
	public void dispose() {
		music.dispose();
	}

}
