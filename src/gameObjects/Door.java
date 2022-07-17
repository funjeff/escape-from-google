package gameObjects;

import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;

public class Door extends GameObject {
	
	int curFrame = 0;
	
	public Door () {
	
	}
	
	public void open() {
		this.getAnimationHandler().setFrameTime(100);
	
	}
	
	@Override
	public void frameEvent () {
		
		if (this.getAnimationHandler().getFrame() == 9) {
			this.forget();
		}
	}
	
	
}
