package gamelab.resource;

import gamelab.GameLab;
import gamelab.utils.rendering.SpriteRenderer;
import gamelab.utils.rendering.SpriteSheet;

import java.util.Random;

import com.snakybo.sengine.core.GameObject;
import com.snakybo.sengine.core.utils.Quaternion;
import com.snakybo.sengine.core.utils.Vector3f;

/** @author Kevin Krol
 * @since Jun 6, 2014 */
public class Resource {
	public static final int TREE = 0;
	
	private final GameObject resource;
	private final SpriteRenderer renderer;
	
	public Resource(SpriteSheet spriteSheet, int x, int y, int width, int height) {
		this.resource = new GameObject();
		this.renderer = new SpriteRenderer(spriteSheet, new Random().nextInt(spriteSheet.getRows() * spriteSheet.getCols()));
		
		resource.addComponent(renderer);
		
		resource.getTransform().setPosition(new Vector3f(x * width, y * height, 0));
		resource.getTransform().setRotation(new Quaternion(new Vector3f(1, 0, 0), (float)Math.toRadians(270)));
		resource.getTransform().setScale(new Vector3f(width, 0, height));
		
		resource.getTransform().rotate(new Vector3f(0, 0, 1), (float)Math.toRadians(180));

		GameLab.instance.addChild(resource);
	}
	
	public void load() {
		renderer.setEnabled(true);
	}
	
	public void unload() {
		renderer.setEnabled(false);
	}
}
