package gamelab.utils.city.citizen;

import gamelab.tile.Tile;
import gamelab.utils.city.Building;
import gamelab.world.World;

import java.util.ArrayList;
import java.util.List;

import com.snakybo.sengine.core.Component;
import com.snakybo.sengine.core.GameObject;
import com.snakybo.sengine.core.utils.Vector2i;

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
	
	private List<Tile> availableTiles;
	private Tile targetTile;
	
	private int flags;
	private int attempts;
	
	public Citizen(GameObject home) {
		availableTiles = new ArrayList<Tile>();
		
		this.home = home.getComponent(Building.class);
		
		flags = FLAG_IS_AT_HOME;
		attempts = 0;
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
	
	public void recalculateRadius() {
		availableTiles.clear();
		
		final int startX = -(home.getSize() / 2);
		final int startY = -(home.getSize() / 2);
		final int endX = (home.getSize() / 2) + 1;
		final int endY = (home.getSize() / 2) + 1;
		
		final Vector2i position = getTransform().getPosition().getXY().toVector2i();
		final World world = home.getCity().getWorld();
		
		for(int x = startX; x < endX; x++)
			for(int y = startY; y < endY; y++)
				availableTiles.add(world.getTileAt(
						position.getX() + (x * Tile.TILE_WIDTH),
						position.getY() + (y * Tile.TILE_HEIGHT)
					));
	}
	
	private void isAtHome() {
		// TODO: Citizen is at home
		//System.out.println("Citizen is at home");
		
		flags = FLAG_FIND_RESOURCE;
	}
	
	private void returnToHome() {
		// TODO: Citizen should return to home
		//System.out.println("Citizen should return to home");
	}
	
	private void findResource() {
		//System.out.println("Citizen should find a resource");
		
		targetTile = null;
		
		for(Tile tile : availableTiles) {
			if(tile.getTileId() == Tile.GRASS && !tile.isBeingUsed()) {
				tile.use(this);
				targetTile = tile;
				break;
			}
		}
		
		if(targetTile == null) {
			attempts++;
			
			if(attempts >= 5)
				flags = FLAG_RETURN_TO_HOME;
		} else {
			flags = FLAG_MOVE_TO_RESOURCE;
		}
	}
	
	private void moveToResource() {
		// TODO: Citizen should move to a resource
		//System.out.println("Citizen should move to a resource");
	}
	
	private void gatherResource() {
		// TODO: Citizen should gather a resource
		//System.out.println("Citizen should gather a resource");
	}
	
	private void storeResource() {
		// TODO: Citizen should store a resource at the warehouse
		//System.out.println("Citizen should store a resource at the warehouse");
	}
	
	public void setFlags(int flags) {
		this.flags = flags;
	}
}
