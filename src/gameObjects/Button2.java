package gameObjects;

import engine.GameObject;
import engine.Sprite;

public class Button2 extends GameObject {
	Door d;
	
	public Button2() {
	
		d = new Door ();
		d.declare(1880, 300);
		this.setSprite(new Sprite ("resources/sprites/button.png"));
		this.useSpriteHitbox();
	}
	
	public void pushButton() {
		d.open();
	}

	
}
