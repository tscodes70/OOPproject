package com.mygdx.gameengine.managers;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import java.util.HashMap;


public class SoundManager {

    //private static boolean isPlaying = false;

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
        //if lets say you use this in render you'll need this
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












    /*//use case for scene or places where you're hard setting music e.g in scene class..?
    public static void playMethod2(String filePath) {
        Music music = Gdx.audio.newMusic(Gdx.files.internal(filePath));
        music.setLooping(true);
        music.play();
        //if lets say you use this in render you'll need this
        if (!music.isPlaying()) {
            music.play();
        }
    }*/

    /*//pauses a specific file...???? WORK IN PROGRESS
    public static void pausemethod2(String filePath) {
        Music music = Gdx.audio.newMusic(Gdx.files.internal(filePath));
        music.pause();
    }*/

    //do i make a method to hold then allow to get..?




