package gamelab.utils.city;

import gamelab.utils.city.citizen.Citizen;
import gamelab.utils.city.citizen.CitizenManager;

import java.util.ArrayList;
import java.util.List;

import com.snakybo.sengine.core.Component;
import com.snakybo.sengine.core.utils.Vector2i;

/** @author Kevin Krol
 * @since Jun 2, 2014 */
public class Building extends Component {
	public static final int RADIUS_MULTIPLIER = 10;
	
	private List<Citizen> citizens;
	
	private City city;
	
	private int rawX;
	private int rawY;
	
	public Building(int rawX, int rawY) {
		this.rawX = rawX;
		this.rawY = rawY;
	}
	
	public void init(City city) {
		citizens = new ArrayList<Citizen>();
		
		this.city = city;
		
		addCitizens();
	}
	
	private void addCitizens() {
		citizens.add(CitizenManager.addCitizen(getParent()).getComponent(Citizen.class));
	}
	
	public Vector2i getPosition() {
		return new Vector2i(rawX, rawY);
	}
	
	public City getCity() {
		return city;
	}
}
