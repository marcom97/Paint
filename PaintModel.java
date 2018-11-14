package ca.utoronto.utm.paint;

import java.util.ArrayList;
import java.util.Observable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PaintModel extends Observable {

	private ArrayList<DrawingCommand> drawingCommands = new ArrayList<DrawingCommand>();
	
	private boolean fill = true; // determines whether new shapes should be filled


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
	/**
	 * Set whether new shapes should be filled
	 * @param fill should new shapes be filled
	 */
	public void setFill(boolean fill) {
		this.fill = fill;
	}
	
	public boolean getFill() {
		return this.fill;
	}

	
}
