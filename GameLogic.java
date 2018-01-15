package myapp;

import java.io.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import javafx.scene.paint.Color;


public class GameLogic {
	//private Scanner scanner;
	private boolean should_stop;
	private Player[] players = new Player[2];
	public String firstPlayer;
	private Board board;

	public GameLogic(){
		this.should_stop = false;
		this.players[0] = new Player("player1", 'X');
		this.players[1] = new Player("player2", 'O');
		this.board =new Board(8);
		readFromFile();

		this.init_start_board();
	}

	private void readFromFile(){
		BufferedReader reader = null;
		String path = getClass().getClassLoader().getResource("").getPath();
		try {
			reader = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(new File(path + "\\myapp\\define.txt"))));

			// read all lines into the buffer
			String line = reader.readLine();
			while (line != null) {
				StringBuilder contentBuffer = new StringBuilder();
				contentBuffer.append(line.trim());
				String[] parts = contentBuffer.toString().split(";");
				String s1 = parts[0];
				String s2 = parts[1];

				if(s1.equals("start_player")) {
					firstPlayer = s2;
				} else if(s1.equals("color_player1")) {
					players[0].setColor(Color.web(s2));
				} else if(s1.equals("color_player2")) {
					players[1].setColor(Color.web(s2));
				} else if(s1.equals("board_size")) {
					this.board = new Board(Integer.parseInt(s2));
				}

				line = reader.readLine();
			}
		} catch (IOException e) {
			System.out.println("not found");
			e.printStackTrace();
		}

	}
	private void init_start_board() {
		this.board.set_matrix(this.board.getSize()/2 - 1,this.board.getSize()/2 - 1,'O');
		this.board.set_matrix(this.board.getSize()/2,this.board.getSize()/2,'O');
		this.board.set_matrix(this.board.getSize()/2,this.board.getSize()/2 - 1,'X');
		this.board.set_matrix(this.board.getSize()/2 - 1,this.board.getSize()/2,'X');
	}

	public boolean is_should_stop(){
		return this.should_stop;
	}

	public void set_should_stop(boolean shouldStop){
		this.should_stop = shouldStop;
	}

	public void print_board(){
		this.board.print_matrix();
	}
	
	public Player get_player(int i) {
		return players[i-1];
	}
	 
	public Board getBoard(){
		return this.board;
	}

	public int play_one_turn(char symbol,int row,int col) {
		ArrayList<Point> start_points = new ArrayList<Point>();
		ArrayList<Point> end_points = new ArrayList<Point>();
		//System.out.println(p1.getName() + " - '" + p1.getSymbol() + "' turn");
		this.find_options(symbol,start_points,end_points);
		//order all points to print
		if (start_points.isEmpty() == true) {
			return 1;
		}
		Set <Point> s = new TreeSet<Point>();
		for (Point point : start_points) {
			s.add(point);
		}
		// check if need -1 -1###########################################################
		if(is_point_in_set(new Point(row, col), s) == true) {
			change_all_points(symbol,new Point(row,col),start_points,end_points);
			//
			return 0;
		}
		//	System.out.println("not good option - try again.");
		return -1;
	}

	private void find_options(char symbol, ArrayList<Point> start, ArrayList<Point> end) {

		int size = this.board.getSize();
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++ ) {

				Point p = new Point(-1,-1);
				if (this.board.get_cell(row,col) == ' ') {
					//check 8 directions for each dir . save start point, end point and flip count
					p = this.check_right(row,col,symbol);
					if (p.valid_point() == true) {
						start.add(new Point(row,col));
						end.add(p);
					}
					p = this.check_left(row,col,symbol);
					if (p.valid_point() == true) {
						start.add(new Point(row,col));
						end.add(p);
					}
					p = this.check_up(row,col,symbol);
					if (p.valid_point() == true) {
						start.add(new Point(row,col));
						end.add(p);
					}
					p = this.check_down(row,col,symbol);
					if (p.valid_point() == true) {
						start.add(new Point(row,col));
						end.add(p);
					}
					p = this.check_up_right(row,col,symbol);
					if (p.valid_point() == true) {
						start.add(new Point(row,col));
						end.add(p);
					}
					p = this.check_down_right(row,col,symbol);
					if (p.valid_point() == true){
						start.add(new Point(row,col));
						end.add(p);
					}
					p = this.check_up_left(row,col,symbol);
					if (p.valid_point() == true) {
						start.add(new Point(row,col));
						end.add(p);
					}
					p = this.check_down_left(row,col,symbol);
					if (p.valid_point() == true) {
						start.add(new Point(row,col));
						end.add(p);
					}
				}

			}
		}
	}
	private Point check_right(int row, int col, char symbol) {
		int i = row;
		int j = col + 1;
		char flip_symbol;
		int flip_ctr = 0;

		if (symbol == 'X') {
			flip_symbol = 'O';
		} else {
			flip_symbol = 'X';
		}
		for(; j < this.board.getSize() - 1; j++) {
			if (this.board.get_cell(i,j) == flip_symbol) {
				flip_ctr++;
			} else {
				break;
			}
		}
		if (flip_ctr > 0 && this.board.get_cell(i,j) == symbol) {
			return new Point(i,j);
		}
		return new Point(-1,-1);
	}

	private Point check_left(int row, int col, char symbol){
		int i = row;
		int j = col - 1;
		int flip_ctr = 0;
		char flip_symbol;

		if (symbol == 'X') {
			flip_symbol = 'O';
		} else {
			flip_symbol = 'X';
		}
		for(; j > 0; j--) {
			if (this.board.get_cell(i,j) == flip_symbol) {
				flip_ctr++;
			} else {
				break;
			}
		}
		if (flip_ctr > 0 && this.board.get_cell(i,j) == symbol) {
			return new Point(i,j);
		}
		return new Point(-1,-1);
	}

	private Point check_up(int row, int col, char symbol) {
		int i = row - 1;
		int j = col;
		int flip_ctr = 0;
		char flip_symbol;

		if (symbol == 'X') {
			flip_symbol = 'O';
		} else {
			flip_symbol = 'X';
		}
		for(; i > 0; i--) {
			if (this.board.get_cell(i,j) == flip_symbol) {
				flip_ctr++;
			} else {
				break;
			}
		}
		if (flip_ctr > 0 && this.board.get_cell(i,j) == symbol) {
			return new Point(i,j);

		}
		return new Point(-1,-1);
	}

	private Point check_down(int row, int col, char symbol){
		int i = row + 1;
		int j = col;
		int flip_ctr = 0;
		char flip_symbol;

		if (symbol == 'X') {
			flip_symbol = 'O';
		} else {
			flip_symbol = 'X';
		}
		for(; i < this.board.getSize() - 1; i++) {
			if (this.board.get_cell(i,j) == flip_symbol) {
				flip_ctr++;
			} else {
				break;
			}
		}
		if (flip_ctr > 0 && this.board.get_cell(i,j) == symbol) {
			return new Point(i,j);
		}
		return new Point(-1,-1);
	}

	private Point check_up_right(int row, int col,char symbol){
		int i = row - 1;
		int j = col + 1;
		int flip_ctr = 0;
		char flip_symbol;

		if (symbol == 'X') {
			flip_symbol = 'O';
		} else {
			flip_symbol = 'X';
		}
		int size = this.board.getSize();
		for(; j < size - 1 && i > 0; j++, i--) {
			if (this.board.get_cell(i,j) == flip_symbol) {
				flip_ctr++;
			} else {
				break;
			}
		}
		if (flip_ctr > 0 && this.board.get_cell(i,j) == symbol) {
			return new Point(i,j);
		}
		return new Point(-1,-1);
	}

	private Point check_down_right(int row, int col, char symbol) {
		int i = row + 1;
		int j = col + 1;
		int flip_ctr = 0;
		char flip_symbol;
		if (symbol == 'X') {
			flip_symbol = 'O';
		} else {
			flip_symbol = 'X';
		}
		int size =this.board.getSize();
		for(; j < size - 1 && i < size -1; j++, i++) {
			if (this.board.get_cell(i,j) == flip_symbol) {
				flip_ctr++;
			} else {
				break;
			}
		}
		if (flip_ctr > 0 && this.board.get_cell(i,j) == symbol) {
			return new Point(i,j);
		}
		return new Point(-1,-1);
	}

	private Point check_up_left(int row, int col, char symbol) {
		int i = row - 1;
		int j = col - 1;
		int flip_ctr = 0;
		char flip_symbol;
		if (symbol == 'X') {
			flip_symbol = 'O';
		} else {
			flip_symbol = 'X';
		}

		for(; j > 0 && i > 0; j--,i--) {
			if (this.board.get_cell(i,j) == flip_symbol) {
				flip_ctr++;
			} else {
				break;
			}
		}
		if (flip_ctr > 0 && this.board.get_cell(i,j) == symbol) {
			return new Point(i,j);
		}
		return new Point(-1,-1);
	}

	private Point check_down_left(int row, int col, char symbol) {
		int i = row + 1;
		int j = col - 1;
		int flip_ctr = 0;
		char flip_symbol;
		if (symbol == 'X') {
			flip_symbol = 'O';
		} else {
			flip_symbol = 'X';
		}
		int size = this.board.getSize();
		for(; j > 0 && i < size - 1; j--,i++) {
			if (this.board.get_cell(i,j) == flip_symbol) {
				flip_ctr++;
			} else {
				break;
			}
		}
		if (flip_ctr > 0 && this.board.get_cell(i,j) == symbol) {
			return new Point(i,j);
		}
		flip_ctr = 0;
		return new Point(-1,-1);
	}


	private void change_all_points(char symbol, Point point, ArrayList<Point> start_points, ArrayList<Point> end_points) {
		//System.out.println(p1.getName() + " played: " + point.toString());
		for (int i = 0; i < start_points.size(); i++) {
			if(start_points.get(i).isEqual(point)) {
				change_board_point_to_point(start_points.get(i),end_points.get(i),symbol);
			}
		}

	}

	private void change_board_point_to_point(Point p1, Point p2,char symbol) {
		this.board.set_matrix(p1.getX(),p1.getY(),symbol);
		int x_s = p1.getX();
		int y_s = p1.getY();
		int x_e = p2.getX();
		int y_e = p2.getY();

		if(x_s == x_e) {
			if(y_s < y_e){
				for(int i = y_s + 1; i < y_e; i++){
					this.board.set_matrix(x_s,i,symbol);
				}
			} else {
				for(int i = y_s -1; i > y_e; i--){
					this.board.set_matrix(x_s,i,symbol);
				}
			}
		} else if(y_s == y_e) {
			if(x_s < x_e){
				for(int i = x_s + 1; i < x_e; i++){
					this.board.set_matrix(i,y_s,symbol);
				}
			} else {
				for(int i = x_s -1; i > x_e; i--){
					this.board.set_matrix(i,y_s,symbol);
				}
			}
		} else if(x_s > x_e) {
			if(y_s < y_e){
				for(int i = x_s - 1, j = y_s + 1 ; i > x_e; i--,j++){
					this.board.set_matrix(i,j,symbol);
				}
			} else {
				for(int i = x_s - 1, j = y_s - 1 ; i > x_e; i--,j--){
					this.board.set_matrix(i,j,symbol);
				}
			}
		} else {
			if(y_s < y_e){
				for(int i = x_s + 1, j = y_s +1 ; i < x_e; i++,j++){
					this.board.set_matrix(i,j,symbol);
				}
			} else {
				for(int i = x_s + 1, j = y_s - 1 ; i < x_e; i++,j--){
					this.board.set_matrix(i,j,symbol);
				}
			}
		}
	}
	public boolean is_point_in_set(Point p, Set<Point> s) {
		for (Point point : s) {
			if(point.isEqual(p)) {
				return true;
			}
		}
		return false;
	}

	public boolean board_full() {
		int size = this.board.getSize();
		if(this.board.player_points('X') + this.board.player_points('O') == size*size) {
			return true;
		}
		return false;
	}

}
