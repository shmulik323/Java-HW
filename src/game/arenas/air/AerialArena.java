package game.arenas.air;

import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.air.AerialRacer;
import utilities.EnumContainer.*;

public class AerialArena extends Arena{

	public static final int MAX_RACERS = 6;
	private static final double FRICTION = 0.4;
	private static final double length=1500;
	private static Vision vision=Vision.SUNNY;
	private static Weather weather =Weather.DRY;
	private static Height height =Height.HIGH;
	private static Wind wind =Wind.HIGH;
	
	public AerialArena(double length, int maxRacers) {
		super(length,maxRacers,FRICTION);
	}
	public AerialArena() {
		super(length,MAX_RACERS,FRICTION);
	}
	

	

	public void addRacer(Racer newRacer) throws RacerLimitException, RacerTypeException {
		String message;
		if(!(newRacer instanceof AerialRacer)) {
			 message = "Invalid Racer of type"+" "+'"'+newRacer.className()+'"'+"for "+this.getClass().getSimpleName();
			throw new RacerTypeException(message);
		}
		if(this.getActiveRacers().size()==this.getMAX_RACERS()) {
			message = "Arena is full!"+"("+this.getActiveRacers().size()+" active racers exist)."+"racer #"+newRacer.getSerialNumber()+" was not added";
			throw new RacerLimitException(message);
		}

		else {
			this.getActiveRacers().add(newRacer);
		}
	}
	/**
	 * @return the vision
	 */
	public static Vision getVision() {
		return vision;
	}
	public static void seVision(Vision vis) {
		vision=vis;
	}

	/**
	 * @return the weather
	 */
	public static Weather getWeather() {
		return weather;
	}
	public static void setWeather(Weather weath) {
		weather=weath;
	}
	/**
	 * @return the height
	 */
	public static Height getHeight() {
		return height;
	}
	public static void setHeight(Height hei) {
		height=hei;
	}
	/**
	 * @return the wind
	 */
	public static Wind getWind() {
		return wind;
	}
	public static void setWind(Wind w) {
		wind=w;
	}
}
