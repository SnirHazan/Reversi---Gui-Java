package myapp;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ReversiBoard extends GridPane {
	private GameLogic gl;
	//private static final char BLACK = 'X';
	private static final char WHITE = 'O';
	private ReversiGameController rgc;
	
	public ReversiBoard(ReversiGameController rgc) {
		this.gl = new GameLogic();
		this.rgc = rgc;
		rgc.getCurrentPlayer().setText(gl.firstPlayer);
		//this.draw();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MazeBoard.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}


	}


	public void draw() {
		this.getChildren().clear();

		this.rgc.getxPoints().setText(String.valueOf(gl.getBoard().player_points('X')));
		this.rgc.getoPoints().setText(String.valueOf(gl.getBoard().player_points('O')));

		double height = (double)this.getPrefHeight() - 75;
		double width = (double)this.getPrefWidth() - 75;
		double size = gl.getBoard().getSize();
		double cellHeight = (double)height /(double) size;
		double cellWidth = (double)width / (double) size;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Rectangle rect = new Rectangle(cellWidth, cellHeight, Color.web("0xfddb6e"));
				rect.setStroke(Color.BLACK);
				if (gl.getBoard().get_cell(i, j) == 'X') {
					this.add(rect, j, i);
					this.addDisk(j , i , cellHeight, gl.get_player(1).getColor());
				} else if(gl.getBoard().get_cell(i, j) == WHITE) {
					this.add(rect, j, i);
					this.addDisk(j , i , cellHeight, gl.get_player(2).getColor());
				} else {
					rect.setOnMouseClicked(event ->{
						int row = GridPane.getRowIndex(rect);
						int col = GridPane.getColumnIndex(rect);
						String s = this.rgc.getCurrentPlayer().getText();
						if(s.equals(gl.get_player(1).getName())){
							if(gl.play_one_turn(gl.get_player(1).getSymbol(), row, col) != -1) {
								rgc.getCurrentPlayer().setText(gl.get_player(2).getName());
								this.draw();
							}
						} else {
							if(gl.play_one_turn(gl.get_player(2).getSymbol(), row, col) != -1) {
								rgc.getCurrentPlayer().setText(gl.get_player(1).getName());
								this.draw();
							}
						}
					});
					this.add(rect, j, i);
				}
			}
		}
		if(gl.board_full()){
			endOfGame();
		}
	}

	private void endOfGame(){
		int x = Integer.parseInt(this.rgc.getxPoints().getText());
		int o = Integer.parseInt(this.rgc.getoPoints().getText());
		String message = "player 1 wins";
		if(o > x) {
			message = "player 2 is vectorius";
		} else if ( x==o) {
			message = "TIKO TIKO SHIVAION";
		}
		AlertBox.Display("End of Game", message);
		Stage window =(Stage) this.getScene().getWindow();
		window.close();

	}

	private void addDisk(int j, int i, double cellHeight ,Color playerColor){
		Circle down_circle;
		if(playerColor.toString().equals("0x000000ff")){
			down_circle = new Circle(cellHeight/2,Color.WHITE);
		} else {
			down_circle = new Circle(cellHeight/2,Color.BLACK);
		}
		down_circle.setStroke(Color.BLACK);
		this.add(down_circle, j, i);

		Circle up_circle = new Circle(cellHeight/2 - 1,playerColor);
		up_circle.setStroke(Color.BLACK);
		GridPane.setHalignment(up_circle, HPos.RIGHT);
		GridPane.setValignment(up_circle, VPos.TOP);
		this.add(up_circle, j, i);
	}
}

