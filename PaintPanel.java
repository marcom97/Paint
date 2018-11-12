package ca.utoronto.utm.paint;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

class PaintPanel extends StackPane implements Observer, EventHandler<MouseEvent> {

	private PaintModel model; // slight departure from MVC, because of the way painting works
	private View view; // So we can talk to our parent or other components of the view

	private ShapeMode mode; // modifies how we interpret input

	private Canvas canvas;

	public PaintPanel(PaintModel model, View view) {

		this.canvas = new Canvas(300, 300);
		this.getChildren().add(this.canvas);
		// The canvas is transparent, so the background color of the
		// containing pane serves as the background color of the canvas.
		this.setStyle("-fx-background-color: white");

		this.addEventHandler(MouseEvent.ANY, this);

		this.model = model;
		this.model.addObserver(this);
		
		ShapeModeCreator shapeModeCreator = ShapeModeCreator.getInstance();
		shapeModeCreator.setPaintPanel(this);
		shapeModeCreator.setModel(this.model);
		this.mode = shapeModeCreator.createShapeMode("Circle");

		this.view = view;
	}

	public void repaint() {

		GraphicsContext g = this.canvas.getGraphicsContext2D();
		
		// Reset GraphicContext settings
		g.setLineWidth(1);
		g.setStroke(Color.BLACK);
		g.setFill(Color.BLACK);

		// Clear the canvas
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		this.model.drawShapes(g);
	}
			
	@Override
	public void update(Observable o, Object arg) {
		// Not exactly how MVC works, but similar.
		this.repaint();
	}

	/**
	 * Controller aspect of this
	 */
	public void setMode(String mode) {
		this.mode = ShapeModeCreator.getInstance().createShapeMode(mode);
	}

	/**
	 * Set the color for each new shape
	 * @param color color of the new shapes
	 */
	public void setColor(Color color) {
		this.model.setColor(color);
	}

	/**
	 * Set whether new shapes should be filled
	 * @param fill should new shapes be filled
	 */
	public void setFill(boolean fill) {
		this.model.setFill(fill);
	}
	
	/**
	 * Set the line thickness for new shapes
	 * @param thickness line thickness for new shapes
	 */
	public void setLineThickness(float thickness) {
		this.model.setLineThickness(thickness);
	}
	
	@Override
	public void handle(MouseEvent event) {
		this.mode.handleMouseEvent(event);
	}
}