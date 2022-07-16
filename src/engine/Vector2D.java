package engine;

import java.awt.Point;

public class Vector2D {
	
	public double x;
	public double y;
	
	public Vector2D (double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2D (Vector2D toCopy) {
		this.x = toCopy.x;
		this.y = toCopy.y;
	}
	
	public Vector2D (Vector2D from, Vector2D to) {
		this.x = to.x - from.x;
		this.y = to.y - from.y;
	}
	
	public void add (Vector2D toAdd) {
		this.x += toAdd.x;
		this.y += toAdd.y;
	}
	
	public void scale (double amt) {
		this.x *= amt;
		this.y *= amt;
	}
	
	public void normalize () {
		double len = Math.sqrt (x * x + y * y);
		this.x = this.x / len;
		this.y = this.y / len;
	}

}
