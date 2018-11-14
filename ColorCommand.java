package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ColorCommand implements DrawingCommand{
	private Color color;
	
	public ColorCommand(Color color) {
		this.color = color;
	}
	@Override
	public void execute(GraphicsContext g) {
		g.setStroke(this.color);
		g.setFill(this.color);
	}

}
