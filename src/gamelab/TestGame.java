package gamelab;

import gamelab.city.CityManager;
import gamelab.world.WorldManager;

import com.snakybo.sengine.components.Camera;
import com.snakybo.sengine.components.DirectionalLight;
import com.snakybo.sengine.components.FreeLook;
import com.snakybo.sengine.components.FreeMove;
import com.snakybo.sengine.components.PointLight;
import com.snakybo.sengine.core.CoreEngine;
import com.snakybo.sengine.core.Game;
import com.snakybo.sengine.core.GameObject;
import com.snakybo.sengine.core.utils.Quaternion;
import com.snakybo.sengine.core.utils.Vector3f;
import com.snakybo.sengine.rendering.Attenuation;
import com.snakybo.sengine.rendering.Window;

public class TestGame extends Game {
	private WorldManager worldManager;
	
	@Override
	protected void init(CoreEngine coreEngine) {
		coreEngine.getRenderingEngine().setAmbientLight(new Vector3f(0.1f, 0.1f, 0.1f));
		
		worldManager = new WorldManager(this);
		worldManager.generate();
		
		new CityManager(this);
		
		//addChild(new GameObject(new FreeMove(300), Camera.initOrthographicCamera(0, 960, 0, 540, -1, 1)));
		addChild(new GameObject(new FreeLook(0.5f), new FreeMove(25.0f), Camera.initPerspectiveCamera((float)Math.toRadians(70.0f), (float)Window.getWidth() / (float)Window.getHeight(), 0.01f, 1000.0f)));
		
		GameObject pl = new GameObject(new PointLight(new Vector3f(0, 1, 0.5f), 1, new Attenuation(0, 0, 1)));
		
		pl.getTransform().getLocalPosition().set(0, 0, 0);
		pl.getTransform().getLocalScale().set(1, 1, 1);
		
		addChild(pl);
		
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