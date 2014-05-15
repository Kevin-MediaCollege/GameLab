package gamelab.city;

import com.snakybo.sengine.components.MeshRenderer;
import com.snakybo.sengine.core.Game;
import com.snakybo.sengine.core.GameObject;
import com.snakybo.sengine.core.utils.Vector2f;
import com.snakybo.sengine.core.utils.Vector3f;
import com.snakybo.sengine.rendering.Material;
import com.snakybo.sengine.rendering.Mesh;
import com.snakybo.sengine.rendering.Texture;
import com.snakybo.sengine.rendering.Vertex;

/** @author Kevin Krol
 * @since May 13, 2014 */
public class CityManager {
	private Game game;
	
	public CityManager(Game game) {
		this.game = game;
		
		setPlayerCity();
	}
	
	private void setPlayerCity() {
		Vertex[] vertices = new Vertex[] {
			new Vertex(new Vector3f(-0.5f, 0, -0.5f), new Vector2f(0, 0)),
			new Vertex(new Vector3f(-0.5f, 0,  0.5f), new Vector2f(0, 1)),
			new Vertex(new Vector3f( 0.5f, 0, -0.5f), new Vector2f(1, 0)),
			new Vertex(new Vector3f( 0.5f, 0,  0.5f), new Vector2f(1, 1))
		};
		
		int indices[] = {
			0, 1, 2,
			2, 1, 3
		};
		
		GameObject gameObject = new GameObject();
		Material material = new Material();
		
		material.addTexture("diffuse", new Texture("props/small_hut1.png"));
		
		gameObject.addComponent(new MeshRenderer(new Mesh(vertices, indices, true), material));
		gameObject.addComponent(new City());
		
		gameObject.getTransform().getLocalPosition().set(0, 15, 0);
		gameObject.getTransform().getLocalScale().set(37, 1, 53);
		
		game.addChild(gameObject);
		
		
	}
}
