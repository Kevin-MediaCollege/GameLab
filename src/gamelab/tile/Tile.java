package gamelab.tile;

import gamelab.GameLab;
import gamelab.resource.Resource;
import gamelab.resource.Tree;
import gamelab.utils.city.citizen.Citizen;
import gamelab.utils.rendering.SpriteRenderer;
import gamelab.utils.rendering.SpriteSheet;

import java.util.Random;

import com.snakybo.sengine.core.GameObject;
import com.snakybo.sengine.core.utils.Quaternion;
import com.snakybo.sengine.core.utils.Vector2f;
import com.snakybo.sengine.core.utils.Vector2i;
import com.snakybo.sengine.core.utils.Vector3f;
import com.snakybo.sengine.rendering.Texture;

/** @author Kevin Krol
 * @since May 20, 2014 */
public class Tile {
	public static final int TILE_WIDTH = 32;
	public static final int TILE_HEIGHT = 32;
	
	public static final int DIRT = 0;
	public static final int GRASS = 1;
	
	private static final SpriteSheet TILE_SPRITESHEET = new SpriteSheet(new Texture("tiles.png"), 10, 10);
	
	private final GameObject tile;
	private final SpriteRenderer renderer;
	private final int tileId;
	
	private Resource resource;
	private Citizen user;
	
	private int rawX;
	private int rawY;
	
	public Tile(int tileId, int x, int y, int rawX, int rawY) {
		this.tileId = tileId;
		this.tile = new GameObject();
		this.renderer = new SpriteRenderer(TILE_SPRITESHEET, 0);
		
		this.rawX = rawX;
		this.rawY = rawY;
		
		tile.addComponent(renderer);
		
		tile.getTransform().setPosition(new Vector3f(x * TILE_WIDTH, y * TILE_HEIGHT, 0));
		tile.getTransform().setRotation(new Quaternion(new Vector3f(1, 0, 0), (float)Math.toRadians(270)));
		tile.getTransform().setScale(new Vector3f(TILE_WIDTH, 0, TILE_HEIGHT));
		
		tile.getTransform().rotate(new Vector3f(0, 0, 1), (float)Math.toRadians(180));

		GameLab.instance.addChild(tile);
	}
	
	public void load() {
		renderer.setEnabled(true);
		
		if(resource != null)
			resource.load();
	}
	
	public void unload() {
		renderer.setEnabled(false);
		
		if(resource != null)
			resource.unload();
	}
	
	public void updateSprite(int spriteId) {
		renderer.setActiveSprite(spriteId);
	}
	
	public void use(Citizen citizen) {
		this.user = citizen;
	}
	
	public void addResource(int type) {
		final Vector2f position = getGameObject().getTransform().getPosition().getXY();
		
		switch(type) {
		case Resource.TREE:
			resource = new Tree((int)position.getX(), (int)position.getY());
			break;
		}
	}
	
	public void stopUsing() {
		user = null;
	}
	
	public boolean isBeingUsed() {
		return user != null;
	}
	
	public int getRandomSpriteId(int[] spriteIds) {
		Random random = new Random();
		
		return spriteIds[random.nextInt(spriteIds.length)];
	}
	
	public Vector2i getPosition() {
		return new Vector2i(rawX, rawY);
	}
	
	public int getTileId() {
		return tileId;
	}
	
	public GameObject getGameObject() {
		return tile;
	}
	
	public Resource getResource() {
		return resource;
	}
}