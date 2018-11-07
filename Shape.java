package ca.utoronto.utm.paint;



public abstract class Shape {
	private boolean filled;
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
	
	
}
