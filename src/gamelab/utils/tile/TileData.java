package gamelab.utils.tile;

import gamelab.utils.rendering.SpriteSheet;

import com.snakybo.sengine.rendering.Texture;

/** @author Kevin Krol
 * @since May 12, 2014 */
public class TileData {
	public static final int TILE_WIDTH = 32;
	public static final int TILE_HEIGHT = 32;
	
	public static final class Grass {
		public static final SpriteSheet spriteSheet = new SpriteSheet(
			new Texture("tiles/spriteSheet_grass.png"),
			10,
			2
		);
	}
	
	public static final class Dirt {
		public static final SpriteSheet spriteSheet = new SpriteSheet(
			new Texture("tiles/spriteSheet_dirt.png"),
			10,
			2
		);
	}
	
	public static final class Cities {
		public static final SpriteSheet spriteSheet = new SpriteSheet(
			new Texture("props/spriteSheet_cities.png"),
			1,
			1
		);		
	}
}
