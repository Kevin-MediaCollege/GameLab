package gamelab.player;

import gamelab.tile.Tile;
import gamelab.world.World;
import gamelab.world.chunk.Chunk;

import com.snakybo.sengine.components.Camera;
import com.snakybo.sengine.core.Component;
import com.snakybo.sengine.core.Input;
import com.snakybo.sengine.core.utils.Vector2f;

/** @author Kevin Krol
 * @since May 22, 2014 */
public class InputHandler extends Component {
	private Camera camera;
	private World world;
	
	public InputHandler(Camera camera, World world) {
		this.camera = camera;
		this.world = world;
	}
	
	@Override
	protected void input(float delta) {
		if(Input.getMouse(0)) {
			final Vector2f mouseWorld = camera.mouseToWorld();
			
			Chunk chunk = world.getChunkFromMouseCoords((int)mouseWorld.getX(), (int)mouseWorld.getY());
			
			if(chunk != null) {
				Tile tile = chunk.getTileAt((int)mouseWorld.getX(), (int)mouseWorld.getY());
				
				world.setTile((int)tile.getPosition().getX(), (int)tile.getPosition().getY(), Tile.GRASS);
			}
		}
	}
}
