package gamelab.city;

import gamelab.world.Tiles;

import com.snakybo.sengine.components.MeshRenderer;
import com.snakybo.sengine.core.Game;
import com.snakybo.sengine.core.GameObject;
import com.snakybo.sengine.core.utils.Quaternion;
import com.snakybo.sengine.core.utils.Vector3f;
import com.snakybo.sengine.rendering.Material;
import com.snakybo.sengine.rendering.Mesh;
import com.snakybo.sengine.rendering.Texture;
import com.snakybo.sengine.rendering.Window;

/** @author Kevin Krol
 * @since May 13, 2014 */
public class CityManager {
	private Game game;
	
	public CityManager(Game game) {
		this.game = game;
		
		setPlayerCity();
	}
	
	private void setPlayerCity() {
		GameObject gameObject = new GameObject();
		Material material = new Material();
		
		material.addTexture("diffuse", new Texture("props/cities/size_1.png"));
		
		float sizeX = 1.0f / (Window.getHeight() / Tiles.TILE_HEIGHT);
		float sizeY = 1.0f / (Window.getWidth() / Tiles.TILE_WIDTH);
		
		gameObject.addComponent(new MeshRenderer(new Mesh("plane.obj"), material));
		gameObject.addComponent(new City());
		
		gameObject.getTransform().getLocalScale().set(sizeX, 1, sizeY);
		gameObject.getTransform().setRotation(new Quaternion(new Vector3f(1, 0, 0), (float)Math.toRadians(270)));
		gameObject.getTransform().rotate(new Vector3f(0, 0, 1), (float)Math.toRadians(90));
		
		game.addChild(gameObject);
	}
}
