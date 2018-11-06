package ca.utoronto.utm.paint;

//A point shouldn't really be a shape but we'll have to do this until we create a Squiggle class for Bug 5.
public class Point extends Shape {
	int x, y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
