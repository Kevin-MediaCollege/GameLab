package gamelab.world.chunk;

import gamelab.tile.Tile;

import com.snakybo.sengine.components.Camera;
import com.snakybo.sengine.core.utils.Bounds;
import com.snakybo.sengine.core.utils.Vector2f;

/** @author Kevin Krol
 * @since May 28, 2014 */
public class ChunkChecker implements Runnable {
	private static Thread chunkChecker;
	
	private ChunkProvider chunkProvider;
	private Camera camera;
	
	public ChunkChecker(ChunkProvider chunkProvider, Camera camera) {
		this.chunkProvider = chunkProvider;
		this.camera = camera;
		
		if(chunkChecker == null) {
			chunkChecker = new Thread(this, "chunkChecker");
			chunkChecker.start();
		}
	}
	
	@Override
	public void run() {
		while(chunkChecker.isAlive()) {
			if(camera.getTransform().hasChanged())
				continue;
			
			final Bounds bounds = camera.toBounds();
			
			for(Chunk chunk : chunkProvider.getChunkList()) {
				final Tile[] tiles = chunk.getTiles();
				
				boolean changed = false;
				
				for(Tile tile : tiles) {
					final Vector2f pos = tile.getPosition();
					
					if(pos.getX() >= bounds.getLeft() && (pos.getX() + Tile.TILE_WIDTH) <= bounds.getRight()) {
						if(pos.getY() >= bounds.getBottom() && (pos.getY() + Tile.TILE_HEIGHT) <= bounds.getTop()) {
							changed = true; 
							
							if(!chunk.isLoaded()) {
								chunk.load();
								break;
							}
						}
					}
				}
				
				if(!changed && chunk.isLoaded())
					chunk.unload();
			}
		}
	}	
}