package gamelab.world;

import gamelab.world.chunk.Chunk;
import gamelab.world.chunk.ChunkProvider;

import java.util.Random;

import com.snakybo.sengine.components.Camera;

/** @author Kevin Krol
 * @since May 12, 2014 */
public class World {
	public static final int MIN_WORLD_X = -5000;
	public static final int MIN_WORLD_Y = -5000;
	public static final int MAX_WORLD_X = 5000;
	public static final int MAX_WORLD_Y = 5000;
	
	private ChunkProvider chunkProvider;
	private Random random;
	private Camera camera;	
	
	public World(Camera camera) {		
		this.camera = camera;
		
		random = new Random();
	}
	
	public void start() {
		chunkProvider = new ChunkProvider(this, random.nextLong());
		
		for(int x = 0; x < 1; x++)
			for(int y = 0; y < 1; y++)
				chunkProvider.provideChunk(x, y);
	}
	
	public boolean setTile(int x, int y, int tileId) {
		if(x >= MIN_WORLD_X && x <= MAX_WORLD_X && y >= MIN_WORLD_X && y <= MAX_WORLD_Y)
			return getChunkFromChunkCoords(x >> 4, y >> 4).setTile(x & 15, y & 15, tileId);
		
		return false;
	}
	
	public Chunk getChunkFromTileCoords(int x, int y) {
		return getChunkFromChunkCoords(x >> 4, y >> 4);
	}
	
	public Chunk getChunkFromChunkCoords(int x, int y) {
		return chunkProvider.getChunkAt(x, y);
	}
	
	public Chunk getChunkFromMouseCoords(int x, int y) {
		if((float)x / 2 + 1 >= 16)
			x += 1;
		
		if((float)y / 2 + 1 >= 16)
			y += 1;
		
		x = (x / 2) >> 4;
		y = (y / 2) >> 4;
		
		return chunkProvider.getChunkAt(x, y);
	}
	
	public Camera getCamera() {
		return camera;
	}
}
