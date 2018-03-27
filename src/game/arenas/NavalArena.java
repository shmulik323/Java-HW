package game.arenas;
import java.util.ArrayList;
import game.racers.RowBoat;
import game.racers.SpeedBoat;
import utilities.Point;

public class NavalArena {
	Point start;
	Point finish;
	double FRICTION =0.7;
	int MAX_RACERS =5;
	ArrayList<SpeedBoat> speedboats=new ArrayList<SpeedBoat>();
	ArrayList<RowBoat> rowboats=new ArrayList<RowBoat>();
	ArrayList<Object> finishd = new ArrayList<Object>();
	public NavalArena(Point start, Point finish) {
		this.start=start;
		this.finish=finish;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


	public boolean addRacer(SpeedBoat racer) {
		return speedboats.add(racer);

	}
	public boolean addRacer(RowBoat racer) {
		return rowboats.add(racer);

	}

	public void initRace() {
		for(SpeedBoat x:speedboats) {
			x.initRace(this, start, finish);
		}
		for(RowBoat x:rowboats) {
			x.initRace(this, start, finish);
		}

	}

	public boolean hasActiveRacers() {
		if(this.speedboats.size()!=0) {
			return true;
		}
		return false;
	}

	public void playTurn() {
		if(!speedboats.isEmpty()) {
			for(SpeedBoat x:speedboats) {
				if(!(x.getFinish().getX()-x.move(FRICTION).getX()>0)) {
					x.setPlace(this.crossFinishLine(x));
					this.speedboats.remove(x);
					break;
				}
			}
		}
	}
	public int crossFinishLine(SpeedBoat SpeedBoat) {
		if(SpeedBoat instanceof SpeedBoat) {
			this.finishd.add(SpeedBoat);
			return this.finishd.indexOf(SpeedBoat);
		}
		return -1;
	}

	public void printFinish() {
		System.out.println("NavalArena Race ended!");
		for(Object x:finishd) {
			System.out.println(x.toString());
			
		}
	}
}

