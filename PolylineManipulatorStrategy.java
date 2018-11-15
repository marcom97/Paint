package ca.utoronto.utm.paint;


import javafx.scene.input.MouseEvent;

public class PolylineManipulatorStrategy extends ShapeManipulatorStrategy {
	private Polyline polyline = new Polyline();
	
	
	@Override
	protected void mouseDragged(MouseEvent e) {
	}

	@Override
	protected void mousePressed(MouseEvent e) {
	}

	@Override
	protected void mouseMoved(MouseEvent e) {
	}

	@Override
	protected void mouseClicked(MouseEvent e) {
		Point point = new Point((int) e.getX(), (int) e.getY());
		this.polyline.addpoints(point);
		setDefaultModifiers(this.polyline);
		this.getModel().addCommand(this.polyline);
		this.getPaintPanel().repaint();
		
	}

	@Override
	protected void mouseReleased(MouseEvent e) {
		
	}

	@Override
	protected void mouseEntered(MouseEvent e) {
	}

	@Override
	protected void mouseExited(MouseEvent e) {


	}
	

}
