package game.racers.air;

import game.racers.Racer;
import utilities.EnumContainer.Color;

public class Helicopter extends Racer implements AerialRacer{
	private  int SerialNumber;
	private static double  maxSpeed =400;
	private static double acceleration=50;
	private static Color color =Color.BLUE;
	
	public Helicopter() {
		super("Helicopter #"+Racer.getSerialNumber(), maxSpeed, acceleration, color);
		SerialNumber=Racer.getSerialNumber();
		Racer.setSerialNumber(Racer.getSerialNumber()+1);
	}
	public Helicopter(String name, double maxSpeed, double acceleration, Color color) {
		super(name, maxSpeed, acceleration, color);
		SerialNumber=Racer.getSerialNumber();
		Racer.setSerialNumber(Racer.getSerialNumber()+1);
	}
	
	/* (non-Javadoc)
	 * @see game.racers.Racer#describeSpecific()
	 */
	@Override
	public String describeSpecific() {
		
		return "";
	}

	/* (non-Javadoc)
	 * @see game.racers.Racer#describeRacer()
	 */
	@Override
	public String describeRacer() {
		
		return "name:"+this.getName()+","+" SerialNumber: "+this.SerialNumber+" maxSpeed: "+this.getMaxSpeed()+","+
				" acceleration: "+this.getAcceleration()+ ","+"Color: "+this.getColor()+" ";
	}


	/* (non-Javadoc)
	 * @see game.racers.Racer#className()
	 */
	@Override
	public String className() {
		return "Helicopter";
	}


}
