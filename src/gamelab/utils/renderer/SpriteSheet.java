package gamelab.utils.renderer;

import com.snakybo.sengine.core.utils.Vector2f;
import com.snakybo.sengine.rendering.Texture;

/** @author Kevin Krol
 * @since May 14, 2014 */
public class SpriteSheet {
	private Texture texture;	
	private Vector2f size;
	
	public SpriteSheet(Texture texture, Vector2f size) {
		this.texture = texture;
		this.size = size;
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public Vector2f getSize() {
		return size;
	}
}
