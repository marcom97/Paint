package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * An abstract class used to represent a shape
 * @author Marco Matamoros
 *
 */
public abstract class Shape {
	private boolean filled;

	/**
	 * Return whether this should be filled
	 * @return	should this be filled
	 */
	public boolean getFilled() {
		return this.filled;
	}
	
	/**
	 * Set whether this should be filled
	 * @param filled should this be filled
	 */
	public void setFilled(boolean filled) {
		this.filled = filled;
	}
}
