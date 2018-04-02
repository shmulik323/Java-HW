package game.arenas;
import java.util.ArrayList;
import game.racers.Car;
import game.racers.Horse;
import utilities.Point;


public class LandArena {
	Point start;
	Point finish;
	double FRICTION =0.5;
	int MAX_RACERS =8;
	ArrayList<Car> cars=new ArrayList<Car>();
	ArrayList<Horse> horses=new ArrayList<Horse>();
	ArrayList<Object> finishd = new ArrayList<Object>();
	public LandArena(Point start, Point finish) {
		this.start=start;
		this.finish=finish;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 
	 * @param racer, Adds a new racer type {@link Car}
	 * @return boolean value
	 */
	public boolean addRacer(Car racer) {
		if(this.cars.size()+this.horses.size()< MAX_RACERS)
			return cars.add(racer);
		return false;
	}
	/**
	 * 
	 * @param racer , Adds a new racer type {@link Horse}
	 * @return boolean value
	 */
	public boolean addRacer(Horse racer) {
		if(this.cars.size()+this.horses.size()< MAX_RACERS)
			return horses.add(racer);
		return false;
	}
	/**
	 * @param initRace, initialization of the race
	 */
	public void initRace() {
		for(Car x:cars) {
			x.initRace(this, start, finish);
		}
		for(Horse x:horses) {
			x.initRace(this, start, finish);
		}

	}
	/**
	 * @param hasActiveRacers, checks for active racers in the Arrays
	 * @return boolean value
	 */
	public boolean hasActiveRacers() {
		boolean flag = false;
		if(this.cars.size()!=0) {
			flag =true;
		}
		if(this.horses.size()!=0) {
			flag =true;
		}
		return flag;
	}
	/**
	 * @param playTurn ,calls move method in each array
	 */
	public void playTurn() {
		if(!cars.isEmpty()) {
			for(Car x:cars) {
				if(!(x.getFinish().getX()-x.move(FRICTION).getX()>0)) {
					x.setPlace(this.crossFinishLine(x));
					this.cars.remove(x);
					break;
				}
			}
		}
		if(!horses.isEmpty()) {
			for(Horse x:horses) {
				if(!(x.getFinish().getX()-x.move(FRICTION).getX()>0)) {
					x.setPlace(this.crossFinishLine(x));
					this.horses.remove(x);
					break;
				}
			}
		}
	}
	/**
	 * @param crossFinishLine ,adds the finish racers to the finished array
	 * @return returns the place of the racer
	 */
	public int crossFinishLine(Car Car) {
		if(Car instanceof Car) {
			this.finishd.add(Car);
			return this.finishd.indexOf(Car);
		}
		return -1;
	}
	/**
	 * 
	 * @param crossFinishLine,adds the finished racers to the finished array
	 * @return place of the racer
	 */
	public int crossFinishLine(Horse horse) {
		if(horse instanceof Horse) {
			this.finishd.add(horse);
			return this.finishd.indexOf(horse);
		}
		return -1;
	}
	/**
	 * @param printFinish ,prints the finished from first to the last
	 * 
	 */
	public void printFinish() {
		System.out.println("LandArena Race ended!");
		for(Object x:finishd) {
			System.out.println(x.toString());
			
		}
	}
}

