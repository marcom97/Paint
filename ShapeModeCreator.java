package ca.utoronto.utm.paint;

//TODO: Make a better implementation of the factory design pattern
public class ShapeModeCreator {
	private static ShapeModeCreator instance = null;
	private PaintPanel paintPanel;
	private PaintModel model;
	
	private ShapeModeCreator() {
		
	}
	
	public static synchronized ShapeModeCreator getInstance() {
		if (instance == null) {
			instance = new ShapeModeCreator();
		}
		return instance;
	}
	
	public ShapeMode createShapeMode(String mode) {
		ShapeMode shapeMode;
		
		switch (mode) {
			case "Rectangle":
				shapeMode = new RectangleMode();
				break;
			default:
				return null;
		}
		
		shapeMode.setPaintPanel(paintPanel);
		shapeMode.setModel(model);
		return shapeMode;
	}

	public void setPaintPanel(PaintPanel paintPanel) {
		this.paintPanel = paintPanel;
	}

	public void setModel(PaintModel model) {
		this.model = model;
	}
}
