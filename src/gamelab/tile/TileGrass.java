package gamelab.tile;

/** @author Kevin Krol
 * @since Jun 1, 2014 */
public class TileGrass extends Tile {
	private static int[] spriteIds = new int[] {24};
	
	public TileGrass(int x, int y) {
		super(Tile.GRASS, x, y);
		
		updateSprite(getRandomSpriteId(spriteIds));
	}
}
