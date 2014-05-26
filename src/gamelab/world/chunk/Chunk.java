package gamelab.world.chunk;

import gamelab.tile.Tile;

import com.snakybo.sengine.components.Camera;
import com.snakybo.sengine.core.utils.Vector2f;

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
	
	public boolean isChunkLoaded() {
		return isChunkLoaded;
	}
	
	public boolean isChunkPopulated() {
		return isChunkPopulated;
	}
	
	public boolean isVisible(Camera camera) {
		final Vector2f cameraPosition = camera.getTransform().getPosition().getXY();
		final Vector2f cameraSize = camera.getOrthoSize();
				
		for(Tile tile : tileStorage) {
			final Vector2f tilePosition = tile.getTile().getTransform().getPosition().getXY();
			final Vector2f tileSize = tile.getTile().getTransform().getLocalScale().getXZ();
			
			final float tileX1 = tilePosition.getX() + (tileSize.getX() / 2);
			final float tileX2 = tilePosition.getX() - (tileSize.getX() / 2);
			final float tileY1 = tilePosition.getY() + (tileSize.getY() / 2);
			final float tileY2 = tilePosition.getY() - (tileSize.getY() / 2);
			
			final float cameraX1 = cameraPosition.getX();
			final float cameraX2 = cameraPosition.getX() + cameraSize.getX();
			final float cameraY1 = cameraPosition.getY();
			final float cameraY2 = cameraPosition.getY() + cameraSize.getY();
			
			if(tileX1 >= cameraX1 && tileX2 <= cameraX2)
				if(tileY1 >= cameraY1 && tileY2 <= cameraY2)
					return true;
		}
		
		return false;
	}
	
	public boolean setTileAt(int x, int y, int spriteId) {
		final int index = x * CHUNK_SIZE + y;
		
		if(tileStorage[index] != null)
			return false;
		
		tileStorage[index] = new Tile(x + (chunkX * CHUNK_SIZE), y + (chunkY * CHUNK_SIZE), spriteId);
		
		return true;
	}
	
	public Tile getTileInChunk(int x, int y) {		
		System.out.println(Math.abs(x) + " " + Math.abs(y) + " " + (Math.abs(x) >> 1) * CHUNK_SIZE + (Math.abs(y) >> 1));
		
		return null;//return tileStorage[(x >> 1) * CHUNK_SIZE + (y >> 1)];
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