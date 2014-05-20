package gamelab.utils.tile;

import gamelab.TestGame;
import gamelab.utils.rendering.SpriteRenderer;
import gamelab.world.chunk.Chunk;

import com.snakybo.sengine.core.GameObject;
import com.snakybo.sengine.core.utils.Quaternion;
import com.snakybo.sengine.core.utils.Vector3f;

/** @author Kevin Krol
 * @since May 20, 2014 */
public class Tile {
	public static final int TILE_Z_LAYER = -31;
	
	final private GameObject tile;
	
	public Tile(int x, int y, int spriteId) {
		this.tile = new GameObject();
		
		tile.addComponent(new SpriteRenderer(TileData.TILES, 0));
		
		tile.getTransform().getLocalPosition().set(x * Chunk.CHUNK_SIZE, y * Chunk.CHUNK_SIZE, TILE_Z_LAYER);
		
		tile.getTransform().setRotation(new Quaternion(new Vector3f(1, 0, 0), (float)Math.toRadians(270)));
		tile.getTransform().rotate(new Vector3f(0, 0, 1), (float)Math.toRadians(180));
		tile.getTransform().getLocalScale().set(TileData.TILE_WIDTH, 0, TileData.TILE_HEIGHT);
		
		TestGame.instance.addChild(tile);
	}
	
	public void onLoad() {
		tile.getComponent(SpriteRenderer.class).setEnabled(true);
	}
	
	public void onUnload() {
		tile.getComponent(SpriteRenderer.class).setEnabled(false);
	}
}