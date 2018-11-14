package ca.utoronto.utm.paint;

/**
 * An Concrete Command class for Color implementing Command
 *
 * @author Malaika Zaidi
 *
 */
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
