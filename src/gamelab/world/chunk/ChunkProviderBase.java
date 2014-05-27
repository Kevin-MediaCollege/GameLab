package gamelab.world.chunk;

import gamelab.world.World;
import gamelab.world.noise.PerlinNoise;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Random;

/** @author Kevin Krol
 * @since May 20, 2014 */
public class ChunkProviderBase extends ChunkProvider {
	private volatile List<Chunk> chunks;
	
	private Random random;
	
	private PerlinNoise perlinNoise;
	
	private World world;
	
	public ChunkProviderBase(World world, long seed) {
		super();
		
		this.world = world;
		
		chunks = new ArrayList<Chunk>();
		
		random = new Random(seed);
		perlinNoise = new PerlinNoise(random.nextInt());
		
		chunkProviderThread.start();
	}
	
	@Override
	public void run() {
		while(chunkProviderThread.isAlive()) {
			try {	
				for(Chunk chunk : chunks) {
					if(!chunk.isVisible(world.getCamera().toBounds())) {
						if(chunk.isChunkLoaded())
							chunk.onChunkUnload();
					} else {
						if(!chunk.isChunkLoaded())
							chunk.onChunkLoad();
					}
				}
				
				Thread.sleep(5);
			} catch(ConcurrentModificationException e) {
			} catch(InterruptedException e) {}
		}
	}
	
	@Override
	public boolean chunkExists(int x, int y) {
		return false;
	}

	@Override
	public Chunk provideChunk(int x, int y) {
		random.setSeed((long)x * 996746212733L + (long)y * 837299812787L);
		
		Chunk chunk = new Chunk(world, x, y);
		
		generateTerrain(chunk);
		
		chunk.onChunkLoad();
		chunks.add(chunk);
		
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
			
				final int textureId = random.nextInt((12 - 10) + 1) + 10;
				
				chunk.setTileAt(tileX, tileY, textureId);
			}
			
			chunk.populate(this);
		}
	}
	
	@Override
	public Chunk getChunkAt(int x, int y) {
		if((float)x / 2 + 1 >= 16)
			x += 1;
		
		if((float)y / 2 + 1 >= 16)
			y += 1;
		
		x = (x / 2) >> 4;
		y = (y / 2) >> 4;
				
		for(Chunk chunk : chunks) {
			final int chunkX = chunk.getChunkX();
			final int chunkY = chunk.getChunkY();
						
			if(x == chunkX && y == chunkY)
				return chunk;
		}
		
		return null;
	}
}
