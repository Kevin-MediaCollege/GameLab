package gamelab.city;

import static org.lwjgl.opengl.GL11.GL_LINE_LOOP;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glVertex3d;

import java.util.ArrayList;
import java.util.List;

import com.snakybo.sengine.components.MeshRenderer;
import com.snakybo.sengine.core.Component;
import com.snakybo.sengine.core.GameObject;
import com.snakybo.sengine.core.utils.Quaternion;
import com.snakybo.sengine.core.utils.Vector3f;
import com.snakybo.sengine.rendering.Material;
import com.snakybo.sengine.rendering.Mesh;
import com.snakybo.sengine.rendering.RenderingEngine;
import com.snakybo.sengine.rendering.Shader;
import com.snakybo.sengine.rendering.Texture;

/** @author Kevin Krol
 * @since May 13, 2014 */
public class City {
	private List<Building> buildings;
	
	private int size;
	
	private boolean isplayerCity;
	
	public City(boolean isPlayerCity) {
		buildings = new ArrayList<Building>();
		
		this.size = 0;
		this.isplayerCity = isPlayerCity;
		
		grow();
	}
	
	public void grow() {
		size++;
		
		buildings.add(new Building(this));
	}
	
	/*@Override
	protected void render(Shader shader, RenderingEngine renderingEngine) {
		glPushMatrix();
		glLoadIdentity();
		
		glBindTexture(GL_TEXTURE_2D, 0);
		glColor3f(0, 0, 0);
		
		glBegin(GL_LINE_LOOP);
		
		for(int i = 0; i <= 300; i++) {
			double angle = 2 * Math.PI * i / 300;
			
			double x = (15f * size) * Math.cos(angle);
			double z = (7.5f * size) * Math.sin(angle);
			
			glVertex3d(x, 0, z);
		}
		
		glEnd();
		
		glPopMatrix();
	}*/
}
