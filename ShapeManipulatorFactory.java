package ca.utoronto.utm.paint;

public class ShapeManipulatorFactory {
	private static ShapeManipulatorFactory instance = null;
	private PaintPanel paintPanel;
	private PaintModel model;
	
	private ShapeManipulatorFactory() {
		
	}
	
	public static synchronized ShapeManipulatorFactory getInstance() {
		if (instance == null) {
			instance = new ShapeManipulatorFactory();
		}
		return instance;
	}
	
	public ShapeManipulatorStrategy createShapeManipulator(String strategy) {
		ShapeManipulatorStrategy shapeManipulatorStrategy;
		
		switch (strategy) {
		case "Squiggle":
			shapeManipulatorStrategy = new SquiggleManipulatorStrategy();
			break;
		case "Circle":
			shapeManipulatorStrategy = new CircleManipulatorStrategy();
			break;
		case "Rectangle":
			shapeManipulatorStrategy = new RectangleManipulatorStrategy();
			break;
		case "Square":
			shapeManipulatorStrategy = new SquareManipulatorStrategy();
			break;
		case "Polyline":
			shapeManipulatorStrategy = new PolylineManipulatorStrategy();
			break;
		default:
			return null;
		}
		
		shapeManipulatorStrategy.setPaintPanel(paintPanel);
		shapeManipulatorStrategy.setModel(model);
		return shapeManipulatorStrategy;
	}

	public void setPaintPanel(PaintPanel paintPanel) {
		this.paintPanel = paintPanel;
	}

	public void setModel(PaintModel model) {
		this.model = model;
	}
}
