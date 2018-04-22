package game.racers.air;

import game.racers.Racer;
import utilities.EnumContainer.Color;

public class Helicopter extends Racer implements AerialRacer{

	private String name;
	private static double  maxSpeed =400;
	private static double acceleration=50;
	private static Color color =Color.BLUE;
	
	public Helicopter() {
		super("", maxSpeed, acceleration, color);
	}
	public Helicopter(String name, double maxSpeed, double acceleration, Color color) {
		super(name, maxSpeed, acceleration, color);
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see game.racers.Racer#describeSpecific()
	 */
	@Override
	public String describeSpecific() {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see game.racers.Racer#describeRacer()
	 */
	@Override
	public String describeRacer() {
		
		return "name:"+this.name+","+"SerialNumber:"+this.getSerialNumber()+"maxSpeed:"+this.maxSpeed+","+
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
		return "["+"Helicopter"+"]";
	}
}
