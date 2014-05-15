package gamelab.utils.renderer;

import com.snakybo.sengine.core.utils.Vector2f;

/** @author Kevin Krol
 * @since May 15, 2014 */
public class Sprite {
	private String name;
	private Vector2f position;
	
	public Sprite(String name, int x, int y) {
		this.name = name;
		this.position = new Vector2f(x, y);
	}
	
	public String getName() {
		return name;
	}
	
	public Vector2f getPosition() {
		return position;
	}
}
