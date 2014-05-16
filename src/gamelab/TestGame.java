package gamelab;

import gamelab.city.CityManager;
import gamelab.world.WorldManager;

import com.snakybo.sengine.components.Camera;
import com.snakybo.sengine.components.FreeMove;
import com.snakybo.sengine.core.CoreEngine;
import com.snakybo.sengine.core.Game;
import com.snakybo.sengine.core.GameObject;
import com.snakybo.sengine.core.Input.KeyCode;
import com.snakybo.sengine.core.utils.Vector3f;

public class TestGame extends Game {
	public static TestGame instance;
	
	private WorldManager worldManager;
	private CityManager cityManager;
	
	@Override
	protected void init(CoreEngine coreEngine) {
		TestGame.instance = this;
		
		coreEngine.getRenderingEngine().setAmbientLight(new Vector3f(1f, 1f, 1f));
		
		worldManager = new WorldManager();
		worldManager.generate();
		
		cityManager = new CityManager();
		cityManager.createPlayerCity();
		
		GameObject camera = 
				//new GameObject(new FreeLook(0.5f), new FreeMove(25.0f, KeyCode.NONE, KeyCode.NONE, KeyCode.A, KeyCode.D, KeyCode.W, KeyCode.S), Camera.initPerspectiveCamera((float)Math.toRadians(70.0f), (float)Window.getWidth() / (float)Window.getHeight(), 0.01f, 1000.0f));
				new GameObject(new FreeMove(300, KeyCode.NONE, KeyCode.NONE, KeyCode.A, KeyCode.D, KeyCode.W, KeyCode.S), Camera.initOrthographicCamera(0, 960, 0, 540, -32, 32));
		
		addChild(camera);
		
		//enableLeapMotion();
	}
	
//	private void enableLeapMotion() {
//		// Create a sample listener and controller
//        Leap listener = new Leap();
//        Controller controller = new Controller();
//
//        // Have the sample listener receive events from the controller
//        controller.addListener(listener);
//
//        // Keep this process running until Enter is pressed
//        System.out.println("Press Enter to quit...");
//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // Remove the sample listener when done
//        controller.removeListener(listener);
//	}
}