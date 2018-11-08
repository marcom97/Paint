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
		g.setFill(Color.BLACK);

		// Clear the canvas
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.strokeText("i=" + i, 50, 75);
		i = i + 1;

		// Draw Lines
		ArrayList<Point> points = this.model.getPoints();
		for (int i = 0; i < points.size() - 1; i++) {
			Point p1 = points.get(i);
			Point p2 = points.get(i + 1);
			g.setStroke(p2.getColor());
			g.setLineWidth(p2.getLineThickness());

			g.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
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
	
	/**
	 * Set all default modifiers for a shape
	 * @param s shape to set modifiers for
	 */
	private void setDefaultModifiers(Shape s) {
		s.setFilled(this.fill);
		s.setLineThickness(this.lineThickness);
		s.setColor(this.color);
	}
	
	/**
	 * Return the vertex obtained by adjusting the specified point so that 
	 * it creates a square with the current rectangle's first vertex.
	 * @param p the point to adjust
	 * @return the square's vertex obtained from p
	 */
	private Point getSquareVertex(Point p) {
		Point v = this.rectangle.getVertex();
		
		int xDiff = p.getX() - v.getX();
		int yDiff = p.getY() - v.getY();
		int width = Math.abs(xDiff);
		int height = Math.abs(yDiff);
		
		Point vertex = new Point(p.getX(), p.getY());
		
		if (height > width) {
			vertex.setX(v.getX() + (int)((double)height/width * xDiff));
		}
		else if (height < width) {
			vertex.setY(v.getY() + (int)((double)width/height * yDiff));
		}
		
		return vertex;
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
			p.setColor(this.color);
			
			this.model.addPoint(p);
			
		} else if (this.mode == "Circle") {

		}
		else if (this.mode == "Rectangle") {
			Point vertex = new Point((int) e.getX(), (int) e.getY());
			this.rectangle.setOppositeVertex(vertex);
			
			this.model.addRectangle(this.rectangle);
		}
		else if (this.mode == "Square") {
			
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
							
			setDefaultModifiers(this.circle);
		}
		else if (this.mode == "Rectangle" || this.mode == "Square") {
			Point vertex = new Point((int) e.getX(), (int)e.getY());
			this.rectangle = new Rectangle(vertex, vertex);	
			
			setDefaultModifiers(this.rectangle);			
		}
	}

	private void mouseReleased(MouseEvent e) {
		if (this.mode == "Squiggle") {

		} else if (this.mode == "Circle") {
			if (this.circle != null) {
				// Problematic notion of radius and centre!!
				int radius = Math.abs((int) this.circle.getCentre().getX() - (int) e.getX());
				this.circle.setRadius(radius);

				this.model.addCircle(this.circle);
				this.circle = null;
			}
		}
		else if (this.mode == "Rectangle") {
			Point vertex = new Point((int)e.getX(), (int)e.getY());
			this.rectangle.setOppositeVertex(vertex);
			
			this.model.addRectangle(this.rectangle);
			this.rectangle = null;
		}
		else if (this.mode == "Square") {
			Point p = new Point((int)e.getX(), (int)e.getY());
			this.rectangle.setOppositeVertex(getSquareVertex(p));
			
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
