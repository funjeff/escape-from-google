package gameObjects;

import engine.GameObject;
import engine.Sprite;
import map.Room;

public class FlyPuppet extends GameObject {
	
	int timer = 0;
	boolean upOrDown;
	
	public FlyPuppet() {
		this.setSprite(new Sprite ("resources/sprites/config/jetpackRobot.txt"));
		this.getAnimationHandler().setFlipHorizontal(true);
	}
	
	@Override
	public void frameEvent () {
		this.setX(this.getX() -6);
		if (upOrDown) {
			this.setY(this.getY() + 6);
		} else {
			this.setY(this.getY() - 6);
		}
		timer = timer + 1;
		if (timer % 3 == 0) {
			upOrDown = !upOrDown;
		}
		if (timer == 100) {
			this.forget();
			Room.loadRoom("resources/mapdata/endCutsceen.tmj");
		}
	}

}
