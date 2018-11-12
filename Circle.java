package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;

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

	@Override
	public void execute(GraphicsContext g) {
		g.setLineWidth(this.getLineThickness());
		g.setStroke(this.getColor());
		g.setFill(this.getColor());
		
		int diameter = radius * 2;
		if (this.getFilled()) {
			g.fillOval((centre.getX() - radius), (centre.getY() - radius), diameter, diameter);		
		}
		else {
			g.strokeOval((centre.getX() - radius), (centre.getY() - radius), diameter, diameter);		
		}
	}
}
