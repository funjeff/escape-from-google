package gameObjects;

import engine.GameObject;
import engine.ObjectHandler;

public class Rat extends GameObject implements Scannable{

	int timer = 0;
	
	boolean moveLeft = true;
	public Rat () {
		
	}
	
	@Override
	public void frameEvent () {
		if (moveLeft) {
			timer = timer + 1;
			this.goX(this.getX() - 2);
		} else {
			timer = timer -1;
			this.goX(this.getX() + 2);
		}
		if (timer > 50) {
			moveLeft = false;
			this.getAnimationHandler().setFlipHorizontal(true);
		}
		if (timer < 0) {
			moveLeft = true;
			this.getAnimationHandler().setFlipHorizontal(false);
		}
		
	}
	
	@Override
	public void scanCompleteAction() {
		((Robot)ObjectHandler.getObjectsByName("Robot").get(0)).unlockMovement();
		
	}

}
