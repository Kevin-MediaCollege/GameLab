package gamelab.world;

import gamelab.world.chunk.Chunk;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** @author Kevin Krol
 * @since May 12, 2014 */
public class World {
	private List<Chunk> chunks;
	
	private Random random;
	
	public World() {
		chunks = new ArrayList<Chunk>();
		
		random = new Random();
	}
	
	/*public void generate() {
		for(int y = -5; y < 5; y++) {
			for(int x = -5; x < 5; x++) {
				Chunk chunk = new Chunk(x, y);
				
				chunk.generateBase();
				
				chunks.add(chunk);
			}
		}
	}*/
}
