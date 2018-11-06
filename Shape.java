package ca.utoronto.utm.paint;


public abstract class Shape {
	private boolean filled;
	private float lineThickness;
	
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
