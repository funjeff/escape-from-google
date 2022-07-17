package gameObjects;

import engine.GameObject;
import engine.Sprite;

public class FullCrate extends GameObject {

	int openTimer = 0;
	int ogX;
	int ogY;
	
	public FullCrate () {
		this.setSprite(new Sprite ("resources/sprites/full crate.png"));
		this.useSpriteHitbox();
	}
	
	public void open () {
		openTimer = 1;
		
	}
	
	@Override
	public void frameEvent () {
		if (openTimer > 0) {
			openTimer = openTimer + 1;
			
			if (openTimer == 50) {
				Robot r = new Robot ();
				r.setRenderPriority(21);
				r.declare(3000, 280);

				this.empty();
			}
			
			if (openTimer % 4 == 1) {
				this.setX(ogX);
				this.setY(ogY);
			} else {
				if (openTimer % 4 == 0) {
					this.goXandY(this.getX() + ((Math.random()*2 * openTimer) - openTimer),this.getY() + ((Math.random()*2 * openTimer) - openTimer) );	
				}
			}
			
			
		}
	}
	
	@Override
	public void onDeclare() {
		ogX = (int) this.getX();
		ogY = (int) this.getY();
	}
	
	public void empty () {
		this.forget();
		EmptyCrate e = new EmptyCrate();
		e.setRenderPriority(20);
		e.declare(this.getX(),this.getY());
	}
	
}