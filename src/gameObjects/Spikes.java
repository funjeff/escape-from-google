package gameObjects;

import engine.GameObject;
import engine.Sprite;

public class Spikes extends GameObject {
	
	public static Sprite spik = new Sprite ("resources/sprites/spikes.png");
	
	public Spikes () {
		setSprite (spik);
		setHitboxAttributes (0, 16, 32, 16);
	}

}
