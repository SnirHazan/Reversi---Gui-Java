package myapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Class of ReversiGameController.
 */
public class ReversiGameController implements Initializable {
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
	private MenuBar bar = new MenuBar();
	@FXML
	private Menu reversi = new Menu("Reversi");
	@FXML
	private MenuItem exit = new MenuItem("Exit");
	@FXML
	private MenuItem settings = new MenuItem("Settings");

	private SettingBox set = new SettingBox();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		reversiBoard = new ReversiBoard(this);

		reversiBoard.setPrefWidth(400);
		reversiBoard.setPrefHeight(400);
		son.getChildren().add(0,reversiBoard);

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
	/** 
	 * @return xPoints - int the point of the first player.
	 */
	public Label getxPoints() {
		return xPoints;
	}
	/** 
	 * @return oPoints - int the point of the second player.
	 */
	public Label getoPoints() {
		return oPoints;
	}
	/**
	 * @return Label - currentPlayer Label.
	 */
	public Label getCurrentPlayer() {
		return currentPlayer;
	}
	/**
	 * @return Char - currentPlayer symbol.
	 */
	public char getCurrentPlayerSymbol(){
		if(this.getCurrentPlayer().getText().equals("player1")){
			return 'X';
		}
		return 'O';
	}
	/**
	 * This function switch current player.
	 */
	public void switchPlayer(){
		if(this.getCurrentPlayerSymbol() == 'X'){
			this.currentPlayer.setText("player2");
		} else {
			this.currentPlayer.setText("player1");
		}
	}
}
