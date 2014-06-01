package gamelab.tile;

/** @author Kevin Krol
 * @since May 28, 2014 */
public class TileDirt extends Tile {
	private static int[] spriteIds = new int[] {10, 11, 12};
	
	public TileDirt(int x, int y) {
		super(Tile.DIRT, x, y);
		
		updateSprite(getRandomSpriteId(spriteIds));
	}
}