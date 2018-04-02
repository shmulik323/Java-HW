package game.arenas;

import java.util.ArrayList;

import game.racers.Car;
import game.racers.Horse;
import utilities.Point;
import utilities.EnumContainer.Surface;
import utilities.EnumContainer.Coverage;
public class LandArena extends Arena {
	
	public static final int MAX_RACERS = 8;
	private static final double FRICTION = 0.5;
	private static Surface surface=Surface.FLAT;
	private static Coverage coverage=Coverage.GRASS;
	private static double length=1250;
	
	public LandArena(double length,int maxRacers) {
		super(length,maxRacers,FRICTION);
		
	}
	public LandArena(){
		super(getLength(),MAX_RACERS,FRICTION);
		
	}

	/**
	 * @param surface the surface to set
	 */
	public static void setSurface(Surface surface) {
		LandArena.surface = surface;
	}
	/**
	 * @param coverage the coverage to set
	 */
	public static void setCoverage(Coverage coverage) {
		LandArena.coverage = coverage;
	}
	/**
	 * @return the length
	 */
	public static double getLength() {
		return length;
	}
	/**
	 * @param length the length to set
	 */
	public void setLength(double length) {
		LandArena.length = length;
	}
	
}