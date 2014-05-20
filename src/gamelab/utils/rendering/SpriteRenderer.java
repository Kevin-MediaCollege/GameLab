package gamelab.utils.rendering;

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
	private SpriteSheet spriteSheet;
	
	private Material material;
	private Mesh spriteMesh;
	
	private boolean enabled;
	
	public SpriteRenderer(SpriteSheet spriteSheet, int activeSprite) {
		this.spriteSheet = spriteSheet;
		
		material = new Material();
		material.addTexture("diffuse", spriteSheet.getTexture());
		
		setActiveSprite(activeSprite);
	}
	
	@Override
	protected void render(Shader shader, RenderingEngine renderingEngine) {
		if(enabled) {
			shader.bind();
			shader.updateUniforms(getTransform(), material, renderingEngine);
			
			spriteMesh.draw();
		}
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public void setActiveSprite(int activeSprite) {
		final Vector2f p = spriteSheet.spriteIdToTextureCoords(activeSprite);
		final Vector2f s = spriteSheet.getSpriteSize();
		
		final Vertex[] vertices = new Vertex[] {
			new Vertex(new Vector3f(-0.5f, 0, -0.5f), new Vector2f(p.getX(),			p.getY())),
			new Vertex(new Vector3f(-0.5f, 0,  0.5f), new Vector2f(p.getX(), 			p.getY() + s.getY())),
			new Vertex(new Vector3f( 0.5f, 0, -0.5f), new Vector2f(p.getX() + s.getX(),	p.getY())),
			new Vertex(new Vector3f( 0.5f, 0,  0.5f), new Vector2f(p.getX() + s.getX(), p.getY() + s.getY()))
		};
		
		final int indices[] = {
			0, 1, 2,
			2, 1, 3
		};
		
		spriteMesh = new Mesh(vertices, indices, true);
	}
	
	public SpriteSheet getSpriteSheet() {
		return spriteSheet;
	}
}
