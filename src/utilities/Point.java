package utilities;
/**
 * @author shmuel moha 204568323
 * @author alexs vizman 314342064
 *
 */
public class Point {

	private double x;
	private double y;
	private double MIN_X = 0;
	private double MAX_X = 10000000;
	private double MIN_Y = 0;
	private double MAX_Y = 800;
	
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
		if(x>this.getMIN_X() && x<this.getMAX_X()) {
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
		if(y>this.getMIN_Y() && y<this.getMAX_Y()) {
			this.y = y;
			return true;
		}
		return false;
	}
	/**
	 * @return the mAX_X
	 */
	public double getMAX_X() {
		return MAX_X;
	}
	/**
	 * @param mAX_X the mAX_X to set
	 */
	public void setMAX_X(double mAX_X) {
		MAX_X = mAX_X;
	}
	/**
	 * @return the mIN_X
	 */
	public double getMIN_X() {
		return MIN_X;
	}
	/**
	 * @param mIN_X the mIN_X to set
	 */
	public void setMIN_X(double mIN_X) {
		MIN_X = mIN_X;
	}
	/**
	 * @return the mIN_Y
	 */
	public double getMIN_Y() {
		return MIN_Y;
	}
	/**
	 * @param mIN_Y the mIN_Y to set
	 */
	public void setMIN_Y(double mIN_Y) {
		MIN_Y = mIN_Y;
	}
	/**
	 * @return the mAX_Y
	 */
	public double getMAX_Y() {
		return MAX_Y;
	}
	/**
	 * @param mAX_Y the mAX_Y to set
	 */
	public void setMAX_Y(double mAX_Y) {
		MAX_Y = mAX_Y;
	}



}
