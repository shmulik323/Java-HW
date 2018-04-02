package utilities;

public class Point {

	private double x;
	private double y;
	/**
	 * class constructor, sets values to x and y
	 * @param i
	 * @param j
	 */
	public Point(int i, int j) {
		this.setX(i);
		this.setY(j);
	}
	/**
	 * compares two point coordinates to see if they are equal
	 * @param x , point object
	 * @return boolean value
	 */
	public boolean equals(Point x) {
		if(x.getX()==this.getX()&& x.getY()== this.getY())
			return true;
		return false;
	}
	public String toString() {
		return "("+this.getX()+","+this.getY()+")";
	}
	public static void main(String[] args) {

	}
	/**
	 * @param getX, gets the current value of x
	 * @return x
	 */
	public double getX() {
		return x;
	}
	/**
	 * 
	 * @param x, sets new value to x
	 * @return 
	 */
	public boolean setX(double x) {
		if(x>0) {
			this.x = x;
			return true;
		}
		return false;
	}
	/**
	 * @param getY, gets the current value of y
	 * @return y
	 */
	public double getY() {
		return y;
	}
	/**
	 * 
	 * @param y, sets new value to y
	 */
	public boolean setY(double y) {
		if(y>0) {
			this.y = y;
			return true;
		}
		return false;
	}



}
