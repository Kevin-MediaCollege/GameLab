package gamelab.utils.city;

import gamelab.TestGame;
import gamelab.utils.city.citizen.Citizen;
import gamelab.utils.rendering.SpriteRenderer;

import com.snakybo.sengine.core.GameObject;
import com.snakybo.sengine.core.utils.Quaternion;
import com.snakybo.sengine.core.utils.Vector3f;

/** @author Kevin Krol
 * @since Jun 2, 2014 */
public class Building {
	private final GameObject building;
	private final SpriteRenderer renderer;
	
	private City city;
	
	private int size;
	
	public Building(City city, int x, int y) {
		this.city = city;
		this.building = new GameObject();
		this.renderer = new SpriteRenderer(City.BUILDING_SPRITESHEET, 0);
		
		building.addComponent(renderer);
		
		building.getTransform().setPosition(new Vector3f(x * 39, y * 55, 1));
		building.getTransform().setRotation(new Quaternion(new Vector3f(1, 0, 0), (float)Math.toRadians(270)));
		building.getTransform().setScale(new Vector3f(39, 0, 55));
		
		building.getTransform().rotate(new Vector3f(0, 0, 1), (float)Math.toRadians(180));

		TestGame.instance.addChild(building);
		
		size = 1;
		
		addCitizens();
	}
	
	private void addCitizens() {
		for(int i = 0; i < size; i++)
			new Citizen(this);
	}
	
	public GameObject getBuilding() {
		return building;
	}
}
