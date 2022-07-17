package gameObjects;

import engine.GameObject;
import engine.Sprite;

public class SmokePlume extends GameObject {
	
	public static Sprite smokeSprite = new Sprite ("resources/sprites/config/smoke_sheet.txt");
	
	int time = 0;
	
	public SmokePlume () {
		setSprite (smokeSprite);
		this.getAnimationHandler ().setAnimationFrame ((int)(Math.random () * 4));
	}
	
	@Override
	public void frameEvent () {
		this.setY (getY () - 2);
		if (time > 400) { //Particle death time
			forget ();
		}
		time++;
	}

}
