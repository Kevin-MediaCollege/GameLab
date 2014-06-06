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
	
	private int size;
	
	public Building(int rawX, int rawY) {
		this.rawX = rawX;
		this.rawY = rawY;
		
		size = 1;
	}
	
	public void init(City city) {
		citizens = new ArrayList<Citizen>();
		
		this.city = city;
		
		addCitizens();
	}
	
	@Override
	protected void update(float delta) {
		
	}
	
	private void recalculateCitizenRadius() {
		for(Citizen citizen : citizens)
			citizen.recalculateRadius();
	}
	
	private void addCitizens() {
		Citizen citizen = CitizenManager.addCitizen(getParent()).getComponent(Citizen.class);
		
		citizen.recalculateRadius();
		
		citizens.add(citizen);
	}
	
	public Vector2i getPosition() {
		return new Vector2i(rawX, rawY);
	}
	
	public City getCity() {
		return city;
	}
	
	public int getSize() {
		return size;
	}
}
