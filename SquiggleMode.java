package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;

public class SquiggleMode extends ShapeMode {
	private Squiggle squiggle;

	@Override
	protected void mouseDragged(MouseEvent e) {
		Point p = new Point((int) e.getX(), (int) e.getY());
		this.squiggle.addPoint(p);
		this.getModel().addSquiggle(this.squiggle);
	}

	@Override
	protected void mousePressed(MouseEvent e) {

		Point p1 = new Point((int) e.getX(), (int) e.getY());
		this.squiggle = new Squiggle();
		this.squiggle.addPoint(p1);
		setDefaultModifiers(this.squiggle);
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
		this.getModel().addSquiggle(this.squiggle);
		this.squiggle = null;
	}

	@Override
	protected void mouseEntered(MouseEvent e) {
	

	}

	@Override
	protected void mouseExited(MouseEvent e) {
		

	}

}
