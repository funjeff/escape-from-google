package gameObjects;

import engine.GameObject;
import engine.Sprite;

public class Button1 extends GameObject {
	Door d;
	
	public Button1() {
		d = new Door ();
		d.declare(800, 480);
		d.setSprite(new Sprite("resources/sprites/config/smallDoor.txt"));
		d.setHitboxAttributes(0, 0,32, 96);
		
		this.setSprite(new Sprite ("resources/sprites/button.png"));
		this.useSpriteHitbox();
	}
	
	public void pushButton() {
		d.open();
	}

}
