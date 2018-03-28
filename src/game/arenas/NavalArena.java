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
		if(this.rowboats.size()+this.speedboats.size()< MAX_RACERS)
			return speedboats.add(racer);
		return false;
	}
	public boolean addRacer(RowBoat racer) {
		if(this.rowboats.size()+this.speedboats.size()< MAX_RACERS)
			return rowboats.add(racer);
		return false;
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
		boolean flag=false;
		if(this.speedboats.size()!=0) {
			flag = true;
		}
		if(this.rowboats.size()!=0) {
			flag = true;
		}
		return flag;
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
		if(!rowboats.isEmpty()) {
			for(RowBoat x:rowboats) {
				if(!(x.getFinish().getX()-x.move(FRICTION).getX()>0)) {
					x.setPlace(this.crossFinishLine(x));
					this.rowboats.remove(x);
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
	public int crossFinishLine(RowBoat rowBoat) {
		if(rowBoat instanceof RowBoat) {
			this.finishd.add(rowBoat);
			return this.finishd.indexOf(rowBoat);
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

