package gamelab.city;

import gamelab.TestGame;
import gamelab.utils.rendering.SpriteRenderer;
import gamelab.utils.tile.TileData;

import com.snakybo.sengine.core.GameObject;
import com.snakybo.sengine.core.utils.Quaternion;
import com.snakybo.sengine.core.utils.Vector3f;

/** @author Kevin Krol
 * @since May 16, 2014 */
public class Building {
	private City city;
	
	public Building(City city) {
		this.city = city;

		GameObject building = new GameObject();
		
		building.addComponent(new SpriteRenderer(TileData.Cities.spriteSheet, TileData.Cities.SMALL_HUT_1));
		
		building.getTransform().getLocalPosition().set(0, 0, 0);
		
		building.getTransform().setRotation(new Quaternion(new Vector3f(1, 0, 0), (float)Math.toRadians(270)));
		building.getTransform().rotate(new Vector3f(0, 0, 1), (float)Math.toRadians(180));
		
		building.getTransform().getLocalScale().set(37, 0, 53);
		
		TestGame.instance.addChild(building);
	}
}
