package myapp;

import java.util.Arrays;

public class Board {

	private int size;
	private char[][] matrix;

	public Board(int size){
		this.size = size;
		this.matrix = new char[size][size];
		this.init_matrix();
	}
	public void init_matrix(){
		for (int i = 0; i < size; i++) {
			for(int j = 0; j< size; j++)
				this.matrix[i][j] = ' ';
		}
		this.matrix[size/2 -1][size/2 -1] = 'O';
		this.matrix[size/2][size/2 ] = 'O';
		this.matrix[size/2][size/2 -1] = 'X';
		this.matrix[size/2 -1][size/2 ] = 'X';
	}

	public void set_matrix(int row, int col, char symbol) {
		if (symbol != 'X' && symbol != 'O') {
			System.out.println("Unrecognized symbol.");
			return;
		} else {
			this.matrix[row][col] = symbol;
		}
	}

	public void print_matrix() {
		//print first row
		System.out.print(" |");
		for (int i = 0; i < size; i++) {
			System.out.print(" " + (i + 1) + " |");
		}
		System.out.println();
		char[] c1 = new char[4 * size + 2];
		Arrays.fill(c1, '-');
		System.out.println(new String(c1));
		for ( int i = 0; i < size; i++) {
			System.out.print( (i+1)+ "|");
			// run all columns in the row
			for ( int j = 0; j < size; j++) {
				System.out.print(" "+ this.matrix[i][j] + " |");
			}
			//print an empty row
			System.out.println();
			char[] c2 = new char[4 * size + 2];
			Arrays.fill(c2, '-');
			System.out.println(new String(c2));		
			}
	}

	public char get_cell(int row,int col){
		return this.matrix[row][col];
	}

	public int getSize(){
		return this.size;
	}
	
	public int check_score(char c){
		int counter = 0;
		for(int i = 0 ; i < size ; i++){
			for(int j = 0 ; j < size ; j++){
				if(this.get_cell(i, j) == c){
					counter++;
				}
			}
		}
		return counter;
	}
	
	public int player_points(char c) {
		int points = 0;
		for(int i = 0; i< size; i++){
			for(int j=0; j<size; j++) {
				if(matrix[i][j] == c){
					points++;
				}
			}
		}
		return points;
	}

}
