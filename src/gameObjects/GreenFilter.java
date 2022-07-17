package gameObjects;

import engine.GameObject;
import engine.Sprite;

public class GreenFilter extends GameObject {
	
	public GreenFilter () {
		this.setRenderPriority(6000);
		this.setSprite(new Sprite ("resources/sprites/green.png"));
	}

}
