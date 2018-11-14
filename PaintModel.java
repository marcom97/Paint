package ca.utoronto.utm.paint;

import java.util.ArrayList;
import java.util.Observable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PaintModel extends Observable {

	private ArrayList<DrawingCommand> drawingCommands = new ArrayList<DrawingCommand>();
	
	private boolean fill = true; // determines whether new shapes should be filled
	private float lineThickness = 1; // determines the line thickness of new shapes
	private Color color = Color.BLACK; // determines if new shapes should be colored or not.

	public void addCommand(DrawingCommand command) {
		this.drawingCommands.add(command);
		this.setChanged();
		this.notifyObservers();
	}
	
	public void drawShapes(GraphicsContext g) {
		for (DrawingCommand command: this.drawingCommands) {
			command.execute(g);
		}
	}
	
}
