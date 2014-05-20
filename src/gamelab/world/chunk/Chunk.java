package gamelab.world.chunk;

import gamelab.city.City;
import gamelab.utils.tile.Tile;

/** @author Kevin Krol
 * @since May 13, 2014 */
public class Chunk {
	public static final int CHUNK_SIZE = 5;
	
	private Tile[] tileStorage;
	//private City[] cities;
	
	private int xPosition;
	private int yPosition;
	
	private boolean isChunkLoaded;
	private boolean isChunkPopulated;
	
	public Chunk(int xPosition, int yPosition) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		
		isChunkLoaded = false;
		isChunkPopulated = false;
	}
	
	public void onChunkLoad() {
		isChunkLoaded = true;
		
		for(Tile tile : tileStorage)
			tile.onLoad();
	}
	
	public void onChunkUnload() {
		isChunkLoaded = false;
		
		for(Tile tile : tileStorage)
			tile.onUnload();
	}
	
	public void populate() {
		
	}
	
	public Tile getTileInChunk(int x, int y) {
		return tileStorage[x * y];
	}
	
	public boolean isAtLocation(int x, int y) {
		return (xPosition == x) && (yPosition == y);
	}
	
	public boolean isChunkLoaded() {
		return isChunkLoaded;
	}
	
	public boolean isChunkPopulated() {
		return isChunkPopulated;
	}
	
	
	/*
	public void generateBase() {
		Random random = new Random();
		
		for(int tileY = 0; tileY < CHUNK_SIZE; tileY++) {
			for(int tileX = 0; tileX < CHUNK_SIZE; tileX++) {
				Vector2f position = new Vector2f(
						(tileX * Tiles.TILE_WIDTH) + (xPosition * Tiles.TILE_WIDTH * CHUNK_SIZE),
						(tileY * Tiles.TILE_HEIGHT) + (yPosition * Tiles.TILE_HEIGHT * CHUNK_SIZE)
					);
				
				 String texture = "base-" + Integer.toString(random.nextInt(6 - 1) + 1);
				
				tiles[tileX][tileY] = createTile(Tiles.Dirt.spriteSheet, texture, position);
			}
		}
	}
	
	private GameObject createTile(SpriteSheet spriteSheet, String texture, Vector2f position) {
		GameObject tile = new GameObject();
		
		tile.addComponent(new SpriteRenderer(spriteSheet, texture));
		
		tile.getTransform().getLocalPosition().set(position.getX(), position.getY(), -31);
		tile.getTransform().setRotation(new Quaternion(new Vector3f(1, 0, 0), (float)Math.toRadians(270)));
		tile.getTransform().getLocalScale().set(spriteSheet.getSprite(texture).getSize().getX(), 0, spriteSheet.getSprite(texture).getSize().getY());
		
		TestGame.instance.addChild(tile);
		
		return tile;
	}*/
}