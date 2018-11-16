package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;

/**
 * A command to draw a Rectangle
 *
 */
public class RectangleDrawingCommand extends ShapeDrawingCommand {
	private Rectangle rectangle;
	
	public RectangleDrawingCommand(Rectangle r) {
		this.rectangle = r;
	}
	
	@Override
	protected void drawFilled(GraphicsContext g) {
		Point topLeft = rectangle.getTopLeft();
		g.fillRect(topLeft.getX(), topLeft.getY(), rectangle.getWidth(), rectangle.getHeight());
	}

	@Override
	protected void drawOutlined(GraphicsContext g) {
		Point topLeft = rectangle.getTopLeft();
		g.strokeRect(topLeft.getX(), topLeft.getY(), rectangle.getWidth(), rectangle.getHeight());
	}

	@Override
	protected Shape getShape() {
		return rectangle;
	}
}
