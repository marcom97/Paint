package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;

public class SquiggleMode extends ShapeMode {

	@Override
	protected void mouseDragged(MouseEvent e) {
		Point p = new Point((int) e.getX(), (int) e.getY());
		
		setDefaultModifiers(p);
		
		this.getModel().addPoint(p);
	}

	@Override
	protected void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

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
