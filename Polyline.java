package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;

public class Polyline extends Shape {
	private Point start;
	private Point end;
	
	public Polyline(Point start, Point end) {
		this.start = start;
		this.end = end;
	}
	
	public Point getstart() {
		return start;
	}
	
	public void setstart(Point start) {
		this.start = start;
	}
	
	public Point getend() {
		return end;
	}
	
	public void setend(Point end) {
		this.end = end;
	}

	@Override
	public void execute(GraphicsContext g) {
		g.setLineWidth(this.getLineThickness());
		g.setStroke(this.getColor());
		
		Point start = this.getstart();
		Point end = this.getend();
		g.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());		
	}
}
