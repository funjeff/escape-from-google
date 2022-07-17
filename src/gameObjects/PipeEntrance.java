package gameObjects;

import engine.GameCode;
import engine.GameObject;
import map.Room;

public class PipeEntrance extends GameObject {
	
	int mode = 0; //0 is inactive, 1 is transport
	
	int time = 0;
	
	Robot r;
	
	public PipeEntrance () {
		setHitboxAttributes (15, 0, 2, 32);
	}
	
	@Override
	public void frameEvent () {
		
		if (mode == 0 && isColliding ("Robot")) {
			r = (Robot)getCollisionInfo ().getCollidingObjects ().get (0);
			if (r.crouching && r.learnedPipe) {
				GameCode.getSoundPlayer().playSoundEffect(6F,"resources/pipe.wav");
				r.clawMode ();
				r.setX (getX ());
				mode = 1;
			}
		} else if (mode == 1) {
			r.setY (r.getY () + 1);
			time++;
			if (time > 64) {
				Room.loadRoom ("resources/mapdata/final_level.tmj");
			}
		}
		
	}

}
