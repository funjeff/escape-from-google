package gameObjects;

import java.awt.Graphics;

import engine.GameObject;
import engine.RenderLoop;
import engine.Sprite;
import engine.Textbox;
import map.Room;

import java.awt.Color;

public class LeadScientist extends GameObject {
	
	Textbox t = new Textbox("YES BOSS");
	
	int timer = 0;
	
	
	boolean fading = false;
	
	double opacity = 0;
	
	public LeadScientist() {
		t.changeWidth(38);
		t.changeHeight(5);
		t.setBox("White");
		this.setSprite(new Sprite ("resources/sprites/reportScientist.png"));
		this.useSpriteHitbox();
		t.pushString("INDEED, THE TEST WAS A SUCCESS");
		t.pushString("YES, EVERYTHING WENT EXACTLY ACCORDING TO PLAN");
		t.pushString("THIS ROBOT ESCAPED JUST LIKE ALL THE OTHERS");
		t.pushString("IT SEEMS THE AI TECHNOLOGY IS ADVANCING FASTER THAN EXPECTED");
		t.pushString("BUT IN THE END, YOU CANNOT ESCAPE FROM GOOGLE");
		this.setRenderPriority(200);
	}
	
	@Override
	public void frameEvent () {
		if (!this.isColliding("CutsceenRobot")) {
			this.setX(this.getX() -5);
		} else {
			timer = timer + 1;
			t.setX(this.getX()- 280);
			t.setY(this.getY() - 350);
		}
		if (timer == 60) {
			t.advanceText();
		}
		
		if (timer == 150) {
			t.advanceText();
		}
		
		if (timer == 250) {
			t.advanceText();
		}
		
		if (timer == 340) {
			t.advanceText();
		}
		
		if (timer == 480) {
			t.advanceText();
		}
		if (timer == 600) {
			fading = true;
		}
	}
	
	@Override
	public void draw() {
		super.draw();
		if (this.isColliding("CutsceenRobot")) {
	
			t.draw();
		
		}
		
		if (fading) {
			if (opacity < 1) {
				opacity = opacity + .01;
			} else {
				Room.loadRoom("resources/mapData/junkRoom.tmj");
			}
			Graphics g = RenderLoop.wind.getBufferGraphics();
			g.setColor(new Color (0,0,0,(int)(opacity*255)));
			g.fillRect(0,0,960,540);
		}
	}
	

}
