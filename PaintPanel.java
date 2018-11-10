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

	private int i = 0;
	private PaintModel model; // slight departure from MVC, because of the way painting works
	private View view; // So we can talk to our parent or other components of the view


	private ShapeMode mode; // modifies how we interpret input

	
	private boolean fill; // determines whether new shapes should be filled
	private float lineThickness; // determines the line thickness of new shapes
	private Color color; // determines if new shapes should be colored or not.

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
		
		this.fill = true;
		this.lineThickness = 1;
		this.color = Color.BLACK;

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
		g.strokeText("i=" + i, 50, 75);
		i = i + 1;
		
		
		// Draw Squiggles
		ArrayList<Squiggle> squiggles = this.model.getSquiggles();
		for (Squiggle s: squiggles) {
			for (int i=0; i < s.size()-1; i++) {
				Point p1 = s.getPoint(i);
				Point p2 = s.getPoint(i+1);
				g.setStroke(s.getColor());
				g.setLineWidth(s.getLineThickness());
				g.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
			}
				
		}
		

		// Draw Circles
		ArrayList<Circle> circles = this.model.getCircles();
		for (Circle c : circles) {
			int x = c.getCentre().getX();
			int y = c.getCentre().getY();
			int radius = c.getRadius();
		
			g.setLineWidth(c.getLineThickness());
			g.setStroke(c.getColor());
			g.setFill(c.getColor());

			if (c.getFilled()) {
				g.fillOval((x - (radius/2)),(y - (radius/2)), radius, radius);		
			}
			else {
				g.strokeOval((x - (radius/2)),(y - (radius/2)), radius, radius);
			}
		}
		
		//Draw Rectangles
		ArrayList<Rectangle> rectangles = this.model.getRectangles();
		for (Rectangle r: rectangles) {

			g.setLineWidth(r.getLineThickness());
			g.setFill(r.getColor());
			g.setStroke(r.getColor());


			Point topLeft = r.getTopLeft();
			if (r.getFilled()) {
				g.fillRect(topLeft.getX(), topLeft.getY(), r.getWidth(), r.getHeight());
			}
	
			else {
				g.strokeRect(topLeft.getX(), topLeft.getY(), r.getWidth(), r.getHeight());
			}
		}
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
	
	public Color getColor() {
		return this.color;
	}
	
	/**
	 * Set the color for each new shape
	 * @param color color of the new shapes
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	public boolean getFill() {
		return this.fill;
	}
	
	/**
	 * Set whether new shapes should be filled
	 * @param fill should new shapes be filled
	 */
	public void setFill(boolean fill) {
		this.fill = fill;
	}
	
	public float getLineThickness() {
		return this.lineThickness;
	}
	
	/**
	 * Set the line thickness for new shapes
	 * @param thickness line thickness for new shapes
	 */
	public void setLineThickness(float thickness) {
		this.lineThickness = thickness;
	}

	@Override
	public void handle(MouseEvent event) {
		this.mode.handleMouseEvent(event);

	}
}