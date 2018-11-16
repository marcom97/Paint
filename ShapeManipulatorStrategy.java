package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * The base class for all shape manipulator strategies
 * @author Marco Matamoros
 *
 */
public abstract class ShapeManipulatorStrategy {
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
		s.setFilled(this.model.getFill());
	}
	
	/**
	 * Called when a mouse drag event was detected
	 * @param e the event
	 */
	protected abstract void mouseDragged(MouseEvent e);
	/**
	 * Called when a mouse press event was detected
	 * @param e the event
	 */
	protected abstract void mousePressed(MouseEvent e);
	/**
	 * Called when a mouse move event was detected
	 * @param e the event
	 */
	protected abstract void mouseMoved(MouseEvent e);
	/**
	 * Called when a mouse click event was detected
	 * @param e the event
	 */
	protected abstract void mouseClicked(MouseEvent e);
	/**
	 * Called when a mouse release event was detected
	 * @param e the event
	 */
	protected abstract void mouseReleased(MouseEvent e);
	/**
	 * Called when a mouse enter event was detected
	 * @param e the event
	 */
	protected abstract void mouseEntered(MouseEvent e);
	/**
	 * Called when a mouse exit event was detected
	 * @param e the event
	 */
	protected abstract void mouseExited(MouseEvent e);
}