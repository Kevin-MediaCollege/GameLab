package gamelab;

import gamelab.city.CityManager;
import gamelab.world.WorldManager;

import com.snakybo.sengine.components.Camera;
import com.snakybo.sengine.components.FreeMove;
import com.snakybo.sengine.core.Component;
import com.snakybo.sengine.core.CoreEngine;
import com.snakybo.sengine.core.Game;
import com.snakybo.sengine.core.GameObject;
import com.snakybo.sengine.core.Input;
import com.snakybo.sengine.core.Input.KeyCode;
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
		
	}
}