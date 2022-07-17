
	package gameObjects;

	import java.awt.Graphics;

import engine.GameCode;
import engine.GameObject;
import engine.ObjectHandler;
import engine.RenderLoop;
	import engine.Sprite;
	import engine.Textbox;
import map.Room;

import java.awt.Color;

	public class FinalScientist extends GameObject {
		
		Textbox t;
		
		static int testNum = 891;
		
		int timer = 0;
		
		boolean fading = true;
		
		double opacity = 1;
		
		boolean throwing = false;
		
		public PropRobot prop = new PropRobot ();
		
		boolean inzialized = false;
		
		boolean walkAway = false;
		
		boolean scroll = false;
		
		boolean showText = false;
		
		boolean endFade = false;
		
		int finalTimer = 0;
		
		public FinalScientist() {
			
			testNum = testNum + 1;
			t =  new Textbox("STARTING TEST " + testNum);
			
			t.changeWidth(38);
			t.changeHeight(5);
			t.setBox("White");
			this.setSprite(new Sprite ("resources/sprites/reportScientist.png"));
			this.useSpriteHitbox();
			this.setRenderPriority(200);
			
		}
		
		@Override
		public void frameEvent () {
			if (!inzialized) {
				Room.setView((int)this.getX()-500, (int)this.getY()-100);
				prop.setX(this.getX() -10);
				prop.setY(this.getY() + 10);
				inzialized = true;
			}
			if (throwing) {
				prop.frameEvent();
				if (prop.isDone) {
					throwing = false;
					scroll = true;
				}
			}
			
			if (scroll) {
				Room.setView(Room.getViewX() - 2, Room.getViewY());
				t.setX(Room.getViewX() + 100);
				t.setY(Room.getViewY() + 50);
				
			}
			
			if (Room.getViewX() < 350 && Room.getViewX() != 0) {
				
				endFade = true;
				
			}
			if (showText) {
				finalTimer = finalTimer + 1;
			}
			
			if (finalTimer == 100) {
				try {
				GameCode.getSoundPlayer().stop();
				} catch (NullPointerException e) {
				}
				Room.loadRoom("resources/mapData/lab.tmj");
				((FullCrate)ObjectHandler.getObjectsByName("FullCrate").get(0)).empty();
				GameCode.openCrate();
			}
			
		}
		
		@Override
		public void draw() {
			super.draw();
			
			prop.draw();
			
			
			
			if (fading) {
				if (opacity > 0) {
					opacity = opacity - .01;
				} else {
					fading = false;
					throwing = true;
				}
				Graphics g = RenderLoop.wind.getBufferGraphics();
				g.setColor(new Color (0,0,0,(int)(opacity*255)));
				g.fillRect(0,0,960,540);
			}
			
			if (endFade) {
				if (opacity < 1) {
					opacity = opacity + .005;
				} else {
					showText = true;
				}
				Graphics g = RenderLoop.wind.getBufferGraphics();
				g.setColor(new Color (0,0,0,(int)(opacity*255)));
				g.fillRect(0,0,960,540);
			}
			
			if (showText) {
				t.draw();
			}
			
		}
		
		
		

		private class PropRobot extends GameObject {
			
			boolean isDone = false;
			int vx = 30;
			int vy = -20;
			
			
			public PropRobot () {
				this.setSprite(new Sprite ("resources/sprites/prop.png"));
				this.useSpriteHitbox();
			}
			
			@Override
			public void frameEvent () {
					
					vy = vy + 2;
					if (!this.goY(this.getY() + vy)) {		
						while (this.goY(this.getY() + 1));
						isDone = true;
					}
						
					if (vx > 0) {
						vx = vx - 1;
					}
					this.goX(this.getX() - vx);
			}
			
			public boolean isDone () {
				return isDone;
			}
		}
		
	}
