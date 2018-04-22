package game.racers.air;

import game.racers.Racer;
import game.racers.Wheeled;
import utilities.EnumContainer.Color;

public class Airplane extends Racer implements AerialRacer{
	private  int SerialNumber;
	private static double  maxSpeed =885;
	private static double acceleration=100;
	private static Color color =Color.BLACK;
	private Wheeled wheeled=new Wheeled(3);
	
	public Airplane(String name, double maxSpeed, double acceleration, Color color,int numOfWheels) {
		super(name, maxSpeed, acceleration, color);
		this.wheeled=new Wheeled(numOfWheels);
		SerialNumber=this.getSerialNumber();
		this.setSerialNumber(this.getSerialNumber()+1);
	}
	public Airplane(String name, double maxSpeed, double acceleration, Color color) {
		super(name, maxSpeed, acceleration, color);	
		SerialNumber=this.getSerialNumber();
		this.setSerialNumber(this.getSerialNumber()+1);
	}
	public Airplane() {
		super("", maxSpeed, acceleration, color);
		SerialNumber=this.getSerialNumber();
		this.setSerialNumber(this.getSerialNumber()+1);
	}
	
	/* (non-Javadoc)
	 * @see game.racers.Racer#describeSpecific()
	 */
	@Override
	public String describeSpecific() {
		
		return "NumOfWheels:"+this.wheeled.getNumOfWheels();
	}

	/* (non-Javadoc)
	 * @see game.racers.Racer#describeRacer()
	 */
	@Override
	public String describeRacer() {
		
		return "name:"+this.getName()+","+" SerialNumber: "+SerialNumber+" maxSpeed: "+this.getMaxSpeed()+","+
				" acceleration: "+this.getAcceleration()+ ",";
	}
	

	/* (non-Javadoc)
	 * @see game.racers.Racer#introduce()
	 */
	@Override
	public void introduce() {
		System.out.println("["+this.className()+"]"+this.describeRacer()+describeSpecific());

	}

	/* (non-Javadoc)
	 * @see game.racers.Racer#className()
	 */
	@Override
	public String className() {
		return "Airplane";
	}
	
}
