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

	private String mode; // modifies how we interpret input (could be better?)
	private Circle circle; // the circle we are building
	private Rectangle rectangle;//the rectangle we can build
	private Squiggle squiggle; // squiggle we can build
	
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

		this.mode = "Circle"; // bad code here?

		this.fill = true;
		this.lineThickness = 1;

		this.model = model;
		
		this.model.addObserver(this);

		this.view = view;
	}

	public void repaint() {

		GraphicsContext g = this.canvas.getGraphicsContext2D();
		
		// Reset GraphicContext settings
		g.setLineWidth(1);
		g.setStroke(Color.BLACK);
		
		

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
			g.strokeOval((x - (radius/2)),(y - (radius/2)), radius, radius);
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
			Point TopLeft = r.getTopLeft();
			Point BottomRight = r.getBottomRight();
			Point BottomLeft = r.getBottomLeft();
			Point TopRight = r.getTopRight();
		
			g.setLineWidth(r.getLineThickness());
			g.setFill(r.getColor());
		
			
			Integer Rectangle_Width = Math.abs((TopRight.getX() - TopLeft.getX()));
			Integer Rectangle_Height = Math.abs((TopLeft.getY()- BottomLeft.getY()));
					

			if (r.getFilled()) {
				g.fillRect(TopLeft.getX(), TopLeft.getY(), Rectangle_Width, Rectangle_Height);
			}
	
			else {
				g.strokeRect(TopLeft.getX(), TopLeft.getY(), Rectangle_Width, Rectangle_Height);
				g.setStroke(r.getColor());
			}
		}
	}
			
	

	private Integer abs(int j) {
		// TODO Auto-generated method stub
		return null;
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
		this.mode = mode;
	}
	
	/**
	 * Set the color for each new shape
	 * @param color color of the new shapes
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	
	/**
	 * Set whether new shapes should be filled
	 * @param fill should new shapes be filled
	 */
	public void setFill(boolean fill) {
		this.fill = fill;
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

	private void mouseMoved(MouseEvent e) {
		if (this.mode == "Squiggle") {
			
	
		} else if (this.mode == "Circle") {

		}
	}

	private void mouseDragged(MouseEvent e) {
		if (this.mode == "Squiggle") {
			Point p = new Point((int) e.getX(), (int) e.getY());
			this.squiggle.setColor(this.color);
			this.squiggle.setLineThickness(this.lineThickness);
			this.squiggle.addPoint(p);
			this.model.addSquiggle(this.squiggle);
			
			
		}
			
		else if (this.mode == "Circle") {

		}
		else if (this.mode == "Rectangle") {
			Point Temporary_Rect = new Point((int) e.getX(), (int) e.getY());
			
			this.rectangle.setBottomRight(Temporary_Rect);
			this.rectangle.setFilled(this.fill);
			this.rectangle.setLineThickness(this.lineThickness);
			this.rectangle.setColor(this.color);
			this.model.addRectangle(this.rectangle);

		}
	}

	private void mouseClicked(MouseEvent e) {
		if (this.mode == "Squiggle") {
			
		} else if (this.mode == "Circle") {

		}
	}

	private void mousePressed(MouseEvent e) {
		if (this.mode == "Squiggle") {
			Point p1 = new Point((int) e.getX(), (int) e.getY());
			this.squiggle = new Squiggle();
			this.squiggle.addPoint(p1);
	
		} else if (this.mode == "Circle") {
			// Problematic notion of radius and centre!!
			Point centre = new Point((int) e.getX(), (int) e.getY());
			int radius = 0;
			this.circle = new Circle(centre, radius);
		}
		else if (this.mode == "Rectangle") {
			Point topLeft = new Point((int) e.getX(), (int)e.getY());
			this.rectangle = new Rectangle();
			this.rectangle.setTopLeft(topLeft);

		}
	}

	private void mouseReleased(MouseEvent e) {
		if (this.mode == "Squiggle") {
			Point p1 = new Point((int) e.getX(), (int)e.getY());
			this.squiggle.addPoint(p1);
			this.squiggle.setColor(this.color);
			this.squiggle.setLineThickness(this.lineThickness);
			this.model.addSquiggle(this.squiggle);
			this.squiggle = null;
			
			
		} else if (this.mode == "Circle") {
			if (this.circle != null) {
				// Problematic notion of radius and centre!!
				int radius = Math.abs((int) this.circle.getCentre().getX() - (int) e.getX());
				this.circle.setRadius(radius);
				this.circle.setFilled(this.fill);
				this.circle.setLineThickness(this.lineThickness);
				this.circle.setColor(this.color);
				this.model.addCircle(this.circle);
				this.circle = null;
			}
		}
		else if (this.mode == "Rectangle") {
			Point bottomRight = new Point((int)e.getX(), (int)e.getY());
			this.rectangle.setBottomRight(bottomRight);
			this.rectangle.setFilled(this.fill);
			this.rectangle.setLineThickness(this.lineThickness);

			this.rectangle.setColor(this.color);

			this.model.addRectangle(this.rectangle);
			this.rectangle = null;
		}
	}

	

	private void mouseEntered(MouseEvent e) {
		if (this.mode == "Squiggle") {
			
		} else if (this.mode == "Circle") {

		}
	}

	private void mouseExited(MouseEvent e) {
		if (this.mode == "Squiggle") {

		} else if (this.mode == "Circle") {

		}
	}
}
