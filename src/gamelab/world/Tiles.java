package gamelab.world;

import gamelab.utils.rendering.Sprite;
import gamelab.utils.rendering.SpriteSheet;

import com.snakybo.sengine.core.utils.Vector2f;
import com.snakybo.sengine.rendering.Texture;

/** @author Kevin Krol
 * @since May 12, 2014 */
public class Tiles {
	public static final int TILE_WIDTH = 32;
	public static final int TILE_HEIGHT = 32;
	
	public static final class Grass {
		// Sprite name constants
		public static final String CORNER_OUTER_LEFT_UP = "corner-outer-left-up";			// 0
		public static final String CORNER_OUTER_RIGHT_UP = "corner-outer-right-up";			// 2
		public static final String CORNER_OUTER_LEFT_DOWN = "corner-outer-left-down";		// 7
		public static final String CORNER_OUTER_RIGHT_DOWN = "corner-outer-right-down";		// 9
		
		public static final String CORNER_INNER_LEFT_UP = "corner-inner-left-up";			// 11
		public static final String CORNER_INNER_RIGHT_UP = "corner-inner-right-up";			// 12
		public static final String CORNER_INNER_LEFT_DOWN = "corner-inner-left-down";		// 13
		public static final String CORNER_INNER_RIGHT_DOWN = "corner-inner-right-down";		// 10
		
		public static final String UP = "up";				// 1
		public static final String DOWN = "down";			// 6
		public static final String LEFT = "left";			// 3
		public static final String RIGHT = "right";			// 5
		public static final String CENTER = "center";		// 4
		
		public static final String DECORATOR_1 = "decorator-1";		// 21
		public static final String DECORATOR_2 = "decorator-2";		// 22
		public static final String DECORATOR_3 = "decorator-3";		// 23
		public static final String DECORATOR_4 = "decorator-4";		// 24
		public static final String DECORATOR_5 = "decorator-5";		// 25
		public static final String DECORATOR_6 = "decorator-6";		// 26
		public static final String DECORATOR_7 = "decorator-7";		// 27
		
		// Sprite Sheet
		public static final SpriteSheet spriteSheet = new SpriteSheet(
			new Texture("tiles/spriteSheet_grass.png"),
			new Vector2f(10, 10),
			
			new Sprite[] {
				new Sprite(CENTER, 	new Vector2f(4, 0)),
				new Sprite(UP, 		new Vector2f(1, 0)),
				new Sprite(DOWN,	new Vector2f(7, 0)),
				new Sprite(LEFT,	new Vector2f(3, 0)),
				new Sprite(RIGHT,	new Vector2f(5, 0))
			}
		);
	}
	
	public static final class Dirt {
		// Sprite name constants
		public static final String BASE_1 = "base-1";		// 8
		public static final String BASE_2 = "base-2";		// 9
		public static final String BASE_3 = "base-3";		// 10
		public static final String BASE_4 = "base-4";		// 11
		public static final String BASE_5 = "base-5";		// 12
		
		public static final String DECORATOR_1 = "decorator-1";		// 0
		public static final String DECORATOR_2 = "decorator-2";		// 1
		public static final String DECORATOR_3 = "decorator-3";		// 2
		public static final String DECORATOR_4 = "decorator-4";		// 3
		public static final String DECORATOR_5 = "decorator-5";		// 4
		public static final String DECORATOR_6 = "decorator-6";		// 5
		public static final String DECORATOR_7 = "decorator-7";		// 6
		
		// Sprite Sheet
		public static final SpriteSheet spriteSheet = new SpriteSheet(
			new Texture("tiles/spriteSheet_dirt.png"),
			new Vector2f(10, 10),
			
			new Sprite[] {
				new Sprite(BASE_1, new Vector2f(7, 0)),
				new Sprite(BASE_2, new Vector2f(8, 0)),
				new Sprite(BASE_3, new Vector2f(9, 0)),
				new Sprite(BASE_4, new Vector2f(0, 1)),
				new Sprite(BASE_5, new Vector2f(1, 1))
			}
		);
	}
	
	public static final class Cities {
		// Sprite name constants
		public static final String SMALL_HUT_1 = "small-hut-1";		// 0
		
		// Sprite Sheet
		public static final SpriteSheet spriteSheet = new SpriteSheet(
			new Texture("props/spriteSheet_cities.png"),
			new Vector2f(1, 1),
			
			new Sprite[] {
				new Sprite(SMALL_HUT_1, new Vector2f(0, 0))
			}
		);		
	}
}
