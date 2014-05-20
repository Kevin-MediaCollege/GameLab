package gamelab.utils.tile;

import gamelab.TestGame;
import gamelab.utils.rendering.SpriteRenderer;

import com.snakybo.sengine.core.GameObject;
import com.snakybo.sengine.core.Transform;
import com.snakybo.sengine.core.utils.Quaternion;
import com.snakybo.sengine.core.utils.Vector2f;
import com.snakybo.sengine.core.utils.Vector3f;

/** @author Kevin Krol
 * @since May 20, 2014 */
public class Tile {
	public static final int TILE_Z_LAYER = -31;
	
	final private GameObject tile;
	
	public Tile(int x, int y, SpriteRenderer spriteRenderer) {
		tile = new GameObject();
		
		final Transform tileTransform = tile.getTransform();
		final Vector2f spriteSize = spriteRenderer.getSpriteSheet().getSpriteSize();
		final Quaternion tileRotation = new Quaternion(new Vector3f(1, 0, 0), (float)Math.toRadians(270));
		
		tile.addComponent(spriteRenderer);
		
		tileTransform.getPosition().set(x, y, TILE_Z_LAYER);
		tileTransform.getRotation().set(tileRotation);
		tileTransform.getLocalScale().set(spriteSize.getX(), 0, spriteSize.getY());
		
		TestGame.instance.addChild(tile);
	}
	
	public void onLoad() {
		tile.getComponent(SpriteRenderer.class).setEnabled(true);
	}
	
	public void onUnload() {
		tile.getComponent(SpriteRenderer.class).setEnabled(false);
	}
}
