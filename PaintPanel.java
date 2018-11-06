package ca.utoronto.utm.paint;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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
	
	private boolean fill; // determines whether new shapes should be filled
	private float lineThickness; // determines the line thickness of new shapes
	private Color color;

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
		this.lineThickness = 5;
		
		this.model = model;
		this.model.addObserver(this);

		this.view = view;
	}

	public void repaint() {

		GraphicsContext g = this.canvas.getGraphicsContext2D();

		// Clear the canvas
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.strokeText("i=" + i, 50, 75);
		i = i + 1;

		// Draw Lines
		ArrayList<Point> points = this.model.getPoints();
		for (int i = 0; i < points.size() - 1; i++) {
			Point p1 = points.get(i);
			Point p2 = points.get(i + 1);
			
			g.setStroke(this.color);
			g.setLineWidth(p2.getLineThickness());
			
			g.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		}

		// Draw Circles
		ArrayList<Circle> circles = this.model.getCircles();
		for (Circle c : circles) {
			int x = c.getCentre().getX();
			int y = c.getCentre().getY();
			int radius = c.getRadius();

			g.setStroke(this.color);
			g.setLineWidth(c.getLineThickness());

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
			
			g.strokeLine(TopLeft.getX(), TopLeft.getY(),TopRight.getX(),TopRight.getY());
			g.strokeLine(TopLeft.getX(),TopLeft.getY(),BottomLeft.getX(),BottomLeft.getY());
			g.strokeLine(TopRight.getX(),TopRight.getY(),BottomRight.getX(),BottomRight.getY());
			g.strokeLine(BottomLeft.getX(), BottomLeft.getY(),BottomRight.getX(),BottomRight.getY());
			
			if (r.getFilled()) {
				//Fill Rectangle
			}
			else {
				//Stroke Rectangle
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
		this.mode = mode;
	}

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
			p.setLineThickness(this.lineThickness);
			this.model.addPoint(p);
			
		} else if (this.mode == "Circle") {

		}
		else if (this.mode == "Rectangle") {
			
		}
	}

	private void mouseClicked(MouseEvent e) {
		if (this.mode == "Squiggle") {

		} else if (this.mode == "Circle") {

		}
	}

	private void mousePressed(MouseEvent e) {
		if (this.mode == "Squiggle") {

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

		} else if (this.mode == "Circle") {
			if (this.circle != null) {
				// Problematic notion of radius and centre!!
				int radius = Math.abs((int) this.circle.getCentre().getX() - (int) e.getX());
				this.circle.setRadius(radius);
				this.circle.setFilled(this.fill);
				this.circle.setLineThickness(this.lineThickness);
				this.model.addCircle(this.circle);
				this.circle = null;
			}
		}
		else if (this.mode == "Rectangle") {
			Point bottomRight = new Point((int)e.getX(), (int)e.getY());
			this.rectangle.setBottomRight(bottomRight);
			this.rectangle.setFilled(this.fill);
			this.rectangle.setLineThickness(this.lineThickness);
			
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
