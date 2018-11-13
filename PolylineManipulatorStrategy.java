package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;

public class PolylineManipulatorStrategy extends ShapeManipulatorStrategy {
	private Polyline polyline;
	
	@Override
	protected void mouseDragged(MouseEvent e) {
		Point endpoint = new Point((int)e.getX(), (int) e.getY());
		this.polyline.setend(endpoint);
		
		this.getPaintPanel().repaint();
	}

	@Override
	protected void mousePressed(MouseEvent e) {
		Point start = new Point((int) e.getX(), (int)e.getY());
		Point end = new Point((int) e.getX(), (int)e.getY());
		this.polyline = new Polyline(start,end);
		
		setDefaultModifiers(this.polyline);
		this.getModel().addCommand(this.polyline);
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
		Point end = new Point((int) e.getX(), (int)e.getY());
		this.polyline.setend(end);
		
		this.getPaintPanel().repaint();
		this.polyline = null;
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
