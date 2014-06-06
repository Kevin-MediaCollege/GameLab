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

public class GameLab extends Game {
	public static GameLab instance;
	
	private World world;
	
	@Override
	protected void init(CoreEngine coreEngine) {
		GameLab.instance = this;
		
		coreEngine.getRenderingEngine().setAmbientLight(new Vector3f(1f, 1f, 1f));
		
		Camera camera = Camera.initOrthographicCamera(0, Window.getWidth(), 0, Window.getHeight(), -32, 32);
		
		world = new World(camera);
		
		addChild(new GameObject(
				camera,
				new FreeMove(300, KeyCode.NONE, KeyCode.NONE, KeyCode.A, KeyCode.D, KeyCode.W, KeyCode.S),
				new InputHandler(camera, world),
				new LeapInputReader()
			)
		);
		
		world.start();			
		
		City city = new City(world);
		
		city.addBuilding();
		
		new LeapInputManager();
	}
}