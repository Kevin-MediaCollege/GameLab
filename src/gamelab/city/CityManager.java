package gamelab.city;

import java.util.ArrayList;
import java.util.List;

/** @author Kevin Krol
 * @since May 13, 2014 */
public class CityManager {
	private List<City> cities;
	
	public CityManager() {
		cities = new ArrayList<City>();
	}
	
	public void createBanditCamp() {
		
	}
	
	public void createPlayerCity() {
		cities.add(new City(true));
	}
	
	public void createAiCity() {
		
	}
}
