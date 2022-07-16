package gameObjects;

import engine.GameObject;
import engine.Sprite;

public class grabableCeiling extends GameObject {

	public grabableCeiling () {
		this.setSprite(new Sprite ("resources/sprites/VineTemp.png"));
		this.setHitboxAttributes(32, 16);
	}
	
}
