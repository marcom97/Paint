package ca.utoronto.utm.paint;

import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;




public class ColourSelectorPanel extends GridPane implements EventHandler<ActionEvent> {

	private View view; // So we can talk to our parent or other components of the view

	public ColourSelectorPanel(View view) {
		this.view = view;
		String[] buttonLabels = {"red", "blue", "yellow", "black" , "orange", "Green", "White", "Pink", "Purple", "aliceblue", "Aqua",
				"beige", "brown", "darkgray","crimson", "darkgreen","violet","chocolate"};
		int row = 0;
		for (String label : buttonLabels) {
			Button button = new Button(label);
			button.setMinSize(30, 1);
			this.add(button, row, 0);
			button.setTextFill(null);
			button.setStyle("-fx-background-color:" + label);
			row++;
			button.setOnAction(this);
		}
		
		
	}
	
	@Override
	public void handle(ActionEvent event) {
		String command = "Selects Color" + " " +((Button) event.getSource()).getText();
		Color color = Color.web(((Button) event.getSource()).getText());
		this.view.getPaintPanel().setColor(color);
		System.out.println(command);
	}
}

 
