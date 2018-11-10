package ca.utoronto.utm.paint;

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
}
