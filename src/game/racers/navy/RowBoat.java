package game.racers.navy;

import game.racers.Racer;
import utilities.EnumContainer.Breed;
import utilities.EnumContainer.Color;
import utilities.EnumContainer.Team;
import utilities.EnumContainer.Type;

public class RowBoat extends Racer {
	private String name;
	private static double  maxSpeed =270;
	private static double acceleration=10;
	private static Color color =Color.RED;
	private Team team=Team.DOUBLE;
	private Type type =Type.SKULLING;
	
	public RowBoat(String name, double maxSpeed, double acceleration, Color color) {
		super(name, maxSpeed, acceleration, color);
		// TODO Auto-generated constructor stub
	}
	public RowBoat() {
		super("", maxSpeed, acceleration, color);
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
		return "["+"RowBoat"+"]";
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
