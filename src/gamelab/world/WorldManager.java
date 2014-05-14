package gamelab.world;

import gamelab.utils.Perlin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.snakybo.sengine.core.Game;
import com.snakybo.sengine.core.Time;

/** @author Kevin Krol
 * @since May 12, 2014 */
public class WorldManager {
	private List<Chunk> chunks;
	
	private Game game;
	
	private Random random;
	
	public WorldManager(Game game) {
		chunks = new ArrayList<Chunk>();
		
		this.game = game;
		
		random = new Random();
	}
	
	public void generate() {
		random.setSeed((long)Time.getTime());
		
		for(int y = -2; y < 2; y++) {
			for(int x = -2; x < 2; x++) {
				Chunk chunk = new Chunk(x, y);
				
				chunk.generate(game, new Perlin(random.nextInt()));
				
				chunks.add(chunk);
			}
		}
	}
}
