package gamelab.utils.city;

import gamelab.utils.rendering.SpriteSheet;

import java.util.ArrayList;
import java.util.List;

import com.snakybo.sengine.rendering.Texture;

/** @author Kevin Krol
 * @since Jun 2, 2014 */
public class City {
	public static final SpriteSheet BUILDING_SPRITESHEET = new SpriteSheet(new Texture("buildings.png"), 1, 1);
	
	private static List<City> cities = new ArrayList<City>();
	
	private List<Building> buildings;
	
	private int size;
	
	public City() {
		buildings = new ArrayList<Building>();
		
		size = 1;
		
		cities.add(this);
	}
	
	public void addBuilding() {
		Building building = new Building(this, 2, 2);
		
		buildings.add(building);
	}
}
