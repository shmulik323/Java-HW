package game.arenas;

import java.util.ArrayList;

import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Car;
import game.racers.Racer;
import utilities.Point;

public abstract class Arena {
	private static double length;
	private final static int MIN_Y_GAP=10;
	private static double FRICTION;
	private int MAX_RACERS ;
	private ArrayList<Object> activeRacers=new ArrayList<Object>();
	private ArrayList<Object> completedRacers = new ArrayList<Object>();
	private ArrayList<Object> finished= new ArrayList<Object>();
	
	
	public Arena(double length2, int maxRacers, double friction){
		this.setMAX_RACERS(maxRacers);
		this.setLength(length2);
		this.setFRICTION(friction);	
	}
	
	public void addRacer(Racer newRacer) throws RacerLimitException, RacerTypeException {

	}

	public void initRace() {
		for (Object racer : this.activeRacers)  {
			((Racer) racer).initRace(((Racer) racer).getArena(), new Point(0,activeRacers.indexOf(racer)*MIN_Y_GAP),  new Point(length,activeRacers.indexOf(racer)*MIN_Y_GAP));
			}
		
	}

	public boolean hasActiveRacers() {
		if(this.activeRacers.size()>0) {
			return true;
		}
		return false;
	}

	public void playTurn() {
		
		
	}
	public int crossFinishLine(Racer racer) {
		this.finished.add(racer);
		return this.finished.size();
	}

	public void showResults() {
		for(Object racer:this.finished) {
			((Racer) racer).toString();
		}
		
	}

	/**
	 * @return the fRICTION
	 */
	public static double getFRICTION() {
		return FRICTION;
	}

	/**
	 * @return the length
	 */
	public static double getLength() {
		return length;
	}

	/**
	 * @param length2 the length to set
	 */
	public void setLength(double length2) {
		this.length = length2;
	}

	/**
	 * @return the minYGap
	 */
	public static int getMinYGap() {
		return MIN_Y_GAP;
	}


	/**
	 * @return the activeRacers
	 */
	public ArrayList<Object> getActiveRacers() {
		return activeRacers;
	}

	/**
	 * @param activeRacers the activeRacers to set
	 */
	public void setActiveRacers(ArrayList<Object> activeRacers) {
		this.activeRacers = activeRacers;
	}

	/**
	 * @return the completedRacers
	 */
	public ArrayList<Object> getCompletedRacers() {
		return completedRacers;
	}

	/**
	 * @param completedRacers the completedRacers to set
	 */
	public void setCompletedRacers(ArrayList<Object> completedRacers) {
		this.completedRacers = completedRacers;
	}

	/**
	 * @return the mAX_RACERS
	 */
	public int getMAX_RACERS() {
		return MAX_RACERS;
	}

	/**
	 * @param mAX_RACERS the mAX_RACERS to set
	 */
	public void setMAX_RACERS(int mAX_RACERS) {
		MAX_RACERS = mAX_RACERS;
	}

	/**
	 * @param fRICTION the fRICTION to set
	 */
	public void setFRICTION(double fRICTION) {
		FRICTION = fRICTION;
	}

}
