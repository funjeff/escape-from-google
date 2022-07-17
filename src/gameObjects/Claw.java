package gameObjects;

import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;
import engine.Vector2D;
import map.Room;

public class Claw extends GameObject {
	
	Sprite clawSprite = new Sprite ("resources/sprites/config/claw_sheet.txt");
	Sprite chainSprite = new Sprite ("resources/sprites/config/chain_sheet.txt");
	
	int clawFrame = 0;
	int chainFrame = 0;
	
	int mode = 0; //0 for stationary, 1 for down, 2 for grabbing, 3 for up/across/etc., 4 for release, 5 for end
	
	int time = 0;
	int modeTime = 0;
	
	Vector2D destination = new Vector2D (672, 128);
	Vector2D startPos = new Vector2D (0, 0);
	
	public Claw () {
		setHitboxAttributes (0, 0, 32, 32);
	}
	
	@Override
	public void onDeclare () {
		startPos = new Vector2D (getX (), getY ());
	}
	
	@Override
	public void frameEvent () {
		
		double robotOffsX = 2;
		double robotOffsY = 8;
		
		if (time / 4 % 2 == 0) {
			chainFrame = 0;
		} else {
			chainFrame = 1;
		}
		
		Robot robot = (Robot)ObjectHandler.getObjectsByName ("Robot").get (0);
		if (mode == 1) {
			setY (getY () + 2);
			if (getY () >= robot.getY () - 8) {
				if (isColliding ("Robot")) {
					mode = 2;
					modeTime = 0;
				} else {
					mode = 5;
				}
			}
		}
		
		if (mode == 2) {
			if (modeTime == 30) {
				clawFrame = 1;
				robot.clawMode ();
				robot.setX (getX () + robotOffsX);
				robot.setY (getY () + robotOffsY);
			}
			if (modeTime >= 60) {
				mode = 3;
				modeTime = 0;
			}
		}
		
		if (mode == 3) {
			Vector2D pos = new Vector2D (getX (), getY ());
			Vector2D offs = new Vector2D (pos, destination);
			offs.normalize ();
			offs.scale (2);
			setX (getX () + offs.x);
			setY (getY () + offs.y);
			robot.setX (getX () + robotOffsX);
			robot.setY (getY () + robotOffsY);
			double dist = Math.sqrt ((pos.x - destination.x) * (pos.x - destination.x) + (pos.y - destination.y) * (pos.y - destination.y));
			if (dist < 5) { //CLOSENESS CONSTANT FOR STOPPING
				mode = 4;
				modeTime = 0;
			}
		}
		
		if (mode == 4) {
			if (modeTime == 30) {
				clawFrame = 0;
				mode = 5;
				modeTime = 0;
				robot.unclawMode ();
			}
		}
		
		if (mode == 5) {
			if (modeTime >= 60) {
				mode = 0;
				modeTime = 0;
				setX (startPos.x);
				setY (startPos.y);
			}
		}
		
		time++;
		modeTime++;
		
	}
	
	public void setDestination (double destX, double destY) {
		destination = new Vector2D (destX, destY);
	}
	
	public void start () {
		mode = 1;
		time = 0;
		modeTime = 0;
	}
	
	@Override
	public void draw () {
		int drawX = (int)(getX () - Room.getViewX ());
		int drawY = (int)(getY () - Room.getViewY ());
		clawSprite.draw (drawX, drawY, clawFrame);
		for (int i = 1; i < 30; i++) {
			chainSprite.draw (drawX, drawY - i * 32, chainFrame);
		}
	}

}
