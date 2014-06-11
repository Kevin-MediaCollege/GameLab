package gamelab.resource;

import gamelab.GameLab;
import gamelab.tile.Tile;
import gamelab.utils.city.citizen.Citizen;
import gamelab.utils.rendering.SpriteRenderer;
import gamelab.utils.rendering.SpriteSheet;

import com.snakybo.sengine.core.Component;
import com.snakybo.sengine.core.GameObject;
import com.snakybo.sengine.core.utils.Quaternion;
import com.snakybo.sengine.core.utils.Vector3f;

/** @author Kevin Krol
 * @since Jun 6, 2014 */
public class Resource extends Component {
	public static final int TREE = 0;
	
	private static final float LAYER = 150;
	
	protected SpriteSheet spriteSheet;
	protected SpriteRenderer renderer;
	
	protected float x;
	protected float y;
	protected int spriteId;
	protected int width;
	protected int height;
	
	/** Called when the resource enters the camera's viewport */
	public void load() {
		renderer.setEnabled(true);
	}
	
	/** Called when the resource leaves the camera's viewport */
	public void unload() {
		renderer.setEnabled(false);
	}
	
	public void harvest(Citizen citizen) {}
	
	public boolean canHarvest() {
		return false;
	}
	
	public static Resource create(Resource resource) {
		GameObject resourceGo = new GameObject();
		SpriteRenderer renderer = new SpriteRenderer(resource.spriteSheet, resource.spriteId);
		
		resourceGo.addComponent(renderer);
		resourceGo.addComponent(resource);
		
		resourceGo.getTransform().setPosition(new Vector3f(resource.x, resource.y + Tile.TILE_HEIGHT, LAYER));
		resourceGo.getTransform().setRotation(new Quaternion(new Vector3f(1, 0, 0), (float)Math.toRadians(270)));
		resourceGo.getTransform().setScale(new Vector3f(resource.width, 0, resource.height));
		
		resourceGo.getTransform().rotate(new Vector3f(0, 0, 1), (float)Math.toRadians(180));

		GameLab.instance.addChild(resourceGo);
		
		resource.renderer = renderer;
		
		return resource;
	}
}
