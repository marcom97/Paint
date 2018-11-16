package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;

/**
 * The base class for all shape drawing commands
 * @author Marco Matamoros
 *
 */
public abstract class ShapeDrawingCommand implements DrawingCommand {	
	@Override
	public void execute(GraphicsContext g) {
		if (getShape().getFilled()) {
			this.drawFilled(g);
		}
		else {
			this.drawOutlined(g);
		}
	}
	/**
	 * Draw a shape that is filled using the supplied GraphicsContext
	 * @param g the context used to draw
	 */
	protected abstract void drawFilled(GraphicsContext g);
	/**
	 * Draw a shape that is outlined using the supplied GraphicsContext
	 * @param g the context used to draw
	 */
	protected abstract void drawOutlined(GraphicsContext g);
	/**
	 * Return the Shape object that will be drawn
	 * @return the shape to be drawn
	 */
	protected abstract Shape getShape();
}
