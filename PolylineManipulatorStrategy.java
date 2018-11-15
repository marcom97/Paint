package ca.utoronto.utm.paint;


import javafx.scene.input.MouseEvent;

public class PolylineManipulatorStrategy extends ShapeManipulatorStrategy {
	private Polyline polyline;
	
	@Override
	protected void mouseDragged(MouseEvent e) {
	}

	@Override
	protected void mousePressed(MouseEvent e) {
		Point start = new Point((int) e.getX(), (int) e.getY());
		this.polyline = new Polyline(start);
		setDefaultModifiers(this.polyline);
		
		this.getModel().addCommand(this.polyline);
	}

	@Override
	protected void mouseMoved(MouseEvent e) {
	}

	@Override
	protected void mouseClicked(MouseEvent e) {
		Point mid = new Point((int) e.getX(), (int) e.getY());
		this.polyline.addpointps(mid);
		
	}

	@Override
	protected void mouseReleased(MouseEvent e) {
		Point end = new Point((int) e.getX(), (int) e.getY());
		this.polyline.addpointps(end);
		
		this.getModel().addCommand(this.polyline);
	}

	@Override
	protected void mouseEntered(MouseEvent e) {
	}

	@Override
	protected void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	

}
