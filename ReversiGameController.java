package myapp;




import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ReversiGameController implements Initializable {
	//private int counter = 1;
	@FXML
	private VBox root;
	@FXML
	private HBox son;
	@FXML
	private Label currentPlayer = new Label();
	@FXML
	private Label xPoints = new Label("2");

	@FXML
	private Label oPoints = new Label("2");
	@FXML
	private Button btn = new Button();
	@FXML
	private ReversiBoard reversiBoard;
@FXML
	public Button getBtn() {
		return btn;
	}
@FXML
	private MenuBar bar = new MenuBar();
@FXML
private Menu reversi = new Menu("Reversi");
@FXML
private MenuItem exit = new MenuItem("Exit");
@FXML
private MenuItem settings = new MenuItem("Settings");

private SettingBox set = new SettingBox();



	//private ReversiBoard reversiBoard;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		reversiBoard = new ReversiBoard(this);
		
		reversiBoard.setPrefWidth(400);
		reversiBoard.setPrefHeight(400);
		son.getChildren().add(0,reversiBoard);
		//root.getChildren().add(son);
		reversiBoard.draw();
		
		root.widthProperty().addListener((observable, oldValue, newValue) -> {
			double boardNewWidth = newValue.doubleValue() - 200;
			reversiBoard.setPrefWidth(boardNewWidth);
			reversiBoard.draw();
		});

		root.heightProperty().addListener((observable, oldValue, newValue) -> {
			reversiBoard.setPrefHeight(newValue.doubleValue());
			reversiBoard.draw();
		});
		
		
		exit.setOnAction(event -> {
			String ans = ConfirmBox.Display("Exit", "Are you sure?");
			if(ans.equals("yes")) {
				Stage window =(Stage) root.getScene().getWindow();
				window.close();
			}
		});
		settings.setOnAction(event ->{
			try {
				set.Display("Settings");
			} catch (Exception e) {
				
			}
		});

	}
	
	public Label getxPoints() {
		return xPoints;
	}



	public Label getoPoints() {
		return oPoints;
	}



	@FXML
	protected void clicked() {
		
		
	}

	public Label getCurrentPlayer() {
		return currentPlayer;
	}



	@FXML
	protected void released() {
		currentPlayer.setText("TEST");
	}

}
