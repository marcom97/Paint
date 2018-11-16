package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.StrokeLineCap;

/**
 * A class to represent a polyline
 *
 */
public class Polyline extends Shape {
	private ArrayList<Point> points = new ArrayList<Point>();
	
	/**
	 * Create a new Polyline with the given point as its first point
	 * @param p the first point
	 */
	public Polyline(Point p) {
		points.add(p);
	}
	
	/**
	 * Add a new point to this
	 * @param p the point to add
	 */
	public void addPoint(Point p) {
		points.add(p);
	}
	
	public ArrayList<Point> getPoints(){
		return points;
	}
	
}
