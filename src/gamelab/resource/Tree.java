package gamelab.resource;

import gamelab.utils.city.citizen.Citizen;
import gamelab.utils.rendering.SpriteSheet;

import com.snakybo.sengine.rendering.Texture;

/** @author Kevin Krol
 * @since Jun 6, 2014 */
public class Tree extends Resource {
	private static final SpriteSheet TREE_SPRITESHEET = new SpriteSheet(new Texture("trees.png"), 8, 1);
	
	private static final float TIME_UNTIL_GROWTH = 30f;
	
	private static final int MAX_STAGE = 7;
	
	private static final int TREE_WIDTH = 70;
	private static final int TREE_HEIGHT = 120;
	
	private float timer;
	
	private int stage;
	
	public Tree(int x, int y) {
		this.spriteSheet = TREE_SPRITESHEET;
		this.x = x;
		this.y = y;
		this.spriteId = 1;
		this.width = TREE_WIDTH;
		this.height = TREE_HEIGHT;
		
		this.timer = 0f;
		this.stage = 1;
	}
	
	@Override
	protected void update(float delta) {	
		if(stage < MAX_STAGE) {
			timer += delta;
			
			if(timer >= TIME_UNTIL_GROWTH) {
				timer = 0;
				
				stage++;
				renderer.setActiveSprite(stage);
			}
		}
	}
	
	@Override
	public void harvest(Citizen citizen) {
		this.stage = 1;
		
		renderer.setActiveSprite(stage);
		
		// TODO: Add resource to citizen's inventory
	}
	
	@Override
	public boolean canHarvest() {
		return stage == MAX_STAGE;
	}
}
