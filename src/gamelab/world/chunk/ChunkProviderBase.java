package gamelab.world.chunk;

import gamelab.world.World;
import gamelab.world.noise.PerlinNoise;

import java.util.Random;

/** @author Kevin Krol
 * @since May 20, 2014 */
public class ChunkProviderBase implements IChunkProvider {	
	private Random random;
	
	private PerlinNoise perlinNoise;
	
	private World world;
	
	public ChunkProviderBase(World world, long seed) {
		this.world = world;
		
		random = new Random(seed);
		
		perlinNoise = new PerlinNoise(random.nextInt());
	}
	
	@Override
	public boolean chunkExists(int x, int y) {
		return false;
	}

	@Override
	public Chunk provideChunk(int x, int y) {
		random.setSeed((long)x * 996746212733L + (long)y * 837299812787L);
		
		Chunk chunk = new Chunk(x, y);
		
		generateTerrain(chunk);
		
		return chunk;
	}
	
	@Override
	public void loadChunk(int x, int y) {}

	@Override
	public void unloadQueuedChunks() {}
	
	private void generateTerrain(Chunk chunk) {
		if(!chunk.isChunkPopulated() && !chunkExists(chunk.getChunkX(), chunk.getChunkY())) {
			for(int i = 0; i < chunk.getTileStorageLength(); i++) {
				final int tileX = i & 0xF;
				final int tileY = i >> 4;
			
				chunk.setTileAt(tileX, tileY, 1);
			}
			
			chunk.populate(this);
		}
	}
	
	@Override
	public int getLoadedChunkCount() {
		return 0;
	}
}
