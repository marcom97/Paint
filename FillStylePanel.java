package ca.utoronto.utm.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class FillStylePanel extends GridPane implements EventHandler<ActionEvent> {
	private View view;
	
	public FillStylePanel(View view) {
		this.view = view;
		
		final ToggleGroup group = new ToggleGroup();
		RadioButton solidButton = new RadioButton("Solid");
		solidButton.setToggleGroup(group);
		solidButton.setSelected(true);
		solidButton.setOnAction(this);
		solidButton.setMinWidth(100);
		
		RadioButton outlineButton = new RadioButton("Outline");
		outlineButton.setToggleGroup(group);
		outlineButton.setOnAction(this);
		outlineButton.setMinWidth(100);
		
		this.add(solidButton, 0, 0);
		this.add(outlineButton, 0, 1);
	}

	@Override
	public void handle(ActionEvent event) {
		RadioButton button = (RadioButton) event.getSource();
		view.getPaintPanel().setFill(button.getText() == "Solid");
		System.out.println(button.getText());
	}
}
