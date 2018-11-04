package ca.utoronto.utm.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class FillStylePanel extends GridPane implements EventHandler<ActionEvent> {
	private View view;
	
	public FillStylePanel(View view) {
		this.view = view;
		
		Button solidButton = new Button("Solid");
		solidButton.setOnAction(this);
		solidButton.setMinWidth(100);
		
		Button outlineButton = new Button("Outline");
		outlineButton.setOnAction(this);
		outlineButton.setMinWidth(100);
		
		this.add(solidButton, 0, 0);
		this.add(outlineButton, 0, 1);
	}

	@Override
	public void handle(ActionEvent event) {
		Button button = (Button) event.getSource();
		view.getPaintPanel().setFill(button.getText() == "Solid");
		System.out.println(button.getText());
	}
}
