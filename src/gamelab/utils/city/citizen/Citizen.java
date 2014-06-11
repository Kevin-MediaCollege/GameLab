package gamelab.utils.city.citizen;

import gamelab.resource.Resource;
import gamelab.tile.Tile;
import gamelab.utils.city.Building;
import gamelab.utils.rendering.SpriteSheet;
import gamelab.world.World;

import java.util.ArrayList;
import java.util.List;

import com.snakybo.sengine.core.Component;
import com.snakybo.sengine.core.utils.Vector2f;
import com.snakybo.sengine.core.utils.Vector2i;
import com.snakybo.sengine.core.utils.Vector3f;
import com.snakybo.sengine.rendering.Texture;

/** @author Kevin Krol
 * @since Jun 2, 2014 */
public class Citizen extends Component {
	public static final int FLAG_IS_AT_HOME = 0x00;
	public static final int FLAG_RETURN_TO_HOME = 0x01;
	public static final int FLAG_FIND_RESOURCE = 0x02;
	public static final int FLAG_MOVE_TO_RESOURCE = 0x03;
	public static final int FLAG_GATHER_RESOURCE = 0x04;
	public static final int FLAG_STORE_RESOURCE = 0x05;
	public static final int FLAG_PLANT_RESOURCE = 0x06;
	
	public static final SpriteSheet CITIZENS_SPRITESHEET = new SpriteSheet(new Texture("citizens.png"), 13, 1);
	
	public static final float CITIZEN_LAYER = 100;
	
	public static final int CITIZEN_WIDTH = 18;
	public static final int CITIZEN_HEIGHT = 23;
	
	private Building home;
	
	private List<Tile> availableTiles;
	private Tile targetTile;
	
	private float attempts;
	
	private int flag;
	
	private boolean harvest;
	
	public Citizen(Building home) {
		availableTiles = new ArrayList<Tile>();
		
		this.home = home;
		
		flag = FLAG_IS_AT_HOME;
		attempts = 0;
	}
	
	@Override
	protected void update(float delta) {
		if(flag == FLAG_IS_AT_HOME)
			isAtHome(delta);
		
		if(flag == FLAG_RETURN_TO_HOME)
			returnToHome();
		
		if(flag == FLAG_FIND_RESOURCE)
			findResource();
		
		if(flag == FLAG_MOVE_TO_RESOURCE)
			moveToResource();
		
		if(flag == FLAG_GATHER_RESOURCE)
			gatherResource();
		
		if(flag == FLAG_STORE_RESOURCE)
			storeResource();
		
		if(flag == FLAG_PLANT_RESOURCE)
			plantResource();
	}
	
	/** Recalculate the radius of this citizen */
	public void recalculateRadius() {
		availableTiles.clear();
		
		final int startX = -(home.getSize() / 2);
		final int startY = -(home.getSize() / 2);
		final int endX = (home.getSize() / 2) + 1;
		final int endY = (home.getSize() / 2) + 1;
		
		final Vector2i position = getTransform().getPosition().getXY().toVector2i();
		final World world = home.getCity().getWorld();
		
		for(int x = startX; x < endX; x++) {
			for(int y = startY; y < endY; y++) {
				Tile tile = world.getTileAt(position.getX() + (x * Tile.TILE_WIDTH), position.getY() + (y * Tile.TILE_HEIGHT));
				
				if(tile != null)
					availableTiles.add(tile);
			}
		}
	}
	
	/** Run as long as the citizen is at home */
	private void isAtHome(float delta) {
		//System.out.println("Citizen is at home");
		
		attempts += delta;

		if(attempts >= 2.5f) {
			attempts = 0;
			flag = FLAG_FIND_RESOURCE;
		}
	}
	
	/** Run as long as the citizen should return to home */
	private void returnToHome() {
		//System.out.println("Citizen should return to home");
		
		if(targetTile != null)
			targetTile.stopUsing();
		
		final Vector3f position = getTransform().getPosition();
		final Vector3f homePosition = getTransform().getPosition();
		
		if(position.getX() != homePosition.getX() || position.getY() != homePosition.getY())
			getTransform().getPosition().set(homePosition.getX(), homePosition.getY(), position.getZ());
		
		flag = FLAG_IS_AT_HOME;

	}
	
	/** Run as long as the citizen should find a resource */
	private void findResource() {
		System.out.println("Citizen should find a resource");
		
		List<Tile> selectable = new ArrayList<Tile>();
		Tile tileToHarvest = null;		
		
		targetTile = null;
		harvest = false;
		
		recalculateRadius();
		
		for(Tile tile : availableTiles)
			if(!tile.isBeingUsed())
				if(tile.getTileId() == Tile.GRASS)
					selectable.add(tile);
		
		for(Tile tile : selectable) {
			if(tile.getResource() == null) {
				targetTile = tile;
				break;
			} else {
				if(tile.getResource().canHarvest())
					tileToHarvest = tile;
			}
		}
		
		if(targetTile == null && tileToHarvest != null) {
			targetTile = tileToHarvest;
			harvest = true;
		}
		
		if(targetTile != null) {
			flag = FLAG_MOVE_TO_RESOURCE;
			targetTile.use(this);
		} else {
			flag = FLAG_RETURN_TO_HOME;
		}
	}
	
	/** Run as long as the citizen should move to a resource */
	private void moveToResource() {
		final Vector2f position = getTransform().getPosition().getXY();
		final Vector2f tilePosition = targetTile.getGameObject().getTransform().getPosition().getXY();
		
		if(position.distance(tilePosition) > 1) {
			final Vector3f target = tilePosition.sub(position).normalize().toVector3f();
			target.setZ(0);
			
			getTransform().translate(target);
		} else {
			flag = (harvest) ? FLAG_GATHER_RESOURCE : FLAG_PLANT_RESOURCE;
		}
	}
	
	/** Run as long the citizen should gather a resource */
	private void gatherResource() {
		// TODO: Citizen should gather a resource
		System.out.println("Citizen should gather a resource");
		
		if(targetTile != null)
			targetTile.getResource().harvest(this);
		
		flag = FLAG_FIND_RESOURCE;
	}
	
	/** Run as long as the citizen should plant a resource */
	private void plantResource() {
		// TODO: Properly plant resources
		System.out.println("Citizen should plant a resource");
		
		targetTile.addResource(Resource.TREE);
		
		flag = FLAG_FIND_RESOURCE;
		
		targetTile.stopUsing();
	}
	
	/** Run as long as the citizen should store a resource */
	private void storeResource() {
		// TODO: Citizen should store a resource at the warehouse
		//System.out.println("Citizen should store a resource at the warehouse");
		
		targetTile.stopUsing();
	}
}
