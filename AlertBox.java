package myapp;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
	
	static void Display(String title, String message){
		
		Stage win = new Stage();
		win.setTitle(title);
		
		win.initModality(Modality.APPLICATION_MODAL);
		win.setMinWidth(20);
		
		Label l = new Label();
		l.setText(message);
		l.setFont(new Font(20.0));
		Button exit = new Button("Exit");
		win.setOnCloseRequest(e ->{
			win.close();
		});
		exit.setOnAction(e -> {
			win.close();
			
		});
		
		VBox lay = new VBox(10);
		lay.getChildren().addAll(exit,l);
		lay.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(lay,200,200);
		win.setScene(scene);
		
		win.showAndWait();
	
	}

}
