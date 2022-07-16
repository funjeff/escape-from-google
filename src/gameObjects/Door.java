package gameObjects;

import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;

public class Door extends GameObject {
	
	public Door () {
		this.setSprite(new Sprite ("resources/sprites/door.png"));
		this.setHitboxAttributes(0, 0,23, 53);
		this.adjustHitboxBorders();
	}
	
	public void open() {
		this.forget();
	
	}
	
	@Override
	public void frameEvent () {
		
	}
	
	
}
