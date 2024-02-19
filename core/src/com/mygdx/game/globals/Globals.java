package com.mygdx.game.globals;

public class Globals {

    public static final String APP_TITLE = "this is our game";
    public static final String APP_VERSION = "0.0.1";
    
    public static final String AUDIO_PATH = "audio/music";
    public static final String IMAGE_PATH = "image";
    
    public static final int DEFAULT_AUDIO_VOLUME = 20;
    
    public static final int SPLASH_SCREEN = 0;
    public static final int GAME_SCREEN = 1;
    public static final int END_SCREEN = 2;
    
    public static final String BGAUDIO_SS = String.format("%s/mii-channel.mp3", AUDIO_PATH);
    public static final String BGAUDIO_GS = String.format("%s/megalovania.mp3", AUDIO_PATH);
    public static final String BGAUDIO_ES = String.format("%s/bbq.mp3", AUDIO_PATH);
    
    public static final String BGIMAGE_SS = String.format("%s/splash.jpg", IMAGE_PATH);
    public static final String BGIMAGE_GS = String.format("%s/badlogic.jpg", IMAGE_PATH);
//    public static final String BGIMAGE_ES = String.format("%s/smth.jpg", IMAGE_PATH);
}
