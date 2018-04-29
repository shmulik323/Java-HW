package game.arenas;

import java.util.ArrayList;

import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import utilities.Point;
/**
 * @author shmuel moha 204568323
 * @author alex weizman 314342064
 *
 */
public abstract class Arena {
	private static double length;
	private final static int MIN_Y_GAP=10;
	private static double FRICTION;
	private int MAX_RACERS ;
	private ArrayList<Racer> activeRacers=new ArrayList<Racer>();
	private ArrayList<Racer> completedRacers = new ArrayList<Racer>();
	
	
	public Arena(double length2, int maxRacers, double friction){
		this.setMAX_RACERS(maxRacers);
		this.setLength(length2);
		this.setFRICTION(friction);	
	}
	
	public abstract void addRacer(Racer newRacer) throws RacerLimitException, RacerTypeException ;
/**
 * initializes the race
 */
	public void initRace() {
		for (Racer racer : this.activeRacers)  {
			(racer).initRace(racer.getArena(), new Point(0,activeRacers.size()*MIN_Y_GAP),  new Point(length,activeRacers.size()*MIN_Y_GAP));
			}
		
	}
/**
 * checks if race has active racers
 * @return boolean value
 */
	public boolean hasActiveRacers() {
		if(this.activeRacers.size()>0) {
			return true;
		}
		return false;
	}
/**
 * plays one turn
 */
	public void playTurn() {
		if(!(this.getActiveRacers().isEmpty())) {
			for(Object r:this.getActiveRacers()) {
				if(((Racer)r).getCurrentLocation().getX()<this.getLength()) {
					((Racer) r).move(getFRICTION());
				}
				else {
					this.crossFinishLine((Racer) r);
					this.getActiveRacers().remove(r);
					return;
				}
			}
		}
	}
/**
 * checks how many crossed the finish line
 * @param racer
 * @return number of racers that crossed finish line
 */
	public int crossFinishLine(Racer racer) {
		this.completedRacers.add(racer);
		return this.completedRacers.size();
	}
/**
 * show the results of the race
 */
	public void showResults() {
		for(Racer racer:this.completedRacers) {
			System.out.println("#"+this.completedRacers.indexOf(racer)+" -> "+racer.describeRacer()+ racer.describeSpecific());
		}
		
	}

	/**
	 * @return the FRICTION
	 */
	public static double getFRICTION() {
		return FRICTION;
	}

	/**
	 * @return the length
	 */
	public double getLength() {
		return length;
	}

	/**
	 * @param length2 the length to set
	 */
	public void setLength(double length2) {
		Arena.length = length2;
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
	public ArrayList<Racer> getActiveRacers() {
		return activeRacers;
	}

	/**
	 * @param activeRacers the activeRacers to set
	 */
	public void setActiveRacers(ArrayList<Racer> activeRacers) {
		this.activeRacers = activeRacers;
	}

	/**
	 * @return the completedRacers
	 */
	public ArrayList<Racer> getCompletedRacers() {
		return completedRacers;
	}

	/**
	 * @param completedRacers the completedRacers to set
	 */
	public void setCompletedRacers(ArrayList<Racer> completedRacers) {
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
