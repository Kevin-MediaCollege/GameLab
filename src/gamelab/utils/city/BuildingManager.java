package gamelab.utils.city;

import gamelab.TestGame;
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
public class BuildingManager {
	private static final SpriteSheet BUILDING_SPRITESHEET = new SpriteSheet(new Texture("buildings.png"), 1, 1);
	
	private static final int BUILDING_WIDTH = 64;
	private static final int BUILDING_HEIGHT = 64;
	
	private static List<GameObject> buildings = new ArrayList<GameObject>();
	
	public static Building addBuilding(City city) {
		GameObject buildingGo = new GameObject();
		Building building = new Building(2, 2);
		
		buildingGo.addComponent(new SpriteRenderer(BUILDING_SPRITESHEET, 0));
		buildingGo.addComponent(building);
		
		buildingGo.getTransform().setPosition(new Vector3f(5 * BUILDING_WIDTH - (BUILDING_WIDTH / 2), 5 * BUILDING_HEIGHT - (BUILDING_HEIGHT / 2), 1));
		buildingGo.getTransform().setRotation(new Quaternion(new Vector3f(1, 0, 0), (float)Math.toRadians(270)));
		buildingGo.getTransform().setScale(new Vector3f(BUILDING_WIDTH, 0, BUILDING_HEIGHT));
		
		buildingGo.getTransform().rotate(new Vector3f(0, 0, 1), (float)Math.toRadians(180));

		TestGame.instance.addChild(buildingGo);
		
		buildings.add(buildingGo);
		
		building.init(city);
		
		return building;
	}
	
	public static GameObject getBuilding(Building building) {
		for(GameObject b : buildings)
			if(b.getComponent(Building.class) == building)
				return b;
		
		return null;
	}
}
