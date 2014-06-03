package gamelab.utils.city.citizen;

import gamelab.tile.Tile;
import gamelab.utils.city.Building;
import gamelab.utils.rendering.SpriteRenderer;

import java.util.ArrayList;
import java.util.List;

import com.snakybo.sengine.core.Component;
import com.snakybo.sengine.core.GameObject;

/** @author Kevin Krol
 * @since Jun 2, 2014 */
public class Citizen extends Component {
	public static final int FLAG_IS_AT_HOME = 0x00;
	public static final int FLAG_RETURN_TO_HOME = 0x01;
	public static final int FLAG_FIND_RESOURCE = 0x02;
	public static final int FLAG_MOVE_TO_RESOURCE = 0x03;
	public static final int FLAG_GATHER_RESOURCE = 0x04;
	public static final int FLAG_STORE_RESOURCE = 0x05;
	
	private Building home;
	
	private Tile targetTile;
	
	private int flags;
	
	public Citizen(GameObject home) {
		this.home = home.getComponent(Building.class);
		
		flags = FLAG_IS_AT_HOME;
	}
	
	@Override
	protected void update(float delta) {
		if((flags & FLAG_IS_AT_HOME) == FLAG_IS_AT_HOME)
			isAtHome();
		
		if((flags & FLAG_RETURN_TO_HOME) == FLAG_RETURN_TO_HOME)
			returnToHome();
		
		if((flags & FLAG_FIND_RESOURCE) == FLAG_FIND_RESOURCE)
			findResource();
		
		if((flags & FLAG_MOVE_TO_RESOURCE) == FLAG_MOVE_TO_RESOURCE)
			moveToResource();
		
		if((flags & FLAG_GATHER_RESOURCE) == FLAG_GATHER_RESOURCE)
			gatherResource();
		
		if((flags & FLAG_STORE_RESOURCE) == FLAG_STORE_RESOURCE)
			storeResource();
	}
	
	private void isAtHome() {
		// TODO: Citizen is at home
		//System.out.println("Citizen is at home");
		flags = FLAG_FIND_RESOURCE;
	}
	
	private void returnToHome() {
		// TODO: Citizen should return to home
		System.out.println("Citizen should return to home");
	}
	
	private void findResource() {
		// TODO: Citizen shoulld find a resource
		//System.out.println("Citizen should find a resource");
		
		List<Tile> tiles = new ArrayList<Tile>();
		
		for(int x = -1; x < 2; x++) {
			for(int y = -1; y < 2; y++) {
				int xPos = (int)(getTransform().getPosition().getX());
				int yPos = (int)(getTransform().getPosition().getY());
				
				//System.out.println(xPos + " " + yPos);
				
				tiles.add(home.getCity().getWorld().getTileAt(xPos, yPos));
			}
		}
		
		for(Tile tile : tiles)
			tile.getGameObject().getComponent(SpriteRenderer.class).setActiveSprite(0);
	}
	
	private void moveToResource() {
		// TODO: Citizen should move to a resource
		System.out.println("Citizen should move to a resource");
	}
	
	private void gatherResource() {
		// TODO: Citizen should gather a resource
		System.out.println("Citizen should gather a resource");
	}
	
	private void storeResource() {
		// TODO: Citizen should store a resource at the warehouse
		System.out.println("Citizen should store a resource at the warehouse");
	}
	
	public void setFlags(int flags) {
		this.flags = flags;
	}
}
