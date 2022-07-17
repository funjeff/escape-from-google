package gameObjects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import engine.GameCode;
import engine.GameObject;
import engine.RenderLoop;
import engine.Vector2D;
import map.Room;

public class ScanReigon extends GameObject {

	Scannable scanObj;
	
	int centerX;
	int centerY;
	double radius;
	
	double theta = 0;
	
	LinkedList<Point> pathA;
	LinkedList<Point> pathB;
	LinkedList<Point> pathC;
	
	Vector2D[] pos = null;
	Vector2D[] vels = null;
	Point[][] paths = null;
	
	int time = 0;
	int selTime = 0;
	boolean isActive = false;
	boolean learned = false;
	
	String titleText;
	String[] descText;
	
	public ScanReigon (Scannable scanObj) {
		this.setRenderPriority(6001);
		this.scanObj = scanObj;
		pathA = new LinkedList ();
		pathB = new LinkedList ();
		pathC = new LinkedList ();
	}
	
	public void setCenterPt (Point centerPt) {
		centerX = centerPt.x;
		centerY = centerPt.y;
	}
	
	public void setRadius (double radius) {
		this.radius = radius;
	}
	
	public void setTitleText (String title) {
		this.titleText = title;
	}
	
	public void setDescText (String[] desc) {
		this.descText = desc;
	}
	
	@Override
	public void onDeclare () {
		centerX = (int)getX ();
		centerY = (int)getY ();
	}
	
	@Override
	public void pausedEvent () {
		if (GameCode.isScanMode ()) {
			int mouseX = getCursorX () + Room.getViewX ();
			int mouseY = getCursorY () + Room.getViewY ();
			double dist = (mouseX - centerX) * (mouseX - centerX) + (mouseY - centerY) * (mouseY - centerY);
			if (dist < radius * radius) {
				ArrayList<MouseEvent> events = getMouseEvents ();
				for (int i = 0; i < events.size (); i++) {
					MouseEvent curr = events.get (i);
					if (curr.getID () == MouseEvent.MOUSE_CLICKED && curr.getButton () == MouseEvent.BUTTON1) {
						learned = true;
						GameCode.getSoundPlayer().playSoundEffect(6F, "resources/scan.wav");
					}
				}
				isActive = true;
			} else {
				isActive = false;
				pos = null;
				pathA = new LinkedList ();
				pathB = new LinkedList ();
				pathC = new LinkedList ();
				time = 0;
			}
		} else {
			isActive = false;
			pos = null;
			pathA = new LinkedList ();
			pathB = new LinkedList ();
			pathC = new LinkedList ();
			time = 0;
		}
		GameObject centerObj = (GameObject) scanObj;
		
		this.setCenterPt(new Point((int) (centerObj.getX() + centerObj.hitbox().getWidth()/2) ,(int) (centerObj.getY() + centerObj.hitbox().getHeight()/2)));
		this.setX((int)(centerObj.getX() + centerObj.hitbox().getWidth()/2));
		this.setY((int)(centerObj.getY() + centerObj.hitbox().getHeight()/2));
		
	}
	
	@Override
	public void draw () {
		if (GameCode.isScanMode()) {
			double alpha = 1.0 - (double)selTime / 6;
			
			if (isActive) {
				if (time < 15) {
					//Calculate starting path
		
					if (pos == null) {
						pos = new Vector2D[] {
								new Vector2D (centerX + radius, centerY),
								new Vector2D (centerX + Math.cos (Math.PI * .667) * radius, centerY - Math.sin (Math.PI * .667) * radius),
								new Vector2D (centerX + Math.cos (Math.PI * 1.334) * radius, centerY - Math.sin (Math.PI * 1.334) * radius)
						};
						vels = new Vector2D[] {
								new Vector2D (0, 1),
								new Vector2D (0.8660254037845792, -0.49999999999975697),
								new Vector2D (-0.8660254037845792, -0.49999999999975697)
						};
						for (int i = 0; i < 3; i++) {
							vels[i].scale (.71);
						}
						paths = new Point[3][15];
						Vector2D centerVec = new Vector2D (centerX, centerY);
						for (int i = 0; i < 15; i++) {
							for (int j = 0; j < 3; j++) {
								Vector2D offs = new Vector2D (pos[j], centerVec);
								offs.normalize ();
								offs.scale (1.2 * i); //Gravity scale (1.2, 5)
								vels[j].add (offs);
								Vector2D accelVec = new Vector2D (vels[j]);
								accelVec.normalize ();
								accelVec.scale (5); //Accel scale
								vels[j].add (accelVec);
								paths[j][14 - i] = new Point ((int)pos[j].x, (int)pos[j].y);
								pos[j].add (vels[j]);
							}
						}
						theta = 0;
					}
					//Starting path
					int currSize = pathA.size ();
					pathA.addFirst (new Point (paths[0][currSize]));
					pathB.addFirst (new Point (paths[1][currSize]));
					pathC.addFirst (new Point (paths[2][currSize]));
				} else {
					//Add paths to circle
					pathA.addFirst (new Point ((int)(centerX + Math.cos (theta) * radius), (int)(centerY - Math.sin (theta) * radius)));
					pathB.addFirst (new Point ((int)(centerX + Math.cos (theta + Math.PI * .667) * radius), (int)(centerY - Math.sin (theta + Math.PI * .667) * radius)));
					pathC.addFirst (new Point ((int)(centerX + Math.cos (theta + Math.PI * 1.334) * radius), (int)(centerY - Math.sin (theta + Math.PI * 1.334) * radius)));
					if (pathA.size () > 15) {
						pathA.removeLast ();
						pathB.removeLast ();
						pathC.removeLast ();
					}
					theta += Math.PI / 45;
				}
				
				Graphics2D g = (Graphics2D)RenderLoop.wind.getBufferGraphics ();
				g.setColor (new Color (255, 0, 0, (int)(alpha * 255)));
				g.fillRect ((int)centerX, (int)centerY, 2, 2);
				Stroke s = new BasicStroke (5.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
				g.setStroke (s);
				
				//Convert to array
				Iterator<Point> iterA = pathA.iterator ();
				Iterator<Point> iterB = pathB.iterator ();
				Iterator<Point> iterC = pathC.iterator ();
				int[] ptsAX = new int[pathA.size ()];
				int[] ptsAY = new int[pathA.size ()];
				int[] ptsBX = new int[pathB.size ()];
				int[] ptsBY = new int[pathB.size ()];
				int[] ptsCX = new int[pathC.size ()];
				int[] ptsCY = new int[pathC.size ()];
				for (int i = 0; i < pathA.size (); i++) {
					Point currA = iterA.next ();
					Point currB = iterB.next ();
					Point currC = iterC.next ();
					ptsAX[i] = currA.x;
					ptsAY[i] = currA.y;
					ptsBX[i] = currB.x;
					ptsBY[i] = currB.y;
					ptsCX[i] = currC.x;
					ptsCY[i] = currC.y;
				}
				for (int i = 0; i < pathA.size () - 1; i++) {
					g.setColor (new Color ((int)(g.getColor ().getRed () - 12), 0, 0, g.getColor ().getAlpha ()));
					g.drawLine (ptsAX[i] - Room.getViewX (), ptsAY[i] - Room.getViewY (), ptsAX[i + 1] - Room.getViewX (), ptsAY[i + 1] - Room.getViewY ());
					g.drawLine (ptsBX[i] - Room.getViewX (), ptsBY[i] - Room.getViewY (), ptsBX[i + 1] - Room.getViewX (), ptsBY[i + 1] - Room.getViewY ());
					g.drawLine (ptsCX[i] - Room.getViewX (), ptsCY[i] - Room.getViewY (), ptsCX[i + 1] - Room.getViewX (), ptsCY[i + 1] - Room.getViewY ());
				}
		//		g.drawPolyline (ptsAX, ptsAY, pathA.size ());
		//		g.drawPolyline (ptsBX, ptsBY, pathB.size ());
		//		g.drawPolyline (ptsCX, ptsCY, pathC.size ());
				
				//Draw circle
				double circleRadius = radius < 40 ? radius - 12 : radius * 0.7;
				g.drawOval ((int)(centerX - circleRadius) - Room.getViewX (), (int)(centerY - circleRadius) - Room.getViewY (), (int)(circleRadius * 2), (int)(circleRadius * 2));
				
				if (time > 30) {
					Stroke s2 = new BasicStroke (2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
					g.setStroke (s2);
					if (!learned) {
						if (!(time >= 32 && time <= 34) && !(time >= 38 && time <= 40) && !(time >= 44 && time <= 48)) {
							g.drawRect ((int)getX () - (int)radius - 12 - Room.getViewX (), (int)getY () - (int)radius - 12 - Room.getViewY (), 240, (int)radius * 2 + 30);
						}
					} else {
						if (!(time >= 32 && time <= 34) && !(time >= 38 && time <= 40) && !(time >= 44 && time <= 48)) {
							int spd = 8;
							g.drawRect ((int)getX () - (int)radius - 12 - selTime * spd - Room.getViewX (), (int)getY () - (int)radius - 12 - selTime * spd - Room.getViewY (), (int)radius + 200 + selTime * spd * 2, (int)radius * 2 + 30 + selTime * spd * 2);
						}
					}
				}
				
				if (time > 60) {
					g.setColor(Color.WHITE);
					g.drawString (titleText, (int)(getX () + radius + 8) - Room.getViewX (), (int)(getY () - radius + 8) - Room.getViewY ());
				}
				if (time > 70) {
					String[] desc = descText;
					for (int i = 0; i < desc.length; i++) {
						if (time > 70 + i * 4) {
							g.setColor(Color.WHITE);
							g.drawString (desc[i], (int)(getX () + radius + 8) - Room.getViewX (), (int)(getY () - radius + 28 + i * 12) - Room.getViewY ());
						}
					}
				}
				
				time++;
				
				if (learned) {
					selTime++;
					if (selTime == 5) {
						GameCode.setScanMode(false);
						scanObj.scanCompleteAction ();
						selTime = 0;
						learned = false;
					}
				}
				
			}
		} else {
			isActive = false;
			pos = null;
			pathA = new LinkedList ();
			pathB = new LinkedList ();
			pathC = new LinkedList ();
			time = 0;
		}
	}
	
}