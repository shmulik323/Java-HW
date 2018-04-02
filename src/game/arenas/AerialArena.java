package game.arenas;

import utilities.EnumContainer.*;

public class AerialArena extends Arena{

	public static final int MAX_RACERS = 6;
	private static final double FRICTION = 0.4;
	private static final int length=1500;
	private static Vision vision=Vision.SUNNY;
	private static Weather weather =Weather.DRY;
	private static Height height =Height.HIGH;
	private static Wind wind =Wind.HIGH;
	
	public AerialArena(int length, int maxRacers) {
		super(length,maxRacers,FRICTION);
	}
	public AerialArena() {
		super(length,MAX_RACERS,FRICTION);
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
