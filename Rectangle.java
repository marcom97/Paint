package ca.utoronto.utm.paint;

public class Rectangle {
	
	private Point TopLeft;
	private Point BottomRight;
	
	public Rectangle() {
	}
	public void setTopLeft (Point topLeft) {
		this.TopLeft = topLeft;
	}
	
	public void setBottomRight(Point bottomRight) {
		this.BottomRight = bottomRight;
	}
	
	public Point getTopLeft() {
		return this.TopLeft;
	}
	
	public Point getBottomRight() {
		return this.BottomRight;
	}
	public Point getBottomLeft() {
		Point BottomLeft = new Point(TopLeft.getX(),BottomRight.getY());
		return BottomLeft;
	}

	
	public Point getTopRight() {
		Point TopRight = new Point(BottomRight.getX(),TopLeft.getY());
		return TopRight;
	}
	

}
