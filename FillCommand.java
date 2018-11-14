package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;

public class FillCommand implements DrawingCommand{
	private boolean fill;
	public FillCommand(boolean fill) {
		this.fill = true;
	}
	@Override
	public void execute(GraphicsContext g) {
		
	}

}
