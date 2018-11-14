package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;

public class LineThicknessCommand implements DrawingCommand{
	
	private float linethickness;
	public LineThicknessCommand(float linethickness) {
		this.linethickness = linethickness;
	}
	@Override
	public void execute(GraphicsContext g) {
		 g.setLineWidth(linethickness);
		
	}

}
