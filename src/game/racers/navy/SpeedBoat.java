package game.racers.navy;

import game.racers.Racer;
import utilities.EnumContainer.Color;
import utilities.EnumContainer.Team;
import utilities.EnumContainer.Type;

public class SpeedBoat extends Racer {

	private String name;
	private static double  maxSpeed =270;
	private static double acceleration=10;
	private static Color color =Color.RED;
	
	public SpeedBoat(String name, double maxSpeed, double acceleration, Color color) {
		super(name, maxSpeed, acceleration, color);
		// TODO Auto-generated constructor stub
	}

	public SpeedBoat() {
		super("", maxSpeed, acceleration, color);
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
		return "["+"SpeedBoat"+"]";
	}

}
