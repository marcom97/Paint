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

	private ShapeManipulatorStrategy strategy; // modifies how we interpret input

	private Canvas canvas;
	private boolean fill;


	public PaintPanel(PaintModel model, View view) {

		this.canvas = new Canvas(600, 600);
		
		
		this.getChildren().add(this.canvas);
		// The canvas is transparent, so the background color of the
		// containing pane serves as the background color of the canvas.
		this.setStyle("-fx-background-color: white");

		this.addEventHandler(MouseEvent.ANY, this);

		this.model = model;
		this.model.addObserver(this);
		this.fill = true;
		
		ShapeManipulatorFactory shapeManipulatorFactory = ShapeManipulatorFactory.getInstance();
		shapeManipulatorFactory.setPaintPanel(this);
		shapeManipulatorFactory.setModel(this.model);
		this.strategy = shapeManipulatorFactory.createShapeManipulator("Circle");
		
		

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
	public void setShapeStrategy(String strategy) {
		this.strategy = ShapeManipulatorFactory.getInstance().createShapeManipulator(strategy);
	}
	
	public void setColor(Color color) {
		this.model.addCommand(new ColorCommand(color));
		
	}
	public void setFill(boolean fill) {
		this.fill = fill;
	}
	
	public void setLineThickness(float linethickness) {
		this.model.addCommand(new LineThicknessCommand(linethickness));
		
	}
	@Override
	public void handle(MouseEvent event) {
		this.strategy.handleMouseEvent(event);
	}

	

	
}