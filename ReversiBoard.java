package myapp;

import java.io.IOException;
import java.util.ArrayList;

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
	private static final char PLAYER_1 = 'X';
	private static final char PLAYER_2 = 'O';
	private ReversiGameController rgc;

	public ReversiBoard(ReversiGameController rgc) {
		this.gl = new GameLogic();
		this.rgc = rgc;
		rgc.getCurrentPlayer().setText(gl.firstPlayer);
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
		boolean noMoveFlag = false;
		boolean endGameFlag = false;
		ArrayList<Point> start_points = new ArrayList<Point>();
		ArrayList<Point> end_points = new ArrayList<Point>();

		gl.find_options(rgc.getCurrentPlayerSymbol(),start_points,end_points);

		if(start_points.isEmpty()){
			rgc.switchPlayer();
			start_points.clear();
			end_points.clear();

			noMoveFlag =true;


			gl.find_options(rgc.getCurrentPlayerSymbol(),start_points,end_points);
			if(start_points.isEmpty()){
				endGameFlag = true;
			}

		} else {
			this.getChildren().clear();
		}

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
				if (gl.getBoard().get_cell(i, j) == PLAYER_1) {
					this.add(rect, j, i);
					this.addDisk(j , i , cellHeight, start_points, end_points, gl.get_player(1).getColor());
				} else if(gl.getBoard().get_cell(i, j) == PLAYER_2) {
					this.add(rect, j, i);
					this.addDisk(j , i , cellHeight, start_points, end_points, gl.get_player(2).getColor());
				} else  {
					this.add(rect, j, i);

					rect.setOnMouseClicked(event ->{
						int row = GridPane.getRowIndex(rect);
						int col = GridPane.getColumnIndex(rect);
						String s = this.rgc.getCurrentPlayer().getText();

						if(s.equals(gl.get_player(1).getName())){
							if(gl.play_one_turn(gl.get_player(1).getSymbol(),start_points,end_points, row, col) != -1) {
								rgc.getCurrentPlayer().setText(gl.get_player(2).getName());
								this.draw();
							}
						} else {
							if(gl.play_one_turn(gl.get_player(2).getSymbol(),start_points,end_points, row, col) != -1) {
								rgc.getCurrentPlayer().setText(gl.get_player(1).getName());
								this.draw();
							}
						}
					});

				}
			}

		}

		for (Point point : start_points) {
			this.addDisk(point.getY(), point.getX(), cellHeight, start_points, end_points, Color.TRANSPARENT);
		}

		if(gl.board_full() || endGameFlag){
			endOfGame();
		} else if(noMoveFlag==true){
			AlertBox.Display("Reversi", "No available move!");
		}
	}

	private void endOfGame(){
		int player1Point = Integer.parseInt(this.rgc.getxPoints().getText());
		int player2Point = Integer.parseInt(this.rgc.getoPoints().getText());
		String message = "THE WINNER IS PLAYER1";
		if(player2Point > player1Point) {
			message = "THE WINNER IS PLAYER2";
		} else if ( player1Point == player2Point) {
			message = "TIKO TIKO SHIVAION";
		}
		AlertBox.Display("End of Game", message);
		Stage window =(Stage) this.getScene().getWindow();
		window.close();

	}
	private void addDisk(int j, int i, double cellHeight ,ArrayList<Point> start_points,ArrayList<Point> end_points,Color playerColor){
		Circle down_circle;
		if(playerColor.toString().equals("0x000000ff")){
			down_circle = new Circle(cellHeight/2,Color.WHITE);
		} else if(playerColor.toString().equals("0xffffffff")) {
			down_circle = new Circle(cellHeight/2,Color.BLACK);
		} else {
			down_circle = new Circle(cellHeight/2,Color.TRANSPARENT);
		}
		down_circle.setStroke(Color.BLACK);
		this.add(down_circle, j, i);

		Circle up_circle = new Circle(cellHeight/2 - 1,playerColor);
		up_circle.setStroke(Color.BLACK);
		GridPane.setHalignment(up_circle, HPos.RIGHT);
		GridPane.setValignment(up_circle, VPos.TOP);

		this.add(up_circle, j, i);

		up_circle.setOnMouseClicked(event ->{
			int row = GridPane.getRowIndex(up_circle);
			int col = GridPane.getColumnIndex(up_circle);

			String s = this.rgc.getCurrentPlayer().getText();
			if(s.equals(gl.get_player(1).getName())){
				if(gl.play_one_turn(gl.get_player(1).getSymbol(),start_points,end_points, row, col) != -1) {
					rgc.getCurrentPlayer().setText(gl.get_player(2).getName());
					this.draw();
				}
			} else {
				if(gl.play_one_turn(gl.get_player(2).getSymbol(),start_points,end_points, row, col) != -1) {
					rgc.getCurrentPlayer().setText(gl.get_player(1).getName());
					this.draw();
				}
			}
		});
	}


}

