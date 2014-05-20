package gamelab.world.chunk;

import gamelab.world.World;
import gamelab.world.noise.PerlinNoise;

import java.util.ArrayList;
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
	public Chunk provideChunk(int x, int y) {
		random.setSeed((long)x * 996746212733L + (long)y * 837299812787L);
		
		generateTerrain(x, y);
		
		Chunk chunk = new Chunk(x, y);
		
		return null;
	}

	@Override
	public void loadChunk(int x, int y) {}

	@Override
	public void unloadQueuedChunks() {}
	
	private void generateTerrain(int x, int y) {
		
	}
	
	@Override
	public int getLoadedChunkCount() {
		return 0;
	}
}
