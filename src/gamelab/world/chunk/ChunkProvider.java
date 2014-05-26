package gamelab.world.chunk;

/** @author Kevin Krol
 * @since May 22, 2014 */
public abstract class ChunkProvider implements Runnable, IChunkProvider {
	public static final String CHUNK_PROVIDER_THREAD_NAME = "chunkProvider";
	
	protected static Thread chunkProviderThread;
	
	public ChunkProvider() {
		if(chunkProviderThread == null)
			chunkProviderThread = new Thread(this, CHUNK_PROVIDER_THREAD_NAME);
	}
}
