package gameObjects;

import engine.Sprite;

public class Bombmire extends NPC {

	public Bombmire () {
		super();
		
		text.changeText("GIGGDY");
		
		this.setSprite(new Sprite ("resources/sprites/bombmire.png"));
		this.setHitboxAttributes(10, 0, 21, 75);
		this.setRenderPriority(-1);	
		this.adjustHitboxBorders();
	}
}