package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;

public interface DrawingCommand {
	void execute(GraphicsContext g);
}
