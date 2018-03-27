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


	public boolean addRacer(Car racer) {
		return cars.add(racer);

	}
	public boolean addRacer(Horse racer) {
		return horses.add(racer);

	}

	public void initRace() {
		for(Car x:cars) {
			x.initRace(this, start, finish);
		}
		for(Horse x:horses) {
			x.initRace(this, start, finish);
		}

	}

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
	public int crossFinishLine(Car Car) {
		if(Car instanceof Car) {
			this.finishd.add(Car);
			return this.finishd.indexOf(Car);
		}
		return -1;
	}
	public int crossFinishLine(Horse horse) {
		if(horse instanceof Horse) {
			this.finishd.add(horse);
			return this.finishd.indexOf(horse);
		}
		return -1;
	}

	public void printFinish() {
		System.out.println("LandArena Race ended!");
		for(Object x:finishd) {
			System.out.println(x.toString());
			
		}
	}
}

