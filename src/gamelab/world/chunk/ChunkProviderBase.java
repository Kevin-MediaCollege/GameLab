package gamelab.world.chunk;

import gamelab.world.World;

import java.util.ArrayList;
import java.util.List;

/** @author Kevin Krol
 * @since May 20, 2014 */
public class ChunkProviderBase implements IChunkProvider {
	private Chunk[] chunks;
	
	private List<Chunk> loadedChunks;
	private List<Chunk> unloadQueue;
	
	private World world;
	
	public ChunkProviderBase(World world) {
		loadedChunks = new ArrayList<Chunk>();
		unloadQueue = new ArrayList<Chunk>();
		
		this.world = world;
	}
	
	@Override
	public boolean chunkExists(int x, int y) {
		if(chunks[x * y] != null)
			return true;
		
		return false;
	}

	@Override
	public Chunk provideChunk(int x, int y) {
		return null;
	}

	@Override
	public void loadChunk(int x, int y) {}

	@Override
	public void unloadQueuedChunks() {
		for(Chunk chunk : unloadQueue)
			chunk.onChunkUnload();
		
		unloadQueue.clear();
	}

	@Override
	public int getLoadedChunkCount() {
		return loadedChunks.size();
	}
	
}
