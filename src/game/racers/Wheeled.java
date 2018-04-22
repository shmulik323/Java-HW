package game.racers;

public class Wheeled  {
	private int numOfWheels;
	
	public Wheeled(int numOfWheeles){
		this.setNumOfWheels(numOfWheeles);
	}
	public Wheeled() {
		this.setNumOfWheels(0);
	}
	/**
	 * @return the numOfWheels
	 */
	public int getNumOfWheels() {
		return numOfWheels;
	}
	/**
	 * @param numOfWheels the numOfWheels to set
	 */
	public void setNumOfWheels(int numOfWheels) {
		this.numOfWheels = numOfWheels;
	}

}
