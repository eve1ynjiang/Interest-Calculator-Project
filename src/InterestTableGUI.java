
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.*;
public class InterestTableGUI extends Application {
	protected TextField principal, rate;
	protected TextArea displayArea;
	
	int sceneWidth = 600, sceneHeight = 500;
	int verSpaceBetweenNodes = 10, horSpaceBetweenNodes = 10;
	int paneBorderTop = 20, paneBorderRight = 20;
	int paneBorderBottom = 20, paneBorderLeft = 20;
	
	@Override /* Method in Application class */
	public void start(Stage primaryStage) {
		//creating and setting up the flow pane
		FlowPane flow = new FlowPane();
		flow.setVgap(8);
		flow.setHgap(20);
		flow.setAlignment(Pos.CENTER);
		flow.setPadding(new Insets(paneBorderTop, paneBorderRight, 
				paneBorderBottom, paneBorderLeft));
		flow.setPrefWrapLength(400); 
		flow.setStyle("-fx-background-color: #FFB0C0;");
		
		/* Horizontal slider */
		Label sliderLabel = new Label("Number of Years: ");
		sliderLabel.setFont(Font.font("Courier New", FontWeight.BOLD, 15));
		sliderLabel.setTextFill(Color.WHITE);
		
		Slider slider = new Slider();
		slider.setMin(1);
		slider.setMax(25);
		slider.setMajorTickUnit(4);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		slider.setPrefWidth(320);
		flow.getChildren().addAll(sliderLabel, slider);
		
		//creating and setting up the grid pane
		GridPane pane = new GridPane();
		pane.setHgap(horSpaceBetweenNodes);
		pane.setVgap(verSpaceBetweenNodes);
		pane.setPadding(new Insets(paneBorderTop, paneBorderRight, 
				paneBorderBottom, paneBorderLeft));
		pane.setStyle("-fx-background-color: #FFB0C0;");
		
		Label principalLabel = new Label("Principal: ");
		principalLabel.setFont(Font.font("Courier New", FontWeight.BOLD, 15));
		principalLabel.setTextFill(Color.WHITE);
		principal = new TextField();
		principal.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
		principal.setStyle("-fx-text-fill: #CC8A98 ;");
		pane.add(principalLabel, 0, 0);
		pane.add(principal, 1, 0);
		
		Label rateLabel = new Label("Rate(Percentage): ");
		rateLabel.setFont(Font.font("Courier New", FontWeight.BOLD, 15));
		rateLabel.setTextFill(Color.WHITE);
		rate = new TextField();
		rate.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
		rate.setStyle("-fx-text-fill: #CC8A98 ;");
		pane.add(rateLabel, 0, 1);
		pane.add(rate, 1, 1);
		
		Button simple = new Button("Simple Interest");
		simple.setStyle("-fx-background-color: WHITE; -fx-text-fill: #CC8A98;");
		simple.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
		simple.setOnMouseEntered(e -> simple.setStyle("-fx-background-color: "
				+ "EFEFEF ; -fx-text-fill: #CC8A98 "));
        simple.setOnMouseExited(e -> simple.setStyle("-fx-background-color: "
        		+ "WHITE ; -fx-text-fill: #CC8A98 "));
		pane.add(simple, 10, 0);
		
    	Button compound = new Button("Compound Interest");
    	compound.setStyle("-fx-background-color: WHITE; -fx-text-fill: "
    			+ "#CC8A98;");
    	compound.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
    	compound.setOnMouseEntered(e -> compound.setStyle("-fx-background-color:"
    			+ " EFEFEF ; -fx-text-fill: #CC8A98 "));
        compound.setOnMouseExited(e -> compound.setStyle("-fx-background-color:"
        		+ "WHITE ; -fx-text-fill: #CC8A98 "));
    	pane.add(compound, 10, 1);
    	
    	Button both = new Button("Both Interests");
    	both.setStyle("-fx-background-color: WHITE ; -fx-text-fill: #CC8A98 ;");
    	both.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
    	both.setOnMouseEntered(e -> both.setStyle("-fx-background-color: "
    			+ "EFEFEF; -fx-text-fill: #CC8A98 "));
        both.setOnMouseExited(e -> both.setStyle("-fx-background-color: "
        		+ "WHITE ; -fx-text-fill: #CC8A98 "));
    	pane.add(both, 10, 2);
    	
    	/* Using Lambda Expression*/
    	simple.setOnAction(e -> {
			if (principal.getText().isEmpty() || rate.getText().isBlank()) {
				displayArea.setText("You must input values in the "
						+ "allotted areas!");
			} else {
				double principalValue = Double.parseDouble(principal.getText());
				double rateValue = Double.parseDouble(rate.getText());
				double years = slider.getValue();
				displayArea.setText(Interest.simpleInterestString(principalValue, 
						rateValue, years));
			}
		});
    	    	
    	/* Using anonymous inner class */
		compound.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (principal.getText().isEmpty() || rate.getText().isBlank()) {
					displayArea.setText("You must input values in the allotted "
							+ "areas!");
				} else {
					double principalValue = 
							Double.parseDouble(principal.getText());
					double rateValue = Double.parseDouble(rate.getText());
					double years = slider.getValue();
					displayArea.setText(Interest.compoundInterestString(
							principalValue, rateValue, years));
				}
			}
		});
    	
    	
    	/* Using non-anonymous inner class */
    	class ButtonHandler implements EventHandler<ActionEvent> {
    		@Override
    		public void handle(ActionEvent e) {
    			if (principal.getText().isEmpty() || rate.getText().isBlank()) {
    				displayArea.setText("You must input values in the allotted "
    						+ "areas!");
    			} else {
    				double principalValue = Double.parseDouble(principal.getText());
    				double rateValue = Double.parseDouble(rate.getText());
    				double years = slider.getValue();
    				displayArea.setText(Interest.bothInterestString(
    						principalValue, rateValue, years));
    			}
    		}
    	}
    	
    	both.setOnAction(new ButtonHandler());
    	
    	//creating and setting up the scroll pane
    	displayArea = new TextArea();
		displayArea.setPrefSize(sceneWidth, sceneHeight);
		displayArea.setEditable(false);
		displayArea.setWrapText(true);
		displayArea.setStyle("-fx-control-inner-background: #FFC7D2; "
				+ "-fx-text-fill: WHITE;");
		displayArea.setFont(Font.font("Courier New", FontWeight.BOLD, 14));

		// Adding scroll pane to text area 
		ScrollPane scrollPane = new ScrollPane(displayArea);
		
		
		BorderPane rootPane = new BorderPane();
		rootPane.setTop(pane);
		rootPane.setCenter(flow);
		rootPane.setBottom(scrollPane);
		
		// Display the stage
		Scene scene = new Scene(rootPane, sceneWidth, sceneHeight);
		primaryStage.setTitle("Interest Table Calculator");
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
