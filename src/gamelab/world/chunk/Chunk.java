package gamelab.world.chunk;

import gamelab.tile.Tile;

/** @author Kevin Krol
 * @since May 13, 2014 */
public class Chunk {
	public static final int CHUNK_SIZE = 16;
	
	private Tile[] tileStorage;
	
	private int chunkX;
	private int chunkY;
	
	private boolean isLoaded;
	
	public Chunk(int chunkX, int chunkY) {
		tileStorage = new Tile[CHUNK_SIZE * CHUNK_SIZE];
		
		this.chunkX = chunkX;
		this.chunkY = chunkY;
		
		isLoaded = false;
	}
	
	public void load() {
		isLoaded = true;
		
		for(Tile tile : tileStorage)
			tile.load();
	}
	
	public void unload() {
		isLoaded = false;
		
		for(Tile tile : tileStorage)
			tile.unload();
	}
	
	public boolean isLoaded() {
		return isLoaded;
	}
	
	public void setTile(int index, Tile tile) {
		tileStorage[index] = tile;
	}
	
	public Tile getTile(int x, int y) {
	//	System.out.println("Before: " + x + " " + y);
		
		x = x >> 1 & 0xF;
		y = y >> 1 & 0xF;
		
	//	System.out.println("After: " + x + " " + y);
		
		return tileStorage[x * CHUNK_SIZE + y];
	}
	
	public Tile[] getTiles() {
		return tileStorage;
	}
	
	public int getX() {
		return chunkX;
	}
	
	public int getY() {
		return chunkY;
	}
	
	public int getTileStorageLength() {
		return tileStorage.length;
	}
}