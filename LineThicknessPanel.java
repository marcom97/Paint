package ca.utoronto.utm.paint;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LineThicknessPanel extends VBox {
	private View view;
	private static float defaultThickness = 1;
	
	public LineThicknessPanel(View view) {		
		this.view = view;
		this.setAlignment(Pos.TOP_CENTER);
		
		Label title = new Label("Line Thickness: " + (int)defaultThickness);
		title.setFont(Font.font("Arial", FontWeight.BOLD, 10));;
		title.setMaxWidth(90);
		
		Slider s = new Slider(1, 21, defaultThickness);
				
		s.setMaxWidth(95);
		
		s.setMajorTickUnit(10);
		s.setMinorTickCount(9);
		s.setShowTickMarks(true);
		s.setShowTickLabels(true);
		s.setSnapToTicks(true);
		
		s.valueProperty().addListener((property, oldValue, newValue) -> {
			this.view.getPaintPanel().setLineThickness(newValue.floatValue());
			title.setText("Line Thickness: " + newValue.intValue());
		});
		
		this.getChildren().add(title);
		this.getChildren().add(s);
	}	
}
