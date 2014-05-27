package gamelab.tile;

import gamelab.TestGame;
import gamelab.utils.rendering.SpriteRenderer;
import gamelab.utils.rendering.SpriteSheet;
import gamelab.world.World;
import gamelab.world.chunk.Chunk;

import com.snakybo.sengine.core.GameObject;
import com.snakybo.sengine.core.utils.Bounds;
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
	public static final int TILE_Z_LAYER = 0;
	
	final private GameObject tile;
	
	private World world;
	
	public Tile(World world, int x, int y, int spriteId) {
		this.tile = new GameObject();
		
		tile.addComponent(new SpriteRenderer(TILES, spriteId));
		
		tile.getTransform().getLocalPosition().set(x * TILE_WIDTH, y * TILE_HEIGHT, TILE_Z_LAYER);
		
		tile.getTransform().setRotation(new Quaternion(new Vector3f(1, 0, 0), (float)Math.toRadians(270)));
		tile.getTransform().rotate(new Vector3f(0, 0, 1), (float)Math.toRadians(180));
		tile.getTransform().getLocalScale().set(TILE_WIDTH, 0, TILE_HEIGHT);
		
		TestGame.instance.addChild(tile);
	}
	
	public void setToGrass() {
		final Vector2f position = tile.getTransform().getLocalPosition().getXY();
		
		SpriteRenderer renderer = tile.getComponent(SpriteRenderer.class);
		
		renderer.setActiveSprite(24);
		
		Chunk chunk = world.getChunkFromTileCoords((int)position.getX(), (int)position.getY());
		
		//Tile topTile = chunk.getTileInChunk((int)position.getX(), (int)position.getY());
		
		//if(topTile.getSprite() != 24) {
		//	topTile.setToGrass();
		//}
	}
	
	public void onLoad() {
		tile.getComponent(SpriteRenderer.class).setEnabled(true);
	}
	
	public void onUnload() {
		tile.getComponent(SpriteRenderer.class).setEnabled(false);
	}
	
	public GameObject getTile() {
		return tile;
	}
	
	public Bounds getBounds() {
		final Vector2f position = tile.getTransform().getPosition().getXY();
		final Vector2f size = tile.getTransform().getLocalScale().getXZ();
		
		Bounds result = new Bounds(0, 0, 0, 0);
		
		result.setLeft(position.getX());
		result.setBottom(position.getY());
		result.setRight(position.getX() + size.getX());
		result.setTop(position.getY() + size.getY());
		
		return result;
	}
	
	public int getSprite() {
		return tile.getComponent(SpriteRenderer.class).getActiveSprite();
	}
}