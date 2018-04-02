package game.arenas;

import java.util.ArrayList;

import game.racers.RowBoat;
import game.racers.SpeedBoat;
import utilities.EnumContainer.Body;
import utilities.EnumContainer.Surface;
import utilities.EnumContainer.Water;
import utilities.Point;

public class NavalArena extends Arena{

	public static final int MAX_RACERS = 5;
	private static final double FRICTION = 0.7;
	private static double length=1000;
	private static Water water =Water.SWEET;
	private static Surface surface =Surface.FLAT;
	private static Body body =Body.LAKE;
	
	public NavalArena(double length, int maxRacers) {
		super(length,maxRacers,FRICTION);
	}
	public NavalArena() {
		super(length,MAX_RACERS,FRICTION);
	}
	
	/**
	 * @return the water
	 */
	public static Water getWater() {
		return water;
	}
	/**
	 * @param water the water to set
	 */
	public static void setWater(Water water) {
		NavalArena.water = water;
	}
	/**
	 * @return the surface
	 */
	public static Surface getSurface() {
		return surface;
	}
	/**
	 * @param surface the surface to set
	 */
	public static void setSurface(Surface surface) {
		NavalArena.surface = surface;
	}
	/**
	 * @return the body
	 */
	public static Body getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public static void setBody(Body body) {
		NavalArena.body = body;
	}

}
