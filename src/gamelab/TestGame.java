package gamelab;

import gamelab.world.World;

import com.snakybo.sengine.components.Camera;
import com.snakybo.sengine.components.FreeMove;
import com.snakybo.sengine.core.CoreEngine;
import com.snakybo.sengine.core.Game;
import com.snakybo.sengine.core.GameObject;
import com.snakybo.sengine.core.Input.KeyCode;
import com.snakybo.sengine.core.utils.Vector3f;
import com.snakybo.sengine.rendering.Window;

public class TestGame extends Game {
	public static TestGame instance;
	
	private World world;
	
	@Override
	protected void init(CoreEngine coreEngine) {
		TestGame.instance = this;
		
		coreEngine.getRenderingEngine().setAmbientLight(new Vector3f(1f, 1f, 1f));
		
		world = new World();
		//new Building(new City(false));
		
		GameObject camera = 
				//new GameObject(new FreeLook(0.5f), new FreeMove(25.0f, KeyCode.E, KeyCode.Q, KeyCode.A, KeyCode.D, KeyCode.W, KeyCode.S), Camera.initPerspectiveCamera((float)Math.toRadians(70.0f), (float)Window.getWidth() / (float)Window.getHeight(), 0.01f, 1000.0f));
				new GameObject(new FreeMove(300, KeyCode.NONE, KeyCode.NONE, KeyCode.A, KeyCode.D, KeyCode.W, KeyCode.S), Camera.initOrthographicCamera(0, Window.getWidth(), 0, Window.getHeight(), -32, 32));
		
		addChild(camera);
		
		/*new Tile(0, 0, 0);
		new Tile(1, 0, 0);
		new Tile(2, 0, 0);
		new Tile(3, 0, 0);
		new Tile(4, 0, 0);
		new Tile(5, 0, 0);
		
		/*GameObject go = new GameObject();
		
		go.addComponent(new SpriteRenderer(TileData.TILES, 0));
		
		
		addChild(go);*/
		
		
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