package gamelab.world;

import java.util.ArrayList;
import java.util.List;

import com.snakybo.sengine.core.Game;

/** @author Kevin Krol
 * @since May 12, 2014 */
public class WorldManager {
	private List<Chunk> chunks;
	
	public WorldManager() {
		chunks = new ArrayList<Chunk>();
	}
	
	public void generate() {
		for(int y = -5; y < 5; y++) {
			for(int x = -5; x < 5; x++) {
				Chunk chunk = new Chunk(x, y);
				
				chunk.generateBase();
				
				chunks.add(chunk);
			}
		}
	}
}
