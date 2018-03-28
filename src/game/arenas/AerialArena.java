package game.arenas;
import java.util.ArrayList;
import game.racers.Airplane;
import game.racers.Helicopter;
import utilities.Point;

public class AerialArena {
	Point start;
	Point finish;
	double FRICTION =0.4;
	int MAX_RACERS =6;
	ArrayList<Airplane> airplains=new ArrayList<Airplane>();
	ArrayList<Helicopter> helicopters=new ArrayList<Helicopter>();
	ArrayList<Object> finishd = new ArrayList<Object>();
	public AerialArena(Point start, Point finish) {
		this.start=start;
		this.finish=finish;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param addRacer ,add a new racer type {@link Airplane}
	 * @return boolean value
	 */
	public boolean addRacer(Airplane racer) {
		if(this.helicopters.size()+this.airplains.size()<=5) {
		return airplains.add(racer);
		}
		return false;
	}
	/**
	 * @param addRacer ,add a new racer type {@link Helicopter}
	 * @return boolean value
	 */
	public boolean addRacer(Helicopter racer) {
		if(this.helicopters.size()+this.airplains.size()<=5) {
			return helicopters.add(racer);
		}
		return false;
	}

	/**
	 * @param initRace ,initialization of the race
	 */
	public void initRace() {
		for(Airplane x:airplains) {
			x.initRace(this, start, finish);
		}
		for(Helicopter x:helicopters) {
			x.initRace(this, start, finish);
		}

	}
	/**
	 * @param hasActiveRacers ,checks for active racers in the Arrays
	 * @return boolean value
	 */
	public boolean hasActiveRacers() {
		boolean flag =false;
		if(this.airplains.size()!=0) {
			flag = true;
		}
		if(this.helicopters.size()!=0) {
			flag = true;
		}
		return flag;
	}
	/**
	 * @param playTurn ,calls move method in each array
	 * 
	 */
	public void playTurn() {
		if(!airplains.isEmpty()) {
			for(Airplane x:airplains) {
				if(!(x.getFinish().getX()-x.move(FRICTION).getX()>0)) {
					x.setPlace(this.crossFinishLine(x));
					this.airplains.remove(x);
					break;
				}
			}
		}
		if(!helicopters.isEmpty()) {
			for(Helicopter x:helicopters) {
				if(!(x.getFinish().getX()-x.move(FRICTION).getX()>0)) {
					x.setPlace(this.crossFinishLine(x));
					this.helicopters.remove(x);
					break;
				}
			}
		}
	}
	/**
	 * @param crossFinishLine ,adds the finish racers to the finishd array
	 * @return returns the place of the racer
	 */
	public int crossFinishLine(Airplane airplane) {
		if(airplane instanceof Airplane) {
			this.finishd.add(airplane);
			return this.finishd.indexOf(airplane);
		}
		return -1;
	}
	/**
	 * @param crossFinishLine ,adds the finish racers to the finishd array
	 * 
	 */
	public int crossFinishLine(Helicopter helicopter) {
		if(helicopter instanceof Helicopter) {
			this.finishd.add(helicopter);
			return this.finishd.indexOf(helicopter);
		}
		return -1;
	}
	/**
	 * @param printFinish ,prints the finishd from first to the last
	 * 
	 */
	public void printFinish() {
		System.out.println("Aerial Race ended!");
		for(Object x:finishd) {
			System.out.println(x.toString());

		}
	}
}

