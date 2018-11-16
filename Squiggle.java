package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.StrokeLineCap;

/**
 * A class to represent a squiggle
 *
 */
public class Squiggle extends Shape{
	private ArrayList<Point> points = new ArrayList<Point>();

	/**
	 * Create a Squiggle with the given point as the first point
	 * @param p the first point
	 */
	public Squiggle(Point p) {
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
