package gameObjects;

import engine.GameObject;
import engine.Sprite;

public class Button2 extends GameObject {
	Door d;
	
	public Button2() {
	
		d = new Door ();
		d.declare(1880, 255);
		d.setSprite(new Sprite ("resources/sprites/config/bigDoor.txt"));
		d.setHitboxAttributes(0, 0,32, 128);
		
		this.getAnimationHandler().setFlipHorizontal(true);
		
		this.setSprite(new Sprite ("resources/sprites/button.png"));
		this.useSpriteHitbox();
	}
	
	public void pushButton() {
		d.open();
	}

	
}
