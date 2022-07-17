package gameObjects;

import java.awt.Color;
import java.awt.Graphics;

import com.sun.glass.ui.Window;

import engine.GameObject;
import engine.RenderLoop;
import map.Room;

public class WaterDroplet extends GameObject {
	
	double xSpd;
	double vel = 1;
	double accel = 3;
	
	Color dropColor = new Color (0, 0, 255);
	
	public WaterDroplet () {
		xSpd = Math.random () * 8 - 4;
		vel = Math.random () * 6 + 1;
		dropColor = new Color (0, 0, (int)(Math.random () * 224) + 31);
		setHitboxAttributes (0, 0, 2, 3);
	}
	
	@Override
	public void frameEvent () {
		setX (getX () + xSpd);
		setY (getY () + vel);
		vel += accel;
		if (Room.isColliding (this)) {
			forget ();
		}
	}
	
	@Override
	public void draw () {
		Graphics g = RenderLoop.wind.getBufferGraphics ();
		g.setColor (dropColor);
		int drawX = (int)getX () - Room.getViewX ();
		int drawY = (int)getY () - Room.getViewY ();
		g.fillRect (drawX - 3, drawY, 2, 3);
	}

}
