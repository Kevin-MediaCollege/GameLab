package gamelab.utils.rendering;

import com.snakybo.sengine.core.Component;
import com.snakybo.sengine.rendering.Material;
import com.snakybo.sengine.rendering.RenderingEngine;
import com.snakybo.sengine.rendering.Shader;

/** @author Kevin Krol
 * @since May 14, 2014 */
public class SpriteSheetRenderer extends Component {
	private Material material;
	
	private SpriteSheet spriteSheet;
	private Sprite activeSprite;
	
	public SpriteSheetRenderer(SpriteSheet spriteSheet, String activeSpriteName) {
		this.spriteSheet = spriteSheet;
		
		material = new Material();
		material.addTexture("diffuse", spriteSheet.getTexture());
		
		activeSprite = spriteSheet.getSprite(activeSpriteName);
	}
	
	@Override
	protected void render(Shader shader, RenderingEngine renderingEngine) {
		shader.bind();
		shader.updateUniforms(getTransform(), material, renderingEngine);
		
		activeSprite.getMesh().draw();
	}
	
	public void setActiveSprite(String name) {
		this.activeSprite = spriteSheet.getSprite(name);
	}
}
