package gamelab.world.chunk;

import gamelab.utils.tile.Tile;

/** @author Kevin Krol
 * @since May 13, 2014 */
public class Chunk {
	public static final int CHUNK_SIZE = 16;
	
	private Tile[] tileStorage;
	//private City[] cities;
	
	private int chunkX;
	private int chunkY;
	
	private boolean isChunkLoaded;
	private boolean isChunkPopulated;
	
	public Chunk(int chunkX, int chunkY) {
		tileStorage = new Tile[CHUNK_SIZE * CHUNK_SIZE];
		
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
	
	public boolean isAtLocation(int x, int y) {
		return (chunkX == x) && (chunkY == y);
	}
	
	public boolean isChunkLoaded() {
		return isChunkLoaded;
	}
	
	public boolean isChunkPopulated() {
		return isChunkPopulated;
	}
	
	public boolean setTileAt(int x, int y, int spriteId) {
		if(tileStorage[x * y] != null)
			return false;
		
		tileStorage[x * y] = new Tile(x, y, spriteId);
		
		return true;
	}
	
	public Tile getTileInChunk(int x, int y) {
		return tileStorage[x * y];
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