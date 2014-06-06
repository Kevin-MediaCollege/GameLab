package gamelab.resource;

import gamelab.utils.rendering.SpriteSheet;

import com.snakybo.sengine.rendering.Texture;

/** @author Kevin Krol
 * @since Jun 6, 2014 */
public class Tree extends Resource{
	private static final SpriteSheet TREE_SPRITESHEET = new SpriteSheet(new Texture("trees.png"), 8, 1);
	
	private static final int TREE_WIDTH = 120;
	private static final int TREE_HEIGHT = 70;
	
	public Tree(int x, int y) {
		super(TREE_SPRITESHEET, x, y, TREE_WIDTH, TREE_HEIGHT);
	}
}
