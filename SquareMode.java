package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;

public class SquareMode extends ShapeMode {
	private Rectangle rectangle;
	
	/**
	 * Return the vertex obtained by adjusting the specified point so that 
	 * it creates a square with the current rectangle's first vertex.
	 * @param p the point to adjust
	 * @return the square's vertex obtained from p
	 */
	private Point getSquareVertex(Point p) {
		Point v = this.rectangle.getVertex();
		
		int xDiff = p.getX() - v.getX();
		int yDiff = p.getY() - v.getY();
		int width = Math.abs(xDiff);
		int height = Math.abs(yDiff);
		
		Point vertex = new Point(p.getX(), p.getY());
		
		if (height > width) {
			vertex.setX(v.getX() + (int)((double)height/width * xDiff));
		}
		else if (height < width) {
			vertex.setY(v.getY() + (int)((double)width/height * yDiff));
		}
		
		return vertex;
	}

	@Override
	protected void mouseDragged(MouseEvent e) {
		Point vertex1 = new Point((int) e.getX(), (int) e.getY());
		this.rectangle.setOppositeVertex(this.getSquareVertex(vertex1));
				
		this.getModel().addRectangle(this.rectangle);
	}

	@Override
	protected void mousePressed(MouseEvent e) {
		Point vertex = new Point((int) e.getX(), (int)e.getY());
		this.rectangle = new Rectangle(vertex, vertex);	
		
		setDefaultModifiers(this.rectangle);	
	}

	@Override
	protected void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void mouseReleased(MouseEvent e) {
		Point p = new Point((int)e.getX(), (int)e.getY());
		this.rectangle.setOppositeVertex(getSquareVertex(p));
		
		this.getModel().addRectangle(this.rectangle);
		this.rectangle = null;
	}

	@Override
	protected void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
