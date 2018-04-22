package game.racers.land;

import game.racers.Racer;
import game.racers.Wheeled;
import utilities.EnumContainer.Color;
import utilities.EnumContainer.Engine;

public class Car extends Racer implements LandRacer {


	private static int numOfWheels=4;
	private static double  maxSpeed =400;
	private static double acceleration=20;
	private static Color color =Color.RED;
	private Engine engine =Engine.BOXER;
	private Wheeled wheeled;
	private int SerialNumber;
	public Car(String name, double maxSpeed, double acceleration, Color color,int numOfWheels) {
		super(name, maxSpeed, acceleration, color);
		this.wheeled=new Wheeled(numOfWheels);
		// TODO Auto-generated constructor stub
	}
	public Car(String name, double maxSpeed, double acceleration, Color color) {
		super(name, maxSpeed, acceleration, color);
		this.wheeled=new Wheeled(numOfWheels);
		SerialNumber=this.getSerialNumber();
		if(name==null) {
			this.setName("Car"+this.getSerialNumber());
		}
//		this.setSerialNumber(this.getSerialNumber()+1);
	}
	public Car() {
		super("Car", maxSpeed, acceleration, color);
		this.wheeled=new Wheeled(numOfWheels);
		SerialNumber=this.getSerialNumber();
		this.setSerialNumber(this.getSerialNumber()+1);
	}
	

	/* (non-Javadoc)
	 * @see game.racers.Racer#describeSpecific()
	 */
	@Override
	public String describeSpecific() {
		
		return " NumOfWheels: "+ this.wheeled.getNumOfWheels()+","+" Engine: "+this.getEngine();
	}

	/* (non-Javadoc)
	 * @see game.racers.Racer#describeRacer()
	 */
	@Override
	public String describeRacer() {
		
		return "name: "+this.getName()+","+" SerialNumber: "+this.getSerialNumber()+" maxSpeed: "+this.maxSpeed+","+
				" acceleration: "+this.acceleration+ ","+this.describeSpecific();
	}

	/* (non-Javadoc)
	 * @see game.racers.Racer#introduce()
	 */
	@Override
	public void introduce() {
		System.out.println("["+this.className()+"]"+this.describeRacer());

	}

	/* (non-Javadoc)
	 * @see game.racers.Racer#className()
	 */
	@Override
	public String className() {
		return "Car";
	}
	/**
	 * @return the engine
	 */
	public Engine getEngine() {
		return engine;
	}
	/**
	 * @param engine the engine to set
	 */
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	/**
	 * @return the serialNumber
	 */



}
