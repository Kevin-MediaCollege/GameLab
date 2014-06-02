package gamelab.utils.city.citizen;

import gamelab.TestGame;
import gamelab.utils.city.Building;
import gamelab.utils.rendering.SpriteRenderer;
import gamelab.utils.rendering.SpriteSheet;

import com.snakybo.sengine.core.GameObject;
import com.snakybo.sengine.core.utils.Quaternion;
import com.snakybo.sengine.core.utils.Vector3f;
import com.snakybo.sengine.rendering.Texture;

/** @author Kevin Krol
 * @since Jun 2, 2014 */
public class Citizen {
	private static final SpriteSheet CITIZENS_SPRITESHEET = new SpriteSheet(new Texture("citizens.png"), 8, 8);
	
	private final GameObject citizen;
	
	private Building home;
	
	public Citizen(Building home) {
		this.home = home;
		
		citizen = new GameObject();
		
		citizen.addComponent(new SpriteRenderer(CITIZENS_SPRITESHEET, 0));
		
		citizen.getTransform().setPosition(new Vector3f(home.getBuilding().getTransform().getPosition().getX(), home.getBuilding().getTransform().getPosition().getY(), 1.5f));
		citizen.getTransform().setRotation(new Quaternion(new Vector3f(1, 0, 0), (float)Math.toRadians(270)));
		citizen.getTransform().setScale(new Vector3f(16, 0, 16));
		
		citizen.getTransform().rotate(new Vector3f(0, 0, 1), (float)Math.toRadians(180));

		TestGame.instance.addChild(citizen);
	}
	
	public void returnToHome() {
		
	}
	
	public void gatherResources() {
		
	}
}
