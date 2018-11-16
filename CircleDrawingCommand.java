package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;

/**
 * A command to draw a Circle
 * @author Marco Matamoros
 *
 */
public class CircleDrawingCommand extends ShapeDrawingCommand {
	private Circle circle;

	public CircleDrawingCommand(Circle c) {
		this.circle = c;
	}
	
	@Override
	protected void drawFilled(GraphicsContext g) {
		Point centre = circle.getCentre();
		int radius = circle.getRadius();
		int diameter = radius * 2;
		g.fillOval((centre.getX() - radius), (centre.getY() - radius), diameter, diameter);		
	}

	@Override
	protected void drawOutlined(GraphicsContext g) {
		Point centre = circle.getCentre();
		int radius = circle.getRadius();
		int diameter = radius * 2;
		g.strokeOval((centre.getX() - radius), (centre.getY() - radius), diameter, diameter);	
	}

	@Override
	protected Shape getShape() {
		return this.circle;
	}

}
