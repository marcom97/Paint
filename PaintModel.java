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
	
	public Color getColor() {
		return this.color;
	}
	
	/**
	 * Set the color for each new shape
	 * @param color color of the new shapes
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	public boolean getFill() {
		return this.fill;
	}
	
	/**
	 * Set whether new shapes should be filled
	 * @param fill should new shapes be filled
	 */
	public void setFill(boolean fill) {
		this.fill = fill;
	}
	
	public float getLineThickness() {
		return this.lineThickness;
	}
	
	/**
	 * Set the line thickness for new shapes
	 * @param thickness line thickness for new shapes
	 */
	public void setLineThickness(float thickness) {
		this.lineThickness = thickness;
	}
}
