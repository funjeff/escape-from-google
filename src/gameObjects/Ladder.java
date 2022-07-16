package gameObjects;

import engine.GameObject;
import engine.Sprite;

public class Ladder extends GameObject {
	public Ladder () {
		this.setSprite(new Sprite ("resources/sprites/Ladder.png"));
		this.setHitbox(0,0, 32,32);	
	}

}
