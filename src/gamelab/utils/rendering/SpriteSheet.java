package gamelab.utils.rendering;

import java.util.HashMap;
import java.util.Map;

import com.snakybo.sengine.core.utils.Vector2f;
import com.snakybo.sengine.rendering.Texture;

/** @author Kevin Krol
 * @since May 14, 2014 */
public class SpriteSheet {
	private Map<String, Sprite> sprites;
	
	private Texture texture;
	private Vector2f size;
	
	public SpriteSheet(Texture texture, Vector2f size, Sprite[] sprites) {
		this.texture = texture;
		this.size = size;
		
		this.sprites = new HashMap<String, Sprite>();
		
		for(Sprite sprite : sprites) {
			sprite.generateMesh(size);
			
			this.sprites.put(sprite.getName(), sprite);
		}
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public Vector2f getSize() {
		return size;
	}
	
	public Sprite getSprite(String name) {
		return sprites.get(name);
	}
}
