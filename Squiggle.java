package ca.utoronto.utm.paint;

public class Squiggle extends Shape{
	private Point start;
	private Point end;
	
	public Squiggle(Point start, Point end) {
		this.start = start;
		this.end = end;
	
	}
	public Point getStart() {
		return start;
	}

	public void setStart(Point start) {
		this.start = start;
	}
	
	public Point getEnd() {
		return end;
	}

	public void setEnd(Point end) {
		this.end = end;
	}
	

}
