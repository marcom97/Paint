package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;

/**
 * The strategy for creating rectangles to draw
 *
 */
public class RectangleManipulatorStrategy extends ShapeManipulatorStrategy {
	private Rectangle rectangle;

	@Override
	protected void mouseDragged(MouseEvent e) {
		Point vertex = new Point((int) e.getX(), (int) e.getY());
		this.rectangle.setOppositeVertex(vertex);
		
		this.getPaintPanel().repaint();
	}

	@Override
	protected void mousePressed(MouseEvent e) {
		Point vertex = new Point((int) e.getX(), (int)e.getY());
		this.rectangle = new Rectangle(vertex, vertex);	
		
		setDefaultModifiers(this.rectangle);
		this.getModel().addCommand(new RectangleDrawingCommand(this.rectangle));
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
		Point vertex = new Point((int)e.getX(), (int)e.getY());
		this.rectangle.setOppositeVertex(vertex);
		
		this.getPaintPanel().repaint();
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
