package gamelab.world;

import gamelab.utils.renderer.SpriteSheet;

import com.snakybo.sengine.core.utils.Vector2f;
import com.snakybo.sengine.rendering.Texture;

/** @author Kevin Krol
 * @since May 12, 2014 */
public class Tiles {
	public static final int TILE_WIDTH = 32;
	public static final int TILE_HEIGHT = 32;
	
	public static final SpriteSheet grassSprites = new SpriteSheet(
		new Texture("tiles/spritesheet_grass_tiles.png"),
		new Vector2f(7, 2)
	);
	
//	public static final Texture groundSprites = new Texture("tiles/spritesheet_ground_tiles.png");
}
