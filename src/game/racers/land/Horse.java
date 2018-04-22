package game.racers.land;

import game.arenas.land.LandArena;
import game.racers.Racer;
import utilities.Point;
import utilities.EnumContainer.Breed;
import utilities.EnumContainer.Color;
import utilities.EnumContainer.Engine;

public class Horse extends Racer implements LandRacer{
	private static double  maxSpeed =50;
	private static double acceleration=3;
	private  Breed breed =Breed.FRIESIAN ;
	private static  Color color=null;
	
	public Horse(String name, double maxSpeed, double acceleration, Color color) {
		super(name, maxSpeed, acceleration, color);
		// TODO Auto-generated constructor stub
	}
	public Horse() {
		super("", maxSpeed, acceleration, color);
	}
	

	/* (non-Javadoc)
	 * @see game.racers.Racer#describeSpecific()
	 */
	@Override
	public String describeSpecific() {
		
		return "Breed:"+this.getBreed();
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
		return "["+"Horse"+"]";
	}
	/**
	 * @return the breed
	 */
	public Breed getBreed() {
		return breed;
	}
	/**
	 * @param breed the breed to set
	 */
	public void setBreed(Breed breed) {
		this.breed = breed;
	}

}
