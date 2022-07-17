package gameObjects;

import engine.GameObject;
import engine.Sprite;

public class HatRack extends GameObject {

	public static Sprite hatSprite = new Sprite ("resources/sprites/hat_stand.png");
	
	public HatRack () {
		setSprite (hatSprite);
		setHitboxAttributes (15, 6, 16, 16);
	}
	
}
