package gamelab.world.chunk;

import gamelab.tile.Tile;
import gamelab.world.World;

import com.snakybo.sengine.core.utils.Bounds;

/** @author Kevin Krol
 * @since May 13, 2014 */
public class Chunk {
	public static final int CHUNK_SIZE = 16;
	
	private Tile[] tileStorage;
	//private City[] cities;
	
	private World world;
	
	private int chunkX;
	private int chunkY;
	
	private boolean isChunkLoaded;
	private boolean isChunkPopulated;
	
	public Chunk(World world, int chunkX, int chunkY) {
		tileStorage = new Tile[CHUNK_SIZE * CHUNK_SIZE];
		
		this.world = world;
		
		this.chunkX = chunkX;
		this.chunkY = chunkY;
		
		isChunkLoaded = false;
		isChunkPopulated = false;
	}
	
	public void onChunkLoad() {
		isChunkLoaded = true;
		
		for(Tile tile : tileStorage)
			tile.onLoad();
	}
	
	public void onChunkUnload() {
		isChunkLoaded = false;
		
		for(Tile tile : tileStorage)
			tile.onUnload();
	}
	
	public void populate(IChunkProvider chunkProvider) {
		
	}
	
	public boolean isChunkLoaded() {
		return isChunkLoaded;
	}
	
	public boolean isChunkPopulated() {
		return isChunkPopulated;
	}
	
	public boolean isVisible(Bounds bounds) {
		bounds.setLeft(bounds.getLeft() - Tile.TILE_WIDTH);
		bounds.setRight(bounds.getRight() + (2 * Tile.TILE_WIDTH));
		bounds.setBottom(bounds.getBottom() - Tile.TILE_HEIGHT);
		bounds.setTop(bounds.getTop() + Tile.TILE_HEIGHT);
		
		for(Tile tile : tileStorage) {
			final Bounds tileBounds = tile.getBounds();
			
			if(tileBounds.getLeft() >= (bounds.getLeft() - Tile.TILE_WIDTH) && tileBounds.getRight() <= (bounds.getRight() + (2 * Tile.TILE_WIDTH)))
				if(tileBounds.getBottom() >= bounds.getBottom() && tileBounds.getTop() <= bounds.getTop())
					return true;
		}
		
		return false;
	}
	
	public boolean setTileAt(int x, int y, int spriteId) {
		final int index = x * CHUNK_SIZE + y;
		
		if(tileStorage[index] != null)
			return false;
		
		tileStorage[index] = new Tile(world, x + (chunkX * CHUNK_SIZE), y + (chunkY * CHUNK_SIZE), spriteId);
		
		return true;
	}
	
	public Tile getTileInChunk(int x, int y) {
		x = (x + 1) >> 1 & 0xF;
		y = (y + 1) >> 1 & 0xF;
		
		return tileStorage[x * CHUNK_SIZE + y];
	}
	
	public int getChunkX() {
		return chunkX;
	}
	
	public int getChunkY() {
		return chunkY;
	}
	
	public int getTileStorageLength() {
		return tileStorage.length;
	}
}