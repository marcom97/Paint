package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.StrokeLineCap;

public class Squiggle extends Shape{
	private ArrayList<Point> points = new ArrayList<Point>();

	public Squiggle(Point p) {
		points.add(p);
	}
	
	public void addPoint(Point p) {
		points.add(p);
	}

	public ArrayList<Point> getlist(){
		return points;
	}

	@Override
	public void execute(GraphicsContext g) {
		g.setStroke(this.getColor());
		g.setLineWidth(this.getLineThickness());
		g.setLineCap(StrokeLineCap.ROUND);

		for (int i=0; i < points.size()-1; i++) {
			Point p1 = points.get(i);
			Point p2 = points.get(i + 1);
			
			g.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		}
		
		g.setLineCap(StrokeLineCap.SQUARE);
	}
}
