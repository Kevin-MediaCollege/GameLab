package gamelab.world;

import gamelab.utils.renderer.SpriteSheet;

import com.snakybo.sengine.core.utils.Vector2f;
import com.snakybo.sengine.rendering.Texture;

/** @author Kevin Krol
 * @since May 12, 2014 */
public class Tiles {
	public static final int TILE_WIDTH = 32;
	public static final int TILE_HEIGHT = 32;
	
	public static final class Grass {
		public static final SpriteSheet spriteSheet = new SpriteSheet(
			new Texture("tiles/spriteSheet_grass.png"),
			new Vector2f(10, 10)
		);
		
		public static final int CORNER_OUTER_LEFT_UP = 0;
		public static final int CORNER_OUTER_RIGHT_UP = 2;
		public static final int CORNER_OUTER_LEFT_DOWN = 7;
		public static final int CORNER_OUTER_RIGHT_DOWN = 9;
		
		public static final int CORNER_INNER_LEFT_UP = 11;
		public static final int CORNER_INNER_RIGHT_UP = 12;
		public static final int CORNER_INNER_LEFT_DOWN = 13;
		public static final int CORNER_INNER_RIGHT_DOWN = 10;
		
		public static final int UP = 1;
		public static final int DOWN = 7;
		public static final int LEFT = 3;
		public static final int RIGHT = 5;
		public static final int CENTER = 4;
	}
}
