package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.StrokeLineCap;

/**
 * A command to draw a polyline
 *
 */
public class PolylineDrawingCommand extends ShapeDrawingCommand {
	private Polyline polyline;
	
	public PolylineDrawingCommand(Polyline polyline) {
		this.polyline = polyline;
	}
	
	/**
	 * Draw the polyline in the given GraphicsContext
	 * @param g the context to draw in
	 */
	private void drawPolyline(GraphicsContext g) {
		g.setLineCap(StrokeLineCap.ROUND);
		
		ArrayList<Point> points = this.polyline.getPoints();
		for (int i = 0; i < points.size() - 1; i++) {
			Point p1 = points.get(i);
			Point p2 = points.get(i + 1);
			
			g.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		}
		
		g.setLineCap(StrokeLineCap.SQUARE);
	}
	
	@Override
	protected void drawFilled(GraphicsContext g) {
		drawPolyline(g);
	}

	@Override
	protected void drawOutlined(GraphicsContext g) {
		drawPolyline(g);
	}

	@Override
	protected Shape getShape() {
		return this.polyline;
	}

}
