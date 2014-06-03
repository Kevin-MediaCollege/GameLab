package gamelab.utils.city;

import gamelab.world.World;

import java.util.ArrayList;
import java.util.List;

/** @author Kevin Krol
 * @since Jun 2, 2014 */
public class City {
	private static List<City> cities = new ArrayList<City>();
	
	private List<Building> buildings;
	
	private World world;
	
	private int size;
	
	public City(World world) {
		buildings = new ArrayList<Building>();
		
		this.world = world;
		this.size = 1;
		
		cities.add(this);
	}
	
	public void addBuilding() {
		buildings.add(BuildingManager.addBuilding(this));
	}
	
	public World getWorld() {
		return world;
	}
	
	public int getSize() {
		return size;
	}
}
