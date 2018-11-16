package ca.utoronto.utm.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

	private View view; // So we can talk to our parent or other components of the view

	public ShapeChooserPanel(View view) {

		this.view = view;

		String[] buttonLabels = { "Circle", "Rectangle", "Square", "Squiggle", "Polyline" };
		this.setPadding(new Insets(5,5,5,5));
		this.setVgap(4);
		
		int row = 0;
		for (String label : buttonLabels) {
			Button button = new Button(label);
			button.setMinWidth(100);
			this.add(button, 0, row);
			row++;
			button.setOnAction(this);
			
			
			if (label == "Circle") {
				javafx.scene.shape.Circle circle = new javafx.scene.shape.Circle();
				circle.setRadius(10f);
				circle.setFill(Color.WHITE);
				circle.setStroke(Color.BLACK);
				button.setGraphic(circle);
				button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			}
			else if (label == "Rectangle"){
				javafx.scene.shape.Rectangle rectangle = new javafx.scene.shape.Rectangle();
				rectangle.setWidth(20);
				rectangle.setHeight(12);
				rectangle.setFill(Color.WHITE);
				rectangle.setStroke(Color.BLACK);
				button.setGraphic(rectangle);
				button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);


			}
			else if (label == "Square") {
				javafx.scene.shape.Rectangle square = new javafx.scene.shape.Rectangle();
				square.setWidth(15);
				square.setHeight(15);
				square.setFill(Color.WHITE);
				square.setStroke(Color.BLACK);
				button.setGraphic(square);
				button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

			}
			else if (label == "Squiggle") {
				javafx.scene.shape.Arc arc = new javafx.scene.shape.Arc();
				arc.setCenterX(5.0f);
				arc.setCenterY(0.0f);
				arc.setRadiusX(5.0f); //changed from 10
				arc.setRadiusY(5.0f);//changed from 10
				arc.setStartAngle(0.0f);
				arc.setLength(180.0f);
				arc.setType(ArcType.OPEN);
				arc.setStroke(Color.BLACK);
				arc.setFill(null);
				
				javafx.scene.shape.ArcTo ArcTo = new javafx.scene.shape.ArcTo();
				ArcTo.setRadiusX(5.0f);
				ArcTo.setRadiusY(5.0f);
				ArcTo.setAbsolute(false);
				ArcTo.setX(5.0f);
				ArcTo.setY(5.0f);
				
				button.setGraphic(arc);
				button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

				
			}
			else if (label == "Polyline") {
				javafx.scene.shape.Polyline poly = new javafx.scene.shape.Polyline();
				poly.getPoints().addAll(new Double[] {0.0,5.0,10.0,10.0,10.0,20.0,20.0,24.0});
				poly.setStroke(Color.BLACK);
				button.setGraphic(poly);
				button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

			}
		}
		
	}

	@Override
	public void handle(ActionEvent event) {
		String command = ((Button) event.getSource()).getText();
		ShapeManipulatorFactory factory = ShapeManipulatorFactory.getInstance();
		this.view.getPaintPanel().setShapeStrategy(factory.createShapeManipulator(command));
		System.out.println(command);
	}
}
