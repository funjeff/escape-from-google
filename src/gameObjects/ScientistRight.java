package gameObjects;

import java.util.Iterator;

import engine.GameObject;
import engine.Sprite;
import map.Room;


public class ScientistRight extends GameObject{

	Light l;
	
	boolean walkingLeft = false;
	
	public ScientistRight () {
		this.setSprite(new Sprite ("resources/sprites/scientist.png"));
		this.useSpriteHitbox();
		l = new Light ();
	}
	
	@Override
	public void frameEvent () {
		if (walkingLeft) {
			if (!this.goX(this.getX() - 3)) {
				walkingLeft = false;
				this.getAnimationHandler().setFlipHorizontal(false);
			}
		} else {
			if (!this.goX(this.getX() + 3)) {
				walkingLeft = true;
				this.getAnimationHandler().setFlipHorizontal(true);
			}
		}
		l.setX(this.getX() - 75);
		l.setY(this.getY() + 20);
		l.frameEvent();
		l.draw();
	}
	
	
	
	private class Light extends GameObject {
		int height = 240;
		public Light () {
			this.adjustHitboxBorders();
			this.setSprite(new Sprite ("resources/sprites/Light.png"));
			this.enablePixelCollisions();
			this.getAnimationHandler().setFrameTime(250);
			this.getAnimationHandler().enableAlternate();
			this.setHitboxAttributes(0, 27, 208, height);
		}
		@Override
		public void frameEvent () {
				//makes the light level with the floor
				this.setHitboxAttributes(104, 27, 4, height);
				if (Room.isColliding(this)) {
					while (Room.isColliding(this) && height != 0) {
						height = height - 1;
						this.setHitboxAttributes(104, 27, 4, height);
					}
				} else {
					while (!Room.isColliding(this) && height != 240) {
						height = height + 1;
						this.setHitboxAttributes(104, 27, 4, height);
					}
					height = height -1;
				}
				height = height + 27;
				
				
				this.setHitboxAttributes(0, 16, 208, height);
				this.getAnimationHandler().setHeight(height);
				this.getAnimationHandler().scale(208, height);
				if (this.isColliding("Robot")) {
					this.getCollisionInfo().getCollidingObjects().get(0).setX(1950);
					this.getCollisionInfo().getCollidingObjects().get(0).setY(320);
				}
				if (this.isColliding("Plant")) {
					Iterator<GameObject> iter = this.getCollisionInfo().getCollidingObjects().iterator();
					while (iter.hasNext()) {
						GameObject working = iter.next();
						if (working.getClass().getSimpleName().equals("Plant")) {
							Plant workingPlant = (Plant) working;
							if (workingPlant.isExposed()) {
								workingPlant.makeBroken();	
							}
						}
					}
				}
		}
	}
}
