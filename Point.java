package myapp;

public class Point implements Comparable<Object> {
	
	private int xCor;
	private int yCor;
	
	public Point(int x , int y){
		
		this.xCor = x;
		this.yCor = y;
	}
	
	public int getX(){
		return this.xCor;
	}
	public int getY(){
		return this.yCor;
	}
	public boolean isEqual(Point p){
		if(this.xCor == p.getX() && this.yCor == p.getY()) {
			return true;
		}
		return false;
	}
	@Override
	public String toString(){
		return "("+ (this.getX()) + "," + (this.getY()) + ") ";
	}
	
	public boolean valid_point() {
		if (this.getX() < 0 || this.getX() > 7 || this.getY() < 0 || this.getY() > 7 ){
			return false;
		}
		return true;
	}
	
	public int compareTo(Object other){
		if(other instanceof Point){
			Point p1 = (Point) other;
			if(this.xCor == p1.getX() && this.yCor == p1.getY()){
				return 0;
			} else {
				return -1;
			}
		} else {
			return -2;
		}
	}

}
