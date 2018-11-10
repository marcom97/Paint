package ca.utoronto.utm.paint;

public class Rectangle extends Shape {
	
	private Point vertex;
	private Point oppositeVertex;
	private Point topLeft;
	private int width;
	private int height;
	
	public Rectangle(Point vertex, Point oppositeVertex) {
		this.vertex = vertex;
		this.oppositeVertex = oppositeVertex;
	}
	
		public Point getVertex() {
		return this.vertex;
	}
		
	public void setVertex(Point vertex) {
		this.vertex = vertex;
		vertexChanged();
	}

	public Point getOppositeVertex() {
		return this.oppositeVertex;
	}

	public void setOppositeVertex(Point oppositeVertex) {
		this.oppositeVertex = oppositeVertex;
		vertexChanged();
	}
	
	/**
	 * Recalculate the topLeft vertex as well as the width and height of this. 
	 */
	private void vertexChanged() {
		int x = Math.min(this.vertex.getX(), this.oppositeVertex.getX());
		int y = Math.min(this.vertex.getY(), this.oppositeVertex.getY());
		this.topLeft = new Point(x, y);
		
		this.width = Math.abs(this.oppositeVertex.getX() - this.vertex.getX());
		this.height = Math.abs(this.oppositeVertex.getY() - this.vertex.getY());
	}

	public Point getTopLeft() {
		return this.topLeft;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}
}
