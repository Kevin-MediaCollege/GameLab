package gamelab.world;

import gamelab.TestGame;
import gamelab.utils.rendering.SpriteSheet;
import gamelab.utils.rendering.SpriteSheetRenderer;

import java.util.Random;

import com.snakybo.sengine.core.GameObject;
import com.snakybo.sengine.core.utils.Quaternion;
import com.snakybo.sengine.core.utils.Vector2f;
import com.snakybo.sengine.core.utils.Vector3f;

/** @author Kevin Krol
 * @since May 13, 2014 */
public class Chunk {
	public static final int CHUNK_SIZE = 5;
	
	public static final float PERLIN_X_DIVIDER = 8f;
	public static final float PERLIN_Y_DIVIDER = 8f;
	public static final float PERLIN_MULTIPLIER = 7f;
	public static final int PERLIN_FREQENCY = 2;
	
	private GameObject[][] tiles;
	
	private int x;
	private int y;
	
	public Chunk(int x, int y) {
		tiles = new GameObject[CHUNK_SIZE][CHUNK_SIZE];
		
		this.x = x;
		this.y = y;
	}
	
	public void generateBase() {
		Random random = new Random();
		
		for(int tileZ = 0; tileZ < CHUNK_SIZE; tileZ++) {
			for(int tileX = 0; tileX < CHUNK_SIZE; tileX++) {
				Vector2f position = new Vector2f(
						(tileX * Tiles.TILE_WIDTH) + (x * Tiles.TILE_WIDTH * CHUNK_SIZE),
						(tileZ * Tiles.TILE_HEIGHT) + (y * Tiles.TILE_HEIGHT * CHUNK_SIZE)
					);
				
				String texture = "base-" + Integer.toString(random.nextInt(6 - 1) + 1);
				
				tiles[tileX][tileZ] = createTile(Tiles.Dirt.spriteSheet, texture, position);
			}
		}
	}
	
	private GameObject createTile(SpriteSheet spriteSheet, String texture, Vector2f position) {
		GameObject tile = new GameObject();
		
		tile.addComponent(new SpriteSheetRenderer(spriteSheet, texture));
		
		tile.getTransform().getLocalPosition().set(position.getX(), position.getY(), -31);
		tile.getTransform().setRotation(new Quaternion(new Vector3f(1, 0, 0), (float)Math.toRadians(270)));
		tile.getTransform().getLocalScale().set(spriteSheet.getSprite(texture).getSize().getX(), 0, spriteSheet.getSprite(texture).getSize().getY());
		
		TestGame.instance.addChild(tile);
		
		return tile;
	}
}