package myapp;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SettingBox {
	
	public void Display(String title) throws IOException{
		
		Stage win = new Stage();
		win.setTitle(title);
		win.initModality(Modality.APPLICATION_MODAL);
		win.setMinWidth(250);
 	    win.setResizable(false);
		Pane root = (Pane)FXMLLoader.load(getClass().getResource("Set.fxml"));
		Scene scene = new Scene(root,450,300);
		win.setScene(scene);
		win.showAndWait();
		
		
	}

}
