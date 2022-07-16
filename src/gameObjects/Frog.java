package gameObjects;

import engine.GameCode;
import engine.GameObject;
import engine.ObjectHandler;

public class Frog extends GameObject implements Scannable{

	int timer = 0;
	
	double vy;
	
	public Frog () {
		
	}
	
	@Override
	public void frameEvent () {
		if (vy == 0) {
			timer = timer + 1;
		}
		if (timer % 25 == 0) {
			vy = -20;
			timer = timer + 1;
		}
		
		boolean falling = false;
		
		if (vy > 0) {
			falling = true;
		}
		
		if (vy < 18) {
			vy = vy + 2;
		}
		
		
		if (!this.goY(this.getY() + vy)) {
			if (falling) {
				while (this.goY(this.getY() + 1));
			}
			vy = 0;
		}
		
		
	}
	
	@Override
	public void scanCompleteAction() {
		((Robot)ObjectHandler.getObjectsByName("Robot").get(0)).unlockJump();
		
	}

}
