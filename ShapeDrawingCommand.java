package ca.utoronto.utm.paint;

public class ShapeDrawingCommand implements DrawingCommand {
	private Shape shape;
	
	public ShapeDrawingCommand(Shape shape) {
		this.shape = shape;
	}
	@Override
	public void execute() {
		
	}

}
