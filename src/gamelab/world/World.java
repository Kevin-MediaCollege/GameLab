package gamelab.world;

import gamelab.world.chunk.Chunk;
import gamelab.world.chunk.ChunkProviderBase;
import gamelab.world.chunk.IChunkProvider;

import java.util.Random;

import com.snakybo.sengine.components.Camera;

/** @author Kevin Krol
 * @since May 12, 2014 */
public class World {
	private IChunkProvider chunkProvider;
	private Random random;
	private Camera camera;	
	
	public World(Camera camera) {		
		this.camera = camera;
		
		random = new Random();
	}
	
	public void start() {
		chunkProvider = new ChunkProviderBase(this, random.nextLong());
		
		for(int x = 0; x < 5; x++)
			for(int y = 0; y < 5; y++)
				chunkProvider.provideChunk(x, y);
	}
	
	public Chunk getChunkFromTileCoords(int x, int y) {
		return chunkProvider.getChunkAt(x, y);
	}
	
	public Chunk getChunkAt(int x, int y) {
		return chunkProvider.getChunkAt(x, y);
	}
	
	public Camera getCamera() {
		return camera;
	}
}
