package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;

/**
 * A class to represent a Circle
 *
 */
public class Circle extends Shape {
	
	private Point centre;
	private int radius;

	public Circle(Point centre, int radius) {
		this.centre = centre;
		this.radius = radius;
	}

	public Point getCentre() {
		return centre;
	}

	public void setCentre(Point centre) {
		this.centre = centre;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
}
