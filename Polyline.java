package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.StrokeLineCap;

public class Polyline extends Shape {
	private ArrayList<Point> lstpoint = new ArrayList<Point>();
	
	public Polyline( ) {
	 
	}
	
	public void addPoint(Point p) {
		lstpoint.add(p);
	}
	
	public ArrayList<Point> getlist(){
		return lstpoint;
	}
		
	public Point get(int index) {
		return lstpoint.get(index);
	}
	
	public void set(int index, Point p) {
		lstpoint.set(index, p);
	}
	

	@Override
	public void execute(GraphicsContext g) {
		g.setStroke(this.getColor());
		g.setLineWidth(this.getLineThickness());
		g.setLineCap(StrokeLineCap.ROUND);
		
		for (int i = 0; i < lstpoint.size() - 1; i++) {
			Point p1 = lstpoint.get(i);
			Point p2 = lstpoint.get(i + 1);
			
			g.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		
		 		
	}
		g.setLineCap(StrokeLineCap.SQUARE);
	}
}
