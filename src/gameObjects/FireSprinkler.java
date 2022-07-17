package gameObjects;

import engine.GameObject;
import engine.Sprite;

public class FireSprinkler extends GameObject {
	
	Sprite sprinklerSprite = new Sprite ("resources/sprites/fire_sprinkler.png");
	
	boolean isActive = false;
	
	public FireSprinkler () {
		setSprite (sprinklerSprite);
	}
	
	public void activate () {
		isActive = true;
	}
	
	@Override
	public void frameEvent () {
		if (isActive) {
			for (int i = 0; i < 5; i++) {
				double offsX = Math.random () * 8 - 4;
				double offsY = Math.random () * 5;
				WaterDroplet d = new WaterDroplet ();
				d.declare (getX () + 17 + offsX, getY () + 16 + offsY);
			}
		}
	}

}
