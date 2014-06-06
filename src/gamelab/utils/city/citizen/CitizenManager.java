package gamelab.utils.city.citizen;

import gamelab.GameLab;
import gamelab.utils.rendering.SpriteRenderer;
import gamelab.utils.rendering.SpriteSheet;

import java.util.ArrayList;
import java.util.List;

import com.snakybo.sengine.core.GameObject;
import com.snakybo.sengine.core.utils.Quaternion;
import com.snakybo.sengine.core.utils.Vector3f;
import com.snakybo.sengine.rendering.Texture;

/** @author Kevin Krol
 * @since Jun 3, 2014 */
public class CitizenManager {
	private static final SpriteSheet CITIZENS_SPRITESHEET = new SpriteSheet(new Texture("citizens.png"), 13, 1);
	
	private static final int CITIZEN_WIDTH = 18;
	private static final int CITIZEN_HEIGHT = 23;
	
	private static List<GameObject> citizens = new ArrayList<GameObject>();;
	
	public static GameObject addCitizen(GameObject home) {
		final Vector3f position = home.getTransform().getPosition();
		
		GameObject citizen = new GameObject();
		
		citizen.addComponent(new SpriteRenderer(CITIZENS_SPRITESHEET, 0));
		citizen.addComponent(new Citizen(home));
		
		citizen.getTransform().setPosition(new Vector3f(position.getX(), position.getY(), 1.5f));
		citizen.getTransform().setRotation(new Quaternion(new Vector3f(1, 0, 0), (float)Math.toRadians(270)));
		citizen.getTransform().setScale(new Vector3f(CITIZEN_WIDTH, 0, CITIZEN_HEIGHT));
		
		citizen.getTransform().rotate(new Vector3f(0, 0, 1), (float)Math.toRadians(180));

		GameLab.instance.addChild(citizen);
		
		citizens.add(citizen);
		
		return citizen;
	}
}
