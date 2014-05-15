package gamelab.utils.renderer;

import com.snakybo.sengine.core.Component;
import com.snakybo.sengine.core.utils.Vector2f;
import com.snakybo.sengine.core.utils.Vector3f;
import com.snakybo.sengine.rendering.Material;
import com.snakybo.sengine.rendering.Mesh;
import com.snakybo.sengine.rendering.RenderingEngine;
import com.snakybo.sengine.rendering.Shader;
import com.snakybo.sengine.rendering.Vertex;

/** @author Kevin Krol
 * @since May 14, 2014 */
public class SpriteRenderer extends Component {
	private Mesh[] meshes;
	
	private Material material;
	
	private int activeSprite;
	
	public SpriteRenderer(SpriteSheet spriteSheet, int activeSprite) {
		final int spriteSheetSize = (int)(spriteSheet.getSize().getX() * spriteSheet.getSize().getY());
		
		meshes = new Mesh[spriteSheetSize];
		
		material = new Material();
		material.addTexture("diffuse", spriteSheet.getTexture());		
		
		final int indices[] = {
			0, 1, 2,
			2, 1, 3
		};
		
		for(int i = 0; i < spriteSheetSize; i++) {
			int x = i;
			int y = 0;
			
			while(x > spriteSheet.getSize().getX()) {
				x -= spriteSheet.getSize().getX();
				y++;
			}
			
			float posX = x * (1.0f / spriteSheet.getSize().getX());
			float posY = y * (1.0f / spriteSheet.getSize().getY());
			float width = 1.0f / spriteSheet.getSize().getX();
			float height = 1.0f / spriteSheet.getSize().getY();
			
			Vertex[] vertices = new Vertex[] {
				new Vertex(new Vector3f(-0.5f, 0, -0.5f), new Vector2f(posX,			posY)),
				new Vertex(new Vector3f(-0.5f, 0,  0.5f), new Vector2f(posX, 			posY + height)),
				new Vertex(new Vector3f( 0.5f, 0, -0.5f), new Vector2f(posX + width, 	posY)),
				new Vertex(new Vector3f( 0.5f, 0,  0.5f), new Vector2f(posX + width, 	posY + height))
			};
			
			meshes[i] = new Mesh(vertices, indices, true);
		}
		
		this.activeSprite = activeSprite;
	}
	
	@Override
	protected void render(Shader shader, RenderingEngine renderingEngine) {
		shader.bind();
		shader.updateUniforms(getTransform(), material, renderingEngine);
		
		meshes[activeSprite].draw();
	}
	
	public void setActiveSprite(int activeSprite) {
		this.activeSprite = activeSprite;
	}
}
