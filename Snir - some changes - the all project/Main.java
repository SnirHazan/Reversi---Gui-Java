package myapp;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
/**
 * Main class.
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			VBox root = (VBox)FXMLLoader.load(getClass().getResource("MazeGame.fxml"));
			Scene scene = new Scene(root,800,600);
			primaryStage.setOnCloseRequest(e->{
				String ans = ConfirmBox.Display("Exit", "Do you really want to exit?");
				if(ans == "yes"){
					primaryStage.close();
				}
				e.consume();
			});
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Reversi Game");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			//root.requestFocus();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}