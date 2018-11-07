package ca.utoronto.utm.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ColourSelectorPanel extends GridPane implements EventHandler<ActionEvent> {

	private View view; // So we can talk to our parent or other components of the view
	public ColourSelectorPanel(View view) {
		this.view = view;
		String[] buttonLabels = {"black", "darkgrey", "maroon", "olive" , "darkgreen", "Green", "darkblue", "darkmagenta", "darkolivegreen", "aliceblue", "Aqua",
				"beige", "brown", "lightgrey","white", "red","yellow","greenyellow", "lightblue", "blue", "pink", "gold", "violet", "orange", "darksalmon"
				,"teal", "steelblue","crimson"};
		int row = 0;
		int row2 = 0;
		for (String label : buttonLabels) {
			Button button = new Button(label);
			button.setStyle("-fx-background-color:" + label);
			button.setOnAction(this);
			button.setTextFill(null);
			button.setPrefWidth(3);
			
			if (row <= 13) {
				this.add(button, row, 0);
				row++;
			}
			else {
				this.add(button, row2, 1);
				row2++;
			}
			
		}
		
		
		
	}
	
	@Override
	public void handle(ActionEvent event) {
		String[] colors = {"black", "darkgrey", "maroon", "olive" , "darkgreen", "Green", "darkblue", "darkmagenta", "darkolivegreen", "aliceblue", "Aqua",
				"beige", "brown", "lightgrey","white", "red","yellow","greenyellow", "lightblue", "blue", "pink", "gold", "violet", "orange", "darksalmon"
				,"teal", "steelblue","crimson"};
		String command = "Selects Color" + " " +((Button) event.getSource()).getText();
		for (String str: colors) {
			if (((Button) event.getSource()).getText() == str) {
				Color color = Color.web(((Button) event.getSource()).getText());
				this.view.getPaintPanel().setColor(color);
				this.view.getPaintPanel().setiscolor(true);
				
			}
		}
		
		System.out.println(command);
	}
}

 
