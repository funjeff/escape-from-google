package gameObjects;

import engine.GameObject;
import engine.ObjectHandler;

public class LadderGuy extends GameObject implements Scannable{

	int timer = 0;
	
	boolean moveLeft = true;
	public LadderGuy () {
		
	}
	
	@Override
	public void frameEvent () {
		if (moveLeft) {
			timer = timer + 1;
			this.goY(this.getY() - 2);
		} else {
			timer = timer -1;
			this.goY(this.getY() + 2);
		}
		if (timer > 50) {
			moveLeft = false;
		}
		if (timer < 0) {
			moveLeft = true;
		}
		
	}
	
	@Override
	public void scanCompleteAction() {
		((Robot)ObjectHandler.getObjectsByName("Robot").get(0)).unlockLadder();
		
	}

}
