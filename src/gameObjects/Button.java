package gameObjects;

import engine.GameObject;

public class Button extends GameObject {
	Door d;
	
	public Button() {
		d = new Door ();
		d.declare(1000, 400);
	}
	
	public void pushButton() {
		d.open();
	}

}
