package ca.utoronto.utm.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class FillStylePanel extends VBox implements EventHandler<ActionEvent> {
	private View view;
	
	public FillStylePanel(View view) {
		this.view = view;
		this.setAlignment(Pos.TOP_CENTER);
		
		final ToggleGroup group = new ToggleGroup();
		RadioButton solidButton = new RadioButton("Solid");
		solidButton.setToggleGroup(group);
		solidButton.setSelected(true);
		solidButton.setOnAction(this);
		solidButton.setMaxWidth(90);
		
		RadioButton outlineButton = new RadioButton("Outline");
		outlineButton.setToggleGroup(group);
		outlineButton.setOnAction(this);
		outlineButton.setMaxWidth(90);
		
		this.getChildren().add(solidButton);
		this.getChildren().add(outlineButton);
	}

	@Override
	public void handle(ActionEvent event) {
		RadioButton button = (RadioButton) event.getSource();
		view.getPaintPanel().setFill(button.getText() == "Solid");
		System.out.println(button.getText());
	}
}
