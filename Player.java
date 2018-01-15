package myapp;
import javafx.scene.paint.Color;

public class Player {
	
	private String name;
	private char symbol;
	private int points;
	private Color color = null;
	
	public Player(String n, char symbol){
		this.name = n;
		this.symbol = symbol;
	}
	
	public char getSymbol(){
		return this.symbol;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getPoints(){
		return this.points;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	


}
