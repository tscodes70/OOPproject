package com.mygdx.game.globals;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;

public class Globals {

    public static final String APP_TITLE = "this is our game";
    public static final String APP_VERSION = "0.0.1";

    public static final String AUDIO_PATH = "audio/music";
    public static final String IMAGE_PATH = "image";

    public static final int DEFAULT_AUDIO_VOLUME = 20;
    
    public static final int DEFAULT_ENTITY_SPEED = 2;
    public static final int MAX_ENTITY_SPEED = 10;
    public static final int DEFAULT_ENTITY_SPEED_MULTIPLIER = 100;
    public static final int DEFAULT_ENTITY_RADIUS = 40;
    public static final Color DEFAULT_PLAYER_COLOR = Color.BLUE;
    public static final Color DEFAULT_AI_COLOR = Color.RED;
    public static final boolean COLLIDABLE = true;
    public static final boolean AI_CONTROL = true;
    
    public static final int INITIAL_MAX_SPAWN = 15;
    public static final int INITIAL_MIN_SPAWN = 5;

    public static final int SPLASH_SCREEN = 0;
    public static final int GAME_SCREEN = 1;
    public static final int PAUSE_SCREEN = 2;
    public static final int END_SCREEN = 3;

    public static final String BGAUDIO_SS = String.format("%s/mii-channel.mp3", AUDIO_PATH);
    public static final String BGAUDIO_GS = String.format("%s/megalovania.mp3", AUDIO_PATH);
    public static final String BGAUDIO_ES = String.format("%s/bbq.mp3", AUDIO_PATH);

    public static final String BGIMAGE_SS = String.format("%s/splash.jpg", IMAGE_PATH);
    
    public static final String IMAGE_BUTTON1 = String.format("%s/button1.png", IMAGE_PATH);
    public static final String IMAGE_BUTTON2 = String.format("%s/button2.png", IMAGE_PATH);

    public static final int KEYBIND_LEFT = Keys.LEFT;
	public static final int KEYBIND_RIGHT  = Keys.RIGHT;
	public static final int KEYBIND_UP  = Keys.UP;
	public static final int KEYBIND_DOWN  = Keys.DOWN;
	public static final int KEYBIND_ESCAPE  = Keys.ESCAPE;
	public static final int KEYBIND_ENTER  = Keys.ENTER;
	public static final int KEYBIND_BACKSPACE  = Keys.BACKSPACE;
	public static final int MOUSE_LEFT  = Buttons.LEFT;
	public static final int MOUSE_RIGHT  = Buttons.RIGHT;

}

