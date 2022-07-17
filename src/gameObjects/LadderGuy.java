package gameObjects;

import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;

public class LadderGuy extends GameObject implements Scannable{

	int timer = 0;
	
	boolean moveLeft = true;
	
	ScanReigon r = new ScanReigon (this);
	
	public LadderGuy () {
		this.setSprite(new Sprite ("resources/sprites/scientistClimb.png"));
		this.useSpriteHitbox();
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
	public void onDeclare () {
		
		r.setRadius (40);
		r.setTitleText ("Ladder Guy");
		r.setDescText (new String[] {"climbs ladders", "with the w key"});
		r.declare (this.getX(), this.getY());
	}
	
	@Override
	public void scanCompleteAction() {
		((Robot)ObjectHandler.getObjectsByName("Robot").get(0)).unlockLadder();
		
	}

}
