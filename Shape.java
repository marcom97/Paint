package ca.utoronto.utm.paint;


/**
 * An abstract class used to represent a shape
 * @author Marco Matamoros
 *
 */
public abstract class Shape {
	private boolean filled;
	private float lineThickness;
	private boolean colored;

	/**
	 * Return whether this should be filled
	 * @return	should this be filled
	 */
	public boolean getFilled() {
		return this.filled;
	}
	
	public boolean getColored() {
		return this.colored;
	}
	
	
	/**
	 * Set whether this should be filled
	 * @param filled should this be filled
	 */
	public void setFilled(boolean filled) {
		this.filled = filled;
	}
	public void setColored(boolean colored) {
		this.colored = colored;
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
