package gamelab.world;

import gamelab.utils.Perlin;
import gamelab.utils.renderer.SpriteRenderer;

import com.snakybo.sengine.core.Game;
import com.snakybo.sengine.core.GameObject;
import com.snakybo.sengine.core.utils.Quaternion;
import com.snakybo.sengine.core.utils.Vector3f;

/** @author Kevin Krol
 * @since May 13, 2014 */
public class Chunk {
	public static final int CHUNK_SIZE = 5;
	
	public static final float PERLIN_X_DIVIDER = 8f;
	public static final float PERLIN_Y_DIVIDER = 8f;
	public static final float PERLIN_MULTIPLIER = 9f;
	public static final int PERLIN_FREQENCY = 2;
	
	private GameObject[][] tiles;
	
	private int x;
	private int y;
	
	public Chunk(int x, int y) {
		tiles = new GameObject[CHUNK_SIZE][CHUNK_SIZE];
		
		this.x = x;
		this.y = y;
	}
	
	public void generate(Game game, Perlin perlin) {
		for(int tileZ = 0; tileZ < CHUNK_SIZE; tileZ++) {
			for(int tileX = 0; tileX < CHUNK_SIZE; tileX++) {
				int textureId = (int)Math.abs(Math.floor(
					perlin.turbulence2(
						tileX / PERLIN_X_DIVIDER, 
						tileZ / PERLIN_Y_DIVIDER, 
						PERLIN_FREQENCY
					) * PERLIN_MULTIPLIER));
				
				float tilePosX = (tileX * Tiles.TILE_WIDTH) + (x * Tiles.TILE_WIDTH * CHUNK_SIZE);
				float tilePosY = (tileZ * Tiles.TILE_HEIGHT) + (y * Tiles.TILE_HEIGHT * CHUNK_SIZE);
				
				GameObject tile = new GameObject();
				
				tile.addComponent(new SpriteRenderer(Tiles.grassSprites, textureId));
				
				tile.getTransform().getLocalPosition().set(tilePosX, tilePosY, 0);
				
				tile.getTransform().setRotation(new Quaternion(new Vector3f(1, 0, 0), (float)Math.toRadians(270)));
				//tile.getTransform().rotate(new Vector3f(0, 0, 1), (float)Math.toRadians(90));
				
				tile.getTransform().getLocalScale().set(Tiles.TILE_WIDTH, 0, Tiles.TILE_HEIGHT);
				
				game.addChild(tile);
				
				tiles[tileX][tileZ] = tile;
			}
		}
	}
}