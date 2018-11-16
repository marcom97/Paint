package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.StrokeLineCap;

public class SquiggleDrawingCommand extends ShapeDrawingCommand {
	private Squiggle squiggle;
	
	public SquiggleDrawingCommand(Squiggle squiggle) {
		this.squiggle = squiggle;
	}
	
	/**
	 * Draw a squiggle in the given GraphicsContext
	 * @param g the context to draw in
	 */
	private void drawSquiggle(GraphicsContext g) {
		g.setLineCap(StrokeLineCap.ROUND);

		ArrayList<Point> points = squiggle.getPoints();
		for (int i=0; i < points.size()-1; i++) {
			Point p1 = points.get(i);
			Point p2 = points.get(i + 1);
			
			g.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		}
		
		g.setLineCap(StrokeLineCap.SQUARE);
	}
	@Override
	protected void drawFilled(GraphicsContext g) {
		drawSquiggle(g);
	}

	@Override
	protected void drawOutlined(GraphicsContext g) {
		drawSquiggle(g);
	}

	@Override
	protected Shape getShape() {
		return squiggle;
	}

}
