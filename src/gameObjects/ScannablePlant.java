package gameObjects;

import engine.GameCode;
import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;

public class ScannablePlant extends GameObject implements Scannable{


	
	public ScannablePlant () {
		this.setSprite(new Sprite ("resources/sprites/config/plant/J_Idle.txt"));
		this.useSpriteHitbox();
	}
	
	@Override
	public void frameEvent () {
		
		
	}
	
	@Override
	public void scanCompleteAction() {
		((Robot)ObjectHandler.getObjectsByName("Robot").get(0)).unlockPlant();
		
	}

}
