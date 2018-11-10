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

<<<<<<< HEAD

	private ShapeMode mode; // modifies how we interpret input

=======
	private String mode; // modifies how we interpret input (could be better?)
	private Circle circle; // the circle we are building
	private Rectangle rectangle;//the rectangle we can build
	private Polyline polyline;
>>>>>>> UserStory10
	
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
		
		ArrayList<Polyline> polylines = this.model.getPolylines();
		for (Polyline p: polylines) {
			g.setLineWidth(p.getLineThickness());
			g.setStroke(p.getColor());
			
			Point start = p.getstart();
			Point end = p.getend();
			g.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());

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

<<<<<<< HEAD
=======
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
			Point temp_circle = new Point((int) e.getX(), (int) e.getY());
			int xdiff = Math.abs((int) temp_circle.getX() - (int) this.circle.getCentre().getX());
			int ydiff = Math.abs((int)temp_circle.getY() - (int) this.circle.getCentre().getY());
			int thisradius = (int) Math.hypot(xdiff, ydiff);
			this.circle.setRadius(thisradius);
			this.circle.setFilled(this.fill);
			this.circle.setLineThickness(this.lineThickness);
			this.circle.setColor(this.color);
			this.model.addCircle(this.circle);

		}
		else if (this.mode == "Rectangle") {
			Point vertex = new Point((int) e.getX(), (int) e.getY());
			this.rectangle.setOppositeVertex(vertex);
			
			this.model.addRectangle(this.rectangle);
		}
		else if (this.mode == "Square") {
			Point vertex1 = new Point((int) e.getX(), (int) e.getY());
			this.rectangle.setOppositeVertex(this.getSquareVertex(vertex1));
			
			this.model.addRectangle(this.rectangle);
		}
		
		else if (this.mode == "Polyline") {
			Point endpoint = new Point((int)e.getX(), (int) e.getY());
			this.polyline.setend(endpoint);
			
			this.model.addPolyline(this.polyline);
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
		
		else if(this.mode == "Polyline") {
			Point start = new Point((int) e.getX(), (int)e.getY());
			Point end = new Point((int) e.getX(), (int)e.getY());
			this.polyline = new Polyline(start,end);
			
			setDefaultModifiers(this.polyline);
		}
	}

	private void mouseReleased(MouseEvent e) {
		if (this.mode == "Squiggle") {

		} else if (this.mode == "Circle") {
			if (this.circle != null) {
				// Problematic notion of radius and centre!!
				int xdiff = Math.abs((int) this.circle.getCentre().getX() - (int) e.getX());
				int ydiff = Math.abs((int) this.circle.getCentre().getY() - (int) e.getY());
				int radius = (int) Math.hypot(xdiff, ydiff);
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
		
		else if (this.mode == "Polyline") {
			Point end = new Point((int) e.getX(), (int)e.getY());
			this.polyline.setend(end);
			
			this.model.addPolyline(this.polyline);
			this.polyline = null;
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
>>>>>>> UserStory10
	}
}