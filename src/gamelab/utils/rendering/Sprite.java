package gamelab.utils.rendering;

import com.snakybo.sengine.core.utils.Vector2f;
import com.snakybo.sengine.core.utils.Vector3f;
import com.snakybo.sengine.rendering.Mesh;
import com.snakybo.sengine.rendering.Vertex;

/** @author Kevin Krol
 * @since May 15, 2014 */
public class Sprite {
	private String name;
	private Vector2f position;
	private Vector2f size;
	
	private Mesh mesh;
	
	public Sprite(String name, Vector2f position, Vector2f size) {
		this.name = name;
		this.position = position;
		this.size = size;
	}
	
	public void generateMesh(Vector2f spriteSheetSize) {
		final float x = position.getX() * (1.0f / spriteSheetSize.getX());
		final float y = position.getY() * (1.0f / spriteSheetSize.getY());
		final float w = 1.0f / spriteSheetSize.getX();
		final float h = 1.0f / spriteSheetSize.getY();
		
		Vertex[] vertices = new Vertex[] {
			new Vertex(new Vector3f(-0.5f, 0, -0.5f), new Vector2f(x,		y)),
			new Vertex(new Vector3f(-0.5f, 0,  0.5f), new Vector2f(x, 		y + h)),
			new Vertex(new Vector3f( 0.5f, 0, -0.5f), new Vector2f(x + w, 	y)),
			new Vertex(new Vector3f( 0.5f, 0,  0.5f), new Vector2f(x + w, 	y + h))
		};
		
		int indices[] = {
			0, 1, 2,
			2, 1, 3
		};
		
		mesh = new Mesh(vertices, indices, true);
	}
	
	public String getName() {
		return name;
	}
	
	public Vector2f getPosition() {
		return position;
	}
	
	public Vector2f getSize() {
		return size;
	}
	
	public Mesh getMesh() {
		return mesh;
	}
}
