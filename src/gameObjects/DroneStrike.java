package gameObjects;

import engine.GameObject;

public class DroneStrike extends GameObject {

	public DroneStrike() {
		this.setHitbox(0,0,16,16);
		this.adjustHitboxBorders();
	}
	
}
