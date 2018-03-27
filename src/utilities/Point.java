package utilities;

public class Point {

	private double x;
	private double y;

	public Point(int i, int j) {
		this.setX(i);
		this.setY(j);
	}
	public boolean equals(Point x) {
		if(x.getX()==this.getX()&& x.getY()== this.getY())
			return true;
		return false;
	}
	public static void main(String[] args) {

	}
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}



}
