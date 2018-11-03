package ca.utoronto.utm.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.awt.Color;
import java.awt.color.*;

public class ColourSelectorPanel extends GridPane implements EventHandler<ActionEvent> {

	private View view; // So we can talk to our parent or other components of the view

	public ColourSelectorPanel(View view) {
		this.view = view;
		
		String[] buttonLabels = { "Red", "Blue", "Green", "Yellow", "Orange" };
		
		int row = 0;
		for (String label : buttonLabels) {
			Button button = new Button(label);
			this.add(button, row, 0);
			button.setStyle("Color.BLUE");
			row ++;
			button.setOnAction(this);
		}
		
	}

	@Override
	public void handle(ActionEvent event) {
		String command = ((Button) event.getSource()).getText();
		this.view.getPaintPanel().setMode(command);
		System.out.println(command);
	}
}

 
