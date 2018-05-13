package game.arenas;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;

import utilities.Point;
/**
 * @author shmuel moha 204568323
 * @author alex weizman 314342064
 *
 */
public abstract class Arena implements Observer {
	private static double length;
	private final static int MIN_Y_GAP=80;
	private static double FRICTION;
	private int MAX_RACERS ;
	private ArrayList<Racer> activeRacers=new ArrayList<Racer>();
	private ArrayList<Racer> completedRacers = new ArrayList<Racer>();
	private ArrayList<Racer> brokenRacers=new ArrayList<Racer>();
	private ArrayList<Racer> disabledRacers=new ArrayList<Racer>();


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
		int y = 0;
		for (Racer racer : this.activeRacers) {
			Point s = new Point(0, y);
			Point f = new Point(this.length, y);
			racer.initRace(this, s, f);
			y += Arena.MIN_Y_GAP;
			racer.addObserver(this);
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

	public void startRace() {
		ExecutorService ex= Executors.newFixedThreadPool(getMAX_RACERS());
		for(Racer racer:this.activeRacers) {
			ex.execute(racer);
			System.out.println(this.hasActiveRacers());
			System.out.println(racer.getCurrentLocation().getX());


		}
		ex.shutdown();
	}

	/**
	 * show the results of the race
	 */
	public void showResults() {
		for(Racer racer:this.completedRacers) {
			System.out.println("#"+this.completedRacers.indexOf(racer)+" -> "+racer.describeRacer()+ racer.describeSpecific());
		}

	}
	public void update(Observable o, Object arg){
		String string = arg.toString();
		System.out.println(string);
		System.out.println(o.toString());

			switch (string) {
			case "FINISHED":
				this.completedRacers.add((Racer) o);
				this.activeRacers.remove((Racer)o);	
				System.out.println(this.activeRacers.size());
				break;
			case "BROKENDOWN":
				this.brokenRacers.add((Racer) o);
				break;
			case "DISABLED":
				this.disabledRacers.add((Racer)o);
				this.activeRacers.remove((Racer) o);
				break;
			case "REPAIRED":
				this.brokenRacers.remove((Racer) o);
				break;
			default:
				break;
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
	public void setFRICTION(double friction) {
		FRICTION = friction;
	}

	/**
	 * @return the disabledRacers
	 */
	public ArrayList<Racer> getDisabledRacers() {
		return disabledRacers;
	}

	/**
	 * @param disabledRacers the disabledRacers to set
	 */
	public void setDisabledRacers(ArrayList<Racer> disabledRacers) {
		this.disabledRacers = disabledRacers;
	}

	/**
	 * @return the brokenRacers
	 */
	public ArrayList<Racer> getBrokenRacers() {
		return brokenRacers;
	}

	/**
	 * @param brokenRacers the brokenRacers to set
	 */
	public void setBrokenRacers(ArrayList<Racer> brokenRacers) {
		this.brokenRacers = brokenRacers;
	}

}
