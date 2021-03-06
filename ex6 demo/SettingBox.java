package myapp;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * Class of SettingsBox.
 */
public class SettingBox {
	/**
	 * This function display the SettingsBox.
	 * @param title - the title of the window.
	 */
	public void Display(String title) {
		
		Stage win = new Stage();
		win.setTitle(title);
		win.initModality(Modality.APPLICATION_MODAL);
		win.setMinWidth(250);
 	    win.setResizable(false);
		Pane root = null;
		try {
			root = (Pane)FXMLLoader.load(getClass().getResource("Set.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root,450,300);
		win.setScene(scene);
		win.showAndWait();
		
		
	}

}
