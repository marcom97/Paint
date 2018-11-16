package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;

/**
 * The strategy for creating circles to draw
 *
 */
public class CircleManipulatorStrategy extends ShapeManipulatorStrategy {
	private Circle circle;

	@Override
	protected void mouseDragged(MouseEvent e) {
		Point p = new Point((int) e.getX(), (int) e.getY());
		int xDiff = this.circle.getCentre().getX() - p.getX();
		int yDiff = this.circle.getCentre().getY() - p.getY();
		int radius = (int)Math.hypot(xDiff, yDiff);
		this.circle.setRadius(radius);
		
		this.getPaintPanel().repaint();
	}

	@Override
	protected void mousePressed(MouseEvent e) {
		Point centre = new Point((int) e.getX(), (int) e.getY());
		this.circle = new Circle(centre, 0);
						
		setDefaultModifiers(this.circle);
		this.getModel().addCommand(new CircleDrawingCommand(this.circle));
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
		Point p = new Point((int) e.getX(), (int) e.getY());
		int xDiff = this.circle.getCentre().getX() - p.getX();
		int yDiff = this.circle.getCentre().getY() - p.getY();
		int radius = (int)Math.hypot(xDiff, yDiff);
		this.circle.setRadius(radius);

		this.getPaintPanel().repaint();
		this.circle = null;
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
