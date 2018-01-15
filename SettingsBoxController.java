package myapp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.stage.Stage;

public class SettingsBoxController implements Initializable{
	@FXML
	public Button Apply = new Button();
	@FXML
	public ChoiceBox<String> beginning = new ChoiceBox<>();
	@FXML
	public ChoiceBox<String> SizeBoard = new ChoiceBox<>();
	@FXML
	public ColorPicker player1Color = new ColorPicker();
	@FXML
	public ColorPicker player2Color = new ColorPicker();

	private final int minBoardSize = 4;
	private final int maxBoardSize = 20;

	public void ApplyClick() {

		WriteConfigToFile();
		Stage stage = (Stage) Apply.getScene().getWindow();
		stage.close();

	}

	private void WriteConfigToFile() {
		ClassLoader loader = this.getClass().getClassLoader();
		PrintWriter writer = null;

		try{
			writer = new PrintWriter(
					new OutputStreamWriter(
							new FileOutputStream(new File(loader.getResource("").getPath() + "\\myapp\\define.txt"))));

		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			writer.println("start_player;" + beginning.getValue());
			writer.println("color_player1;" + player1Color.getValue().toString());
			writer.println("color_player2;" + player2Color.getValue().toString());
			writer.println("board_size;" + SizeBoard.getValue());
			writer.close();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		beginning.getItems().addAll("player1","player2");

		for(int i = minBoardSize ; i <= maxBoardSize ; i+=2) {
			SizeBoard.getItems().add(String.valueOf(i));
		}


	}
}
