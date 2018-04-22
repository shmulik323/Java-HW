/**
 * 
 */
package game.racers.land;

import game.racers.Racer;
import game.racers.Wheeled;
import utilities.EnumContainer.Color;

/**
 * @author shmul
 *
 */
public class Bicycle extends Racer implements LandRacer {

	private static int numOfWheels=2;
	private static double  maxSpeed =270;
	private static double acceleration=10;
	private static Color color =Color.GREEN;
	private Wheeled wheeled;
	
	public Bicycle(String name, double maxSpeed, double acceleration, Color color,int numOfWheels) {
		super(name, maxSpeed, acceleration, color);
		wheeled=new Wheeled(numOfWheels);
		
	}
	public Bicycle(String name, double maxSpeed, double acceleration, Color color) {
		super(name, maxSpeed, acceleration, color);
		wheeled=new Wheeled(numOfWheels);
		
	}
	public Bicycle() {
		super("", maxSpeed, acceleration, color);
		wheeled=new Wheeled(numOfWheels);
	}


	/* (non-Javadoc)
	 * @see game.racers.Racer#describeSpecific()
	 */
	@Override
	public String describeSpecific() {
		
		return "NumOfWheels:"+ this.wheeled.getNumOfWheels();
	}

	/* (non-Javadoc)
	 * @see game.racers.Racer#describeRacer()
	 */
	@Override
	public String describeRacer() {
		
		return "name:"+this.getName()+","+"SerialNumber:"+this.getSerialNumber()+"maxSpeed:"+this.maxSpeed+","+
				"acceleration:"+this.acceleration+ ","+this.describeSpecific();
	}

	/* (non-Javadoc)
	 * @see game.racers.Racer#introduce()
	 */
	@Override
	public void introduce() {
		System.out.println(this.className()+this.describeRacer());

	}

	/* (non-Javadoc)
	 * @see game.racers.Racer#className()
	 */
	@Override
	public String className() {
		return "["+"Bicycle"+"]";
	}


}
