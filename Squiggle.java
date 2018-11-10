package ca.utoronto.utm.paint;

import java.util.ArrayList;

public class Squiggle extends Shape{
	private ArrayList<Point> points = new ArrayList<Point>();

	public Squiggle() {
		
	}
	
	
	public void addPoint(Point p) {
		points.add(p);
	}
	public Point getPoint(int index) {
		return points.get(index);
	}
	public ArrayList<Point> getlist(){
		return points;
	}
	
	public int size() {
		return points.size();
	}
	
	
	

}
