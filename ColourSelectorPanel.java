package ca.utoronto.utm.paint;




import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
 
import javafx.scene.layout.FlowPane;
 
import javafx.scene.paint.Color;

public class ColourSelectorPanel extends FlowPane implements EventHandler<ActionEvent> {

	private View view; // So we can talk to our parent or other components of the view
	public ColourSelectorPanel(View view) {
		this.view = view;
		String[] buttonLabels = {"black", "darkgrey", "maroon", "olive" , "darkgreen", "Green", "darkblue", "darkmagenta", "darkolivegreen", "aliceblue", "Aqua",
				"beige", "brown", "lightgrey","white", "red","yellow","greenyellow", "lightblue", "blue", "pink", "gold", "violet", "orange", "darksalmon"
				,"teal", "steelblue","crimson"};
		 
	
		
		for (String label : buttonLabels) {
			Button button = new Button(label);
			String styles = "-fx-background-color:" + label +";"  + "-fx-border-color: black;" + "-fx-focus-color: dimgrey;";
			button.setStyle(styles);
			button.setOnAction(this);
			button.setTextFill(null);
			button.setPrefWidth(3);
			
			
		
			
			this.getChildren().add(button);
			this.setHgap(5);
			this.setVgap(6);
			this.setPadding(new Insets(10,10,10,10));
			
			
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

 
