package gamelab;

import gamelab.player.InputHandler;
import gamelab.utils.city.City;
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
		
		Camera camera = Camera.initOrthographicCamera(0, Window.getWidth(), 0, Window.getHeight(), -32, 32);
		FreeMove freeMove = new FreeMove(300, KeyCode.NONE, KeyCode.NONE, KeyCode.A, KeyCode.D, KeyCode.W, KeyCode.S);
		
		world = new World(camera);
		
		addChild(new GameObject(camera, freeMove, new InputHandler(camera, world)));
		
		world.start();
		
		City city = new City(world);
		
		city.addBuilding();
		
		//enableLeapMotion();
	}
	
	@Override
	protected void update(float delta) {
		super.update(delta);
		
		//System.out.println(Time.getFps());
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