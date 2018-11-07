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
	private boolean iscolor; // determines if new shapes shouldbe colored or not.


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
		
		this.iscolor = false;

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

			g.strokeOval((x - (radius/2)),(y - (radius/2)), radius, radius);


			if (c.getFilled() && c.getColored()) {
				g.fillOval((x - (radius/2)),(y - (radius/2)), radius, radius);
				g.setFill(this.color);
				
			}
			else {
				g.strokeOval((x - (radius/2)),(y - (radius/2)), radius, radius);
				if (c.getColored()) {
					g.setStroke(this.color);
				}
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
			
			Integer Rectangle_Width = Math.abs((TopRight.getX() - TopLeft.getX()));
			Integer Rectangle_Height = Math.abs((TopLeft.getY()- BottomLeft.getY()));
						

			if (r.getFilled() && r.getColored()) {
				g.setFill(this.color);
				g.fillRect(TopLeft.getX(), TopLeft.getY(), Rectangle_Width, Rectangle_Height);
			}
			else if (r.getColored()) {
				g.setStroke(this.color);
				g.strokeRect(TopLeft.getX(), TopLeft.getY(), Rectangle_Width, Rectangle_Height);
			}
			else {
				g.strokeRect(TopLeft.getX(), TopLeft.getY(), Rectangle_Width, Rectangle_Height);
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
	 * sets color given by button pressed.
	 * @param color the color the shapes take on
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
	 * Sets whether the shapes should be colored
	 * @param iscolor tells us if shapes should be colored
	 */
	public void setiscolor(boolean iscolor) {
		this.iscolor = iscolor;

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
			Point Temporary_Rect = new Point((int) e.getX(), (int) e.getY());
			
			this.rectangle.setBottomRight(Temporary_Rect);
			this.rectangle.setFilled(this.fill);
			this.rectangle.setLineThickness(this.lineThickness);
			this.rectangle.setColored(this.iscolor);
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
				this.circle.setColored(this.iscolor);
				this.model.addCircle(this.circle);
				this.circle = null;
			}
		}
		else if (this.mode == "Rectangle") {
			Point bottomRight = new Point((int)e.getX(), (int)e.getY());
			this.rectangle.setBottomRight(bottomRight);
			this.rectangle.setFilled(this.fill);
			this.rectangle.setLineThickness(this.lineThickness);

			this.rectangle.setColored(this.iscolor);

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
