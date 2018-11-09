package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;

public abstract class ShapeMode {
	private PaintPanel paintPanel;
	private PaintModel model;
	
	public final void handleMouseEvent(MouseEvent event) {
		if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			mouseDragged(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
			mousePressed(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_MOVED) {
			mouseMoved(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			mouseClicked(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
			mouseReleased(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			mouseEntered(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
			mouseExited(event);
		}
	}
	
	protected PaintPanel getPaintPanel() {
		return paintPanel;
	}
		
	public void setPaintPanel(PaintPanel paintPanel) {
		this.paintPanel = paintPanel;
	}
	
	protected PaintModel getModel() {
		return model;
	}
		
	public void setModel(PaintModel model) {
		this.model = model;
	}

	/**
	 * Set all default modifiers for a shape
	 * @param s shape to set modifiers for
	 */
	protected final void setDefaultModifiers(Shape s) {
		s.setFilled(this.paintPanel.getFill());
		s.setLineThickness(this.paintPanel.getLineThickness());
		s.setColor(this.paintPanel.getColor());
	}
	
	protected abstract void mouseDragged(MouseEvent e);
	protected abstract void mousePressed(MouseEvent e);
	protected abstract void mouseMoved(MouseEvent e);
	protected abstract void mouseClicked(MouseEvent e);
	protected abstract void mouseReleased(MouseEvent e);
	protected abstract void mouseEntered(MouseEvent e);
	protected abstract void mouseExited(MouseEvent e);
}