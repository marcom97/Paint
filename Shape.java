package ca.utoronto.utm.paint;

import javafx.scene.paint.Color;

/**
 * An abstract class used to represent a shape
 * @author Marco Matamoros
 * @author Malaika Zaidi
 *
 */
public abstract class Shape {
	private boolean filled;
	private float lineThickness;
	private Color color;

	/**
	 * Return whether this should be filled
	 * @return	should this be filled
	 */
	public boolean getFilled() {
		return this.filled;
	}
	
	/**
	 * Return the color of new shape
	 * @return	what color should new shape be
	 */
	public Color getColor() {
		return this.color;
	}
	
	/**
	 * Set whether this should be filled
	 * @param filled should this be filled
	 */
	public void setFilled(boolean filled) {
		this.filled = filled;
	}
	
	/**
	 * sets color given by button pressed.
	 * @param color the color the shapes take on
	 */
	public void setColor(Color color) {
		this.color = color;
		System.out.println("Color changed");
	}
	
	/**
	 * Return the line thickness of this
	 * @return	line thickness of this
	 */
	public float getLineThickness() {
		return this.lineThickness;
	}
	
	/**
	 * Set the lineThickness of this
	 * @param thickness the line thickness to set
	 */
	public void setLineThickness(float thickness) {
		this.lineThickness = thickness;
	}
	
	
}
