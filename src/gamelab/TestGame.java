package gamelab;

import gamelab.world.WorldManager;

import java.io.IOException;

import com.leapmotion.leap.Controller;
import com.snakybo.sengine.components.Camera;
import com.snakybo.sengine.components.FreeMove;
import com.snakybo.sengine.core.CoreEngine;
import com.snakybo.sengine.core.Game;
import com.snakybo.sengine.core.GameObject;
import com.snakybo.sengine.core.utils.Vector3f;

public class TestGame extends Game {
	private WorldManager worldManager;
	
	@Override
	protected void init(CoreEngine coreEngine) {
		coreEngine.getRenderingEngine().setAmbientLight(new Vector3f(1, 1, 1));
		
		worldManager = new WorldManager(this);
		//new CityManager(this);
		
		worldManager.generate();
		
		addChild(new GameObject(new FreeMove(250), Camera.initOrthographicCamera(0, 1280, 0, 720, -1, 1)));
		
		//enableLeapMotion();
	}
	
	private void enableLeapMotion() {
		// Create a sample listener and controller
        Leap listener = new Leap();
        Controller controller = new Controller();

        // Have the sample listener receive events from the controller
        controller.addListener(listener);

        // Keep this process running until Enter is pressed
        System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Remove the sample listener when done
        controller.removeListener(listener);
	}
}