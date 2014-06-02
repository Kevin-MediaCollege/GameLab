package gamelab.world.chunk;

import gamelab.tile.Tile;
import gamelab.tile.TileDirt;

import com.snakybo.sengine.core.utils.Bounds;
import com.snakybo.sengine.core.utils.Vector2f;

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
			if(tile != null)
				tile.load();
	}
	
	public void unload() {
		isLoaded = false;
		
		for(Tile tile : tileStorage)
			if(tile != null)
				tile.unload();
	}
	
	public Bounds toBounds() {
		return new Bounds(chunkX * (CHUNK_SIZE * Tile.TILE_WIDTH), chunkY * (CHUNK_SIZE * Tile.TILE_HEIGHT), (chunkX + 1) * (CHUNK_SIZE * Tile.TILE_WIDTH), (chunkY + 1) * (CHUNK_SIZE * Tile.TILE_HEIGHT));
	}
	
	public boolean isLoaded() {
		return isLoaded;
	}
	
	public boolean setTile(int x, int y, int tileId) {
		int xPos = (chunkX * CHUNK_SIZE) + x;
		int yPos = (chunkY * CHUNK_SIZE) + y;
		
		Tile tile = null;
		
		switch(tileId) {
		case Tile.DIRT:
			tile = new TileDirt(xPos, yPos);
		}
		
		tileStorage[x * Chunk.CHUNK_SIZE + y] = tile;
		
		return true;
	}
	
	public Tile getTileAt(int x, int y) {
		x = (x + 1) >> 1 & 0xF;
		y = (y + 1) >> 1 & 0xF;
		
		for(Tile tile : tileStorage)
			if(tile.getPosition().equals(new Vector2f(x, y)))
				return tile;
		
		return null;
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