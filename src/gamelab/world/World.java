package gamelab.world;

import gamelab.tile.Tile;
import gamelab.world.chunk.Chunk;
import gamelab.world.chunk.ChunkProvider;

import java.util.Random;

import com.snakybo.sengine.components.Camera;

/** @author Kevin Krol
 * @since May 12, 2014 */
public class World {
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
	
	public void setTile(Chunk chunk, int tileId, int x, int y) {
		if(chunk.getTile(x, y) == null) {
			Tile tile = new Tile(tileId, x, y);
			
			chunk.setTile(x * Chunk.CHUNK_SIZE + y, tile);
		}
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
