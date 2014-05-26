package gamelab.player;

import gamelab.world.World;
import gamelab.world.chunk.Chunk;

import com.snakybo.sengine.components.Camera;
import com.snakybo.sengine.core.Component;
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
		final Vector2f mouseWorld = camera.mouseToWorld();
		
		world.getChunkAt((int)mouseWorld.getX(), (int)mouseWorld.getY()).onChunkUnload();
	}
}
