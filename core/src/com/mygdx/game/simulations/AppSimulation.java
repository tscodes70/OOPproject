package com.mygdx.game.simulations;

//import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.managers.PlayerControlManager;
import com.mygdx.game.models.Simulation;

public class AppSimulation extends Simulation {
    private int gameState;

	//audio testing
	//private static AssetManager manager;
	private PlayerControlManager pcm;

	//private SceneManager sm;
    @Override
    public void initialize() {
        super.initialize();
    	// Input Processor testing
		pcm = new PlayerControlManager();
		
//		sm = new SceneManager();	
//		sm.setScene(com.mygdx.game.globals.Constants.SPLASH_SCREEN);
		
        // Load specific resources for this simulation
		// resource loading has been moved to scene manager
    }

    @Override
    public void start() {
        super.start();
        // Begin specific simulation logic
    }

    // update simulation. currently called from simulation lifecycle manager
    @Override
    public void update() {
    	// testing game logic is currently here
    	
    	// press enter to move from splash screen to game
    	if(pcm.pollEnterKey() && gameState == com.mygdx.game.globals.Constants.SPLASH_SCREEN) {
    		gameState = com.mygdx.game.globals.Constants.GAME_SCREEN;
			sm.setScene(gameState);
		}
    	
    	// press right or left arrow to end game
    	if(pcm.pollRightKey() || pcm.pollLeftKey() && gameState == com.mygdx.game.globals.Constants.GAME_SCREEN) {
			gameState = com.mygdx.game.globals.Constants.END_SCREEN;
			sm.setScene(gameState);
    	}
    	
    	// press escape key to go back to splash screen
    	if(pcm.pollPauseKey() && gameState == com.mygdx.game.globals.Constants.END_SCREEN) {
			gameState = com.mygdx.game.globals.Constants.SPLASH_SCREEN;
			sm.setScene(gameState);
    	}
    	
    	// Render the things in simulation
    	// rendering moved to within scene
    	sm.renderScene();
    	
    	// print key events in console
    	pcm.checkKeyEvents();
    	// render(batch);
    	
    }
    
	//@Override
	public void render(SpriteBatch batch) {
		// Render things on screen
		// rendering currently moved inside scene
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void create() {
//		// TODO Auto-generated method stub
//		
//	}


    // ... other lifecycle methods

}
