package game.racers;

import java.util.Observable;

import game.arenas.Arena;
import utilities.Point;
import utilities.EnumContainer.Color;
import utilities.Fate;
import utilities.Mishap;
/**
 * @author shmuel moha 204568323
 * @author alex weizman 314342064
 *
 */
public abstract class Racer extends Observable implements Runnable{
	private Arena arena;
	private String name;
	private Point currentLocation= new Point();
	private Point finish=new Point();
	private double maxSpeed;
	private double acceleration;
	private double currentSpeed;
	private double failurePropability;
	private Color color;
	protected static int SerialId=0;
	private int SerialNumber;
	private Mishap mishap ;
	public static void main(String[] args) {


	}
	public Racer(String name, double maxSpeed, double acceleration, Color color) {
		this.name=name;
		this.maxSpeed=maxSpeed;
		this.acceleration=acceleration;
		this.color=color;
		this.SerialNumber=++Racer.SerialId;
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
	public Point move(double friction) {
		if(this.mishap==null || (this.mishap.getTurnsToFix()==0 && this.mishap.isFixable())) {
			if(Fate.breakDown()) {
				this.mishap=Fate.generateMishap();
				System.out.println(this.getName()+" Has a new mishap! "+this.mishap.toString());
			}
		}
		if(this.mishap!=null) {
			if(this.mishap.getTurnsToFix()!=0) {
				currentSpeed+= this.getAcceleration()*friction*this.mishap.getReductionFactor();
				if(this.mishap.isFixable()) {
					this.mishap.nextTurn();
				}
			}
		}
		else if(this.currentSpeed<this.maxSpeed) {
			currentSpeed+=acceleration*friction;
		}
		this.currentLocation.setX(this.currentLocation.getX()+this.currentSpeed);

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

}
