package game.racers;

import java.util.Observable;

import game.arenas.Arena;
import utilities.EnumContainer.Color;
import utilities.EnumContainer.RacerEvent;
import utilities.Fate;
import utilities.Mishap;
import utilities.Point;
/**
 * @author shmuel moha 204568323
 * @author alex weizman 314342064
 *
 */
public abstract class Racer extends IRacer implements Runnable{
	private Arena arena;
	private String name;
	private Point currentLocation= new Point();
	private Point finish=new Point();
	private double maxSpeed;
	private double acceleration;
	private double currentSpeed;
	private double failurePropability =0.05;
	private Color color;
	protected static int SerialId=0;
	private int SerialNumber;
	private Mishap mishap ;
	

	public Racer(String name, double maxSpeed, double acceleration, Color color) {
		this.name=name;
		this.maxSpeed=maxSpeed;
		this.acceleration=acceleration;
		this.color=color;
		this.SerialNumber=++Racer.SerialId;
	}
	@Override
	public void addAttribute(String name, Object obj) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * initializes the race
	 * @param arena
	 * @param start
	 * @param finish
	 */
	public void initRace(Arena arena, Point start, Point finish) {
		this.arena=arena;
		this.currentLocation=start;
		this.finish=finish;
	}
	/**
	 * function that moves the racer
	 * @param friction
	 * @return Point object, the new location
	 */
	public synchronized Point move(double friction) {
		double reductionFactor = 1;
		if(!this.arena.getDisabledRacers().contains(this)) {
		if (!(this.hasMishap()) && Fate.breakDown(failurePropability)) {
			this.mishap=Fate.generateMishap();
			this.setChanged();
			this.notifyObservers(RacerEvent.BROKENDOWN);
		}

		if (this.hasMishap()) {
			reductionFactor = mishap.getReductionFactor();
			if(this.mishap.isFixable()) {
				this.mishap.nextTurn();
			}
			else {
				this.setChanged();
				this.notifyObservers(RacerEvent.DISABLED);
			}
			if(this.mishap.getTurnsToFix()==0) {
				this.setChanged();
				this.notifyObservers(RacerEvent.REPAIRED);
			}
		}

		if (this.currentSpeed < this.maxSpeed) {
			this.currentSpeed += this.acceleration * friction * reductionFactor;
		}
		if (this.currentSpeed > this.maxSpeed) {
			this.currentSpeed = this.maxSpeed;
		}
		
		this.currentLocation.setX(this.currentLocation.getX()+this.currentSpeed);
		}
		if(this.currentLocation.getX()>=this.arena.getLength()) {
			this.currentLocation.setX(arena.getLength());
			this.setChanged();
			this.notifyObservers(RacerEvent.FINISHED);
			
		}
		return this.currentLocation;
	}

	public String describeRacer() {

		return "name:"+this.getName()+","+" SerialNumber: "+this.SerialNumber+" maxSpeed: "+this.getMaxSpeed()+","+
				" acceleration: "+this.acceleration+ ","+"Color: "+this.color+" ";
	}
	public abstract String describeSpecific();//abstract method 

	public void introduce() {
		System.out.println("["+this.className()+"]"+this.describeRacer()+this.describeSpecific());

	}
	public String className() {
		return this.getClass().getSimpleName();
	}
	/**
	 * @return the arena
	 */
	public Arena getArena() {
		return arena;
	}

	/**
	 * @param arena the arena to set
	 * @return 
	 */
	public boolean setArena(Arena arena) {
		if(arena!= null) {
			this.arena = arena;
			return true;
		}
		return false;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 * @return 
	 */
	public boolean setName(String name) {
		if(name.length()!=0) {
			this.name = name;
			return true;
		}
		return false;
	}

	/**
	 * @return the currentLocation
	 */
	public Point getCurrentLocation() {
		return currentLocation;
	}

	/**
	 * @param currentLocation the currentLocation to set
	 * @return 
	 */
	public boolean setCurrentLocation(Point currentLocation) {
		if(currentLocation!=null) {
			this.currentLocation = currentLocation;
			return true;
		}
		return false;
	}

	/**
	 * @return the finish
	 */
	public Point getFinish() {
		return finish;
	}

	/**
	 * @param finish the finish to set
	 * @return 
	 */
	public boolean setFinish(Point finish) {
		if(finish!=null) {
			this.finish = finish;
			return true;
		}
		return false;
	}

	/**
	 * @return the maxSpeed
	 */
	public double getMaxSpeed() {
		return maxSpeed;
	}

	/**
	 * @param maxSpeed the maxSpeed to set
	 * @return 
	 */
	public boolean setMaxSpeed(double maxSpeed) {
		if(maxSpeed>0) {
			this.maxSpeed = maxSpeed;
			return true;
		}
		return false;
	}

	/**
	 * @return the failurePropability
	 */
	public double getFailurePropability() {
		return failurePropability;
	}

	/**
	 * @param failurePropability the failurePropability to set
	 * @since Home Work 3
	 */
	public void setFailurePropability(double failurePropability) {
		this.failurePropability = failurePropability;
	}

	/**
	 * @return the currentSpeed
	 */
	public double getCurrentSpeed() {
		return currentSpeed;
	}

	/**
	 * @param currentSpeed the currentSpeed to set
	 */
	public boolean setCurrentSpeed(double currentSpeed) {
		if(currentSpeed>=0) {
			this.currentSpeed = currentSpeed;
			return true;
		}
		return false;
	}

	/**
	 * @return the acceleration
	 */
	public double getAcceleration() {
		return acceleration;
	}

	/**
	 * @param acceleration the acceleration to set
	 */
	public boolean setAcceleration(double acceleration) {
		if(acceleration>=0) {
			this.acceleration = acceleration;
			return true;
		}
		return false;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	/**
	 * @return the serialNumber
	 */
	public static int getSerialId() {
		return SerialId;
	}
	/**
	 * @param serialNumber the serialNumber to set
	 * @return true if the SerialNumber is valid and false if not
	 */
	public static boolean setSerialId(int serialNumber) {
		if(serialNumber>=0) {
			SerialId = serialNumber;
			return true;
		}
		return false;
	}
	/**
	 * @return the mishap
	 */
	public Mishap getMishap() {
		return mishap;
	}
	/**
	 * @param mishap the mishap to set
	 */
	public void setMishap(Mishap mishap) {
		this.mishap = mishap;
	}

	public boolean threadIsStoped() {
		if(this.arena.getCompletedRacers().contains(this)) {return true;}
		return false;
	}
	@Override
	public synchronized void run() {
		this.getArena();
		while(!arena.getCompletedRacers().contains(this) || !arena.getDisabledRacers().contains(this)) {
		this.move(Arena.getFRICTION());
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().notify();
		}
		}

	}
	private boolean hasMishap() {
		if (this.mishap != null && this.mishap.getTurnsToFix() == 0)
			this.mishap = null;
		return this.mishap != null;
	}


}
