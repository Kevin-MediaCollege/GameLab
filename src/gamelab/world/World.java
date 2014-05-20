package gamelab.world;

import gamelab.city.City;
import gamelab.utils.rendering.SpriteSheet;
import gamelab.utils.tile.Tile;
import gamelab.world.chunk.Chunk;
import gamelab.world.chunk.ChunkProviderBase;
import gamelab.world.chunk.IChunkProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** @author Kevin Krol
 * @since May 12, 2014 */
public class World {
	private List<City> loadedCities;
	
	private List<Chunk> chunks;
	
	private Random random;
	
	private IChunkProvider chunkProvider;
	
	public World() {
		chunks = new ArrayList<Chunk>();
		
		random = new Random();
		chunkProvider = new ChunkProviderBase(this, 173483481276L);
		
		chunkProvider.provideChunk(0, 0);
	}
	
	public boolean spawnCityInWorld(City city) {
		return false;
	}
	
	public boolean updateTileSpriteAt(int x, int y, SpriteSheet spriteSheet, int spriteId) {
		return false;
	}
	
	public boolean tileHasBuilding(int x, int y) {
		return false;
	}
	
	public boolean chunkExists(int x, int y) {
		return false;
	}
	
	public boolean setTile(int x, int y, SpriteSheet spriteSheet, int spriteId) {
		return false;
	} 
	
	public Chunk getChunkFromTileCoords(int x, int y) {
		return getChunkAt(x >> 4, y >> 4);
	}
	
	public Chunk getChunkAt(int x, int y) {
		return null;
	}
	
	public Tile getTileAt(int x, int y) {
		return null;
	}
	
	public City getCityFromBuildingAt(int x, int y) {
		return null;
	}
	
	public City getCityAt(int x, int y) {
		return null;
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
