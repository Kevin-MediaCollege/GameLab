package gamelab.tile;

import gamelab.TestGame;
import gamelab.utils.rendering.SpriteRenderer;
import gamelab.utils.rendering.SpriteSheet;

import com.snakybo.sengine.core.GameObject;
import com.snakybo.sengine.core.utils.Quaternion;
import com.snakybo.sengine.core.utils.Vector2f;
import com.snakybo.sengine.core.utils.Vector3f;
import com.snakybo.sengine.rendering.Texture;

/** @author Kevin Krol
 * @since May 20, 2014 */
public class Tile {
	public static final SpriteSheet TILES = new SpriteSheet(new Texture("tiles.png"), 10, 10);
	
	public static final int TILE_WIDTH = 32;
	public static final int TILE_HEIGHT = 32;
	
	public static final int DIRT = 0;
	public static final int GRASS = 1;
	
	private final GameObject tile;
	private final SpriteRenderer renderer;
	private final int tileId;
	
	public Tile(int tileId, int x, int y) {		
		this.tileId = tileId;
		this.tile = new GameObject();
		this.renderer = new SpriteRenderer(TILES, 0);
		
		tile.addComponent(renderer);

		tile.getTransform().setPosition(new Vector3f(x * TILE_WIDTH, y * TILE_WIDTH, 0));
		tile.getTransform().setRotation(new Quaternion(new Vector3f(1, 0, 0), (float)Math.toRadians(270)));
		tile.getTransform().setScale(new Vector3f(TILE_WIDTH, 0, TILE_HEIGHT));
		
		tile.getTransform().rotate(new Vector3f(0, 0, 1), (float)Math.toRadians(180));

		TestGame.instance.addChild(tile);
	}
	
	public void load() {
		renderer.setEnabled(true);
	}
	
	public void unload() {
		renderer.setEnabled(false);
	}
	
	public void updateSprite(int spriteId) {
		renderer.setActiveSprite(spriteId);
	}
	
	public Vector2f getPosition() {
		Vector2f position = tile.getTransform().getPosition().getXY();
		
		return new Vector2f(position.getX() / TILE_WIDTH, position.getY() / TILE_HEIGHT);
	}
	
	public int getTileId() {
		return tileId;
	}
}