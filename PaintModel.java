package ca.utoronto.utm.paint;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {

	private ArrayList<Point> points = new ArrayList<Point>();
	private ArrayList<Circle> circles = new ArrayList<Circle>();
	private ArrayList<Rectangle>rectangles = new ArrayList<Rectangle>();// Alana edit
<<<<<<< HEAD
	private ArrayList<Squiggle>squiggles = new ArrayList<Squiggle>();
=======
	private ArrayList<Polyline> polylines = new ArrayList<Polyline>();
	
>>>>>>> UserStory10

	public void addPoint(Point p) {
		this.points.add(p);
		this.setChanged();
		this.notifyObservers();
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void addCircle(Circle c) {
		this.circles.add(c);
		this.setChanged();
		this.notifyObservers();
	}

	public ArrayList<Circle> getCircles() {
		return circles;
	}
	
	
	
	public void addRectangle(Rectangle r) {
		this.rectangles.add(r);
		this.setChanged();
		this.notifyObservers();
	}
	
	public ArrayList<Rectangle> getRectangles(){
		return rectangles;
	}
	
<<<<<<< HEAD
	public void addSquiggle(Squiggle s) {
		this.squiggles.add(s);
		this.setChanged();
		this.notifyObservers();
	}

	public ArrayList<Squiggle> getSquiggles() {
		return squiggles;
	}
	
=======
	public void addPolyline(Polyline p) {
		this.polylines.add(p);
		this.setChanged();
		this.notifyObservers();
	}
	
	public ArrayList<Polyline> getPolylines(){
		return polylines;
	}
>>>>>>> UserStory10
}
