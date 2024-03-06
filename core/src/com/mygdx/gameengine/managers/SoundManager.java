package com.mygdx.gameengine.managers;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

    //This method helps to use sound anywhere in the engine just use the following format...
    //can add more logic if needed, or add stuff like lists or something though i don't think needed here yet
    //use SoundManager.play(put/file/path/here/yes/yes.mp3) to have audio anywhere or even in if statements
    public static void play(String filePath) {
        Music music = Gdx.audio.newMusic(Gdx.files.internal(filePath));
        music.play();
    }

    //just dipose
    public void dispose(String filePath) {
        Music music = Gdx.audio.newMusic(Gdx.files.internal(filePath));
        music.dispose();
    }
}
