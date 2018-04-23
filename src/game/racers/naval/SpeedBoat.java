package game.racers.naval;

import game.racers.Racer;
import utilities.EnumContainer.Color;
import utilities.EnumContainer.Team;
import utilities.EnumContainer.Type;

public class SpeedBoat extends Racer {

	private int SerialNumber;
	private static double  maxSpeed =170;
	private static double acceleration=10;
	private static Color color =Color.RED;
	
	public SpeedBoat(String name, double maxSpeed, double acceleration, Color color) {
		super(name, maxSpeed, acceleration, color);
		SerialNumber=Racer.getSerialNumber();
		Racer.setSerialNumber(Racer.getSerialNumber()+1);
		
	}

	public SpeedBoat() {
		super("SpeedBoat #"+getSerialNumber(), maxSpeed, acceleration, color);
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
		return "SpeedBoat";
	}

}
