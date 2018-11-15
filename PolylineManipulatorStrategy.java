package ca.utoronto.utm.paint;


import javafx.scene.input.MouseEvent;

public class PolylineManipulatorStrategy extends ShapeManipulatorStrategy {
	private Polyline polyline;
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
		if (this.polyline == null) {
			this.polyline = new Polyline();
			setDefaultModifiers(this.polyline);
			this.getModel().addCommand(this.polyline);
			

		}
		Point point = new Point((int) e.getX(), (int) e.getY());
		this.polyline.addPoint(point);
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
