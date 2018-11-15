package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;

public class SquiggleManipulatorStrategy extends ShapeManipulatorStrategy {
	private Squiggle squiggle;

	@Override
	protected void mouseDragged(MouseEvent e) {
		Point p = new Point((int) e.getX(), (int) e.getY());
		this.squiggle.addPoint(p);
		
		this.getPaintPanel().repaint();
	}

	@Override
	protected void mousePressed(MouseEvent e) {
		Point p = new Point((int) e.getX(), (int) e.getY());
		this.squiggle = new Squiggle(p);
		setDefaultModifiers(this.squiggle);
		this.getModel().addCommand(this.squiggle);
	}

	@Override
	protected void mouseMoved(MouseEvent e) {
		

	}

	@Override
	protected void mouseClicked(MouseEvent e) {


	}

	@Override
	protected void mouseReleased(MouseEvent e) {
		Point p1 = new Point((int) e.getX(), (int)e.getY());
		this.squiggle.addPoint(p1);
		
		this.getPaintPanel().repaint();
		this.squiggle = null;
	}

	@Override
	protected void mouseEntered(MouseEvent e) {
	

	}

	@Override
	protected void mouseExited(MouseEvent e) {
		

	}

}
