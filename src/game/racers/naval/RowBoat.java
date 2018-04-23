package game.racers.naval;

import game.racers.Racer;
import game.racers.Wheeled;
import utilities.EnumContainer.Breed;
import utilities.EnumContainer.Color;
import utilities.EnumContainer.Team;
import utilities.EnumContainer.Type;

public class RowBoat extends Racer {
	private int SerialNumber;
	private String name;
	private static double  maxSpeed =75;
	private static double acceleration=10;
	private static Color color =Color.RED;
	private Team team=Team.DOUBLE;
	private Type type =Type.SKULLING;
	
	public RowBoat(String name, double maxSpeed, double acceleration, Color color) {
		super(name, maxSpeed, acceleration, color);
		SerialNumber=Racer.getSerialNumber();
		Racer.setSerialNumber(Racer.getSerialNumber()+1);
		
	}
	public RowBoat() {
		super("RowBoat #"+Racer.getSerialNumber(), maxSpeed, acceleration, color);
		SerialNumber=Racer.getSerialNumber();
		Racer.setSerialNumber(Racer.getSerialNumber()+1);
	}

	/* (non-Javadoc)
	 * @see game.racers.Racer#describeSpecific()
	 */
	@Override
	public String describeSpecific() {
		
		return "Team:"+this.getTeam()+","+"Type:"+this.getType();
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
		return "RowBoat";
	}

	/**
	 * @return the team
	 */
	public Team getTeam() {
		return team;
	}
	/**
	 * @param team the team to set
	 */
	public void setTeam(Team team) {
		this.team = team;
	}
	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}

}
