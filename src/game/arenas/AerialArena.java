package game.arenas;
import java.util.ArrayList;
import game.racers.Airplane;
import game.racers.Helicopter;
import utilities.Point;
/**
 * @author shmuel moha 204568323
 * @author alexs vizman 314342064
 *
 */
public class AerialArena {
	private Point start;
	private Point finish;
	private double FRICTION =0.4;
	private int MAX_RACERS =6;
	private ArrayList<Airplane> airplains=new ArrayList<Airplane>();
	private ArrayList<Helicopter> helicopters=new ArrayList<Helicopter>();
	private ArrayList<Object> finishd = new ArrayList<Object>();
	
	public AerialArena(Point start, Point finish) {
		this.setStart(start);
		this.setFinish(finish);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param addRacer ,add a new racer type {@link Airplane}
	 * @return boolean value
	 */
	public boolean addRacer(Airplane racer) {
		if(this.getHelicopters().size()+this.getAirplains().size()< getMAX_RACERS()) {
		return getAirplains().add(racer);
		}
		return false;
	}
	/**
	 * @param addRacer ,add a new racer type {@link Helicopter}
	 * @return boolean value
	 */
	public boolean addRacer(Helicopter racer) {
		if(this.getHelicopters().size()+this.getAirplains().size()< getMAX_RACERS()) {
			return getHelicopters().add(racer);
		}
		return false;
	}

	/**
	 * @param initRace ,initialization of the race
	 */
	public void initRace() {
		for(Airplane x:getAirplains()) {
			x.initRace(this, getStart(), getFinish());
		}
		for(Helicopter x:getHelicopters()) {
			x.initRace(this, getStart(), getFinish());
		}

	}
	/**
	 * @param hasActiveRacers ,checks for active racers in the Arrays
	 * @return boolean value
	 */
	public boolean hasActiveRacers() {
		boolean flag =false;
		if(this.getAirplains().size()!=0) {
			flag = true;
		}
		if(this.getHelicopters().size()!=0) {
			flag = true;
		}
		return flag;
	}
	/**
	 * @param playTurn ,calls move method in each array
	 * 
	 */
	public void playTurn() {
		if(!getAirplains().isEmpty()) {
			for(Airplane x:getAirplains()) {
				if(!(x.getFinish().getX()-x.move(getFRICTION()).getX()>0)) {
					x.setPlace(this.crossFinishLine(x));
					this.getAirplains().remove(x);
					break;
				}
			}
		}
		if(!getHelicopters().isEmpty()) {
			for(Helicopter x:getHelicopters()) {
				if(!(x.getFinish().getX()-x.move(getFRICTION()).getX()>0)) {
					x.setPlace(this.crossFinishLine(x));
					this.getHelicopters().remove(x);
					break;
				}
			}
		}
	}
	/**
	 * @param crossFinishLine ,adds the finish racers to the finished array
	 * @return returns the place of the racer
	 */
	public int crossFinishLine(Airplane airplane) {
		if(airplane instanceof Airplane) {
			this.getFinishd().add(airplane);
			return this.getFinishd().indexOf(airplane);
		}
		return -1;
	}
	/**
	 * @param crossFinishLine ,adds the finish racers to the finished array
	 * 
	 */
	public int crossFinishLine(Helicopter helicopter) {
		if(helicopter instanceof Helicopter) {
			this.getFinishd().add(helicopter);
			return this.getFinishd().indexOf(helicopter);
		}
		return -1;
	}
	/**
	 * @param printFinish ,prints the finished from first to the last
	 * 
	 */
	public void printFinish() {
		System.out.println("Aerial Race ended!");
		for(Object x:getFinishd()) {
			System.out.println(x.toString());

		}
	}

	/**
	 * @return the start
	 */
	public Point getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(Point start) {
		this.start = start;
	}

	/**
	 * @return the finish
	 */
	public Point getFinish() {
		return finish;
	}

	/**
	 * @param finish the finish to set
	 */
	public void setFinish(Point finish) {
		this.finish = finish;
	}

	/**
	 * @return the fRICTION
	 */
	public double getFRICTION() {
		return FRICTION;
	}

	/**
	 * @param fRICTION the fRICTION to set
	 */
	public void setFRICTION(double fRICTION) {
		FRICTION = fRICTION;
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
	 * @return the airplains
	 */
	public ArrayList<Airplane> getAirplains() {
		return airplains;
	}

	/**
	 * @param airplains the airplains to set
	 */
	public void setAirplains(ArrayList<Airplane> airplains) {
		this.airplains = airplains;
	}

	/**
	 * @return the helicopters
	 */
	public ArrayList<Helicopter> getHelicopters() {
		return helicopters;
	}

	/**
	 * @param helicopters the helicopters to set
	 */
	public void setHelicopters(ArrayList<Helicopter> helicopters) {
		this.helicopters = helicopters;
	}

	/**
	 * @return the finishd
	 */
	public ArrayList<Object> getFinishd() {
		return finishd;
	}

	/**
	 * @param finishd the finishd to set
	 */
	public void setFinishd(ArrayList<Object> finishd) {
		this.finishd = finishd;
	}

}

