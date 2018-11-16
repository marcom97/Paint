package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;

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
	
	protected abstract void drawFilled(GraphicsContext g);
	protected abstract void drawOutlined(GraphicsContext g);
	protected abstract Shape getShape();
}
