package game.racers;

import game.arenas.Arena;
import utilities.Point;
import utilities.EnumContainer.Color;
import utilities.Fate;
import utilities.Mishap;
public abstract class Racer {
	private Arena arena;
	private String name;
	private Point currentLocation= new Point();
	private Point finish=new Point();
	private double maxSpeed;
	private double acceleration;
	private double currentSpeed;
	private double failurePropability;
	private Color color;
	private static int SerialNumber=1;
	private Mishap mishap ;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public Racer(String name, double maxSpeed, double acceleration, Color color) {
		this.setName(name);
		this.setMaxSpeed(maxSpeed);
		this.setAcceleration(acceleration);
		this.setColor(color);
	}
	public void initRace(Arena arena, Point start, Point finish) {
		this.setArena(arena);
		this.setCurrentLocation(start);
		this.setFinish(finish);
	}
	public Point move(double friction) {
		if(this.getMishap()==null) {
			this.setMishap(Fate.generateMishap());
			System.out.println(this.getName()+" Has a new mishap! "+this.getMishap().toString());
		}
		if(this.getMishap().getTurnsToFix()!=0) {
			currentSpeed+= this.getAcceleration()*friction*this.getMishap().getReductionFactor();
		}
		else if(this.currentSpeed<this.maxSpeed) {
			currentSpeed+=acceleration*friction;
		}
			this.getCurrentLocation().setX(currentLocation.getX()+currentSpeed);
		
		// has a chance for failure ( see section 4.2 )
		return this.getCurrentLocation();
	}

	public abstract String describeSpecific();
	public abstract String describeRacer();
	public abstract void introduce();
	public abstract String className();
	/**
	 * @return the arena
	 */
	public Arena getArena() {
		return arena;
	}

	/**
	 * @param arena the arena to set
	 */
	public void setArena(Arena arena) {
		this.arena = arena;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the currentLocation
	 */
	public Point getCurrentLocation() {
		return currentLocation;
	}

	/**
	 * @param currentLocation the currentLocation to set
	 */
	public void setCurrentLocation(Point currentLocation) {
		this.currentLocation = currentLocation;
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
	 * @return the maxSpeed
	 */
	public double getMaxSpeed() {
		return maxSpeed;
	}

	/**
	 * @param maxSpeed the maxSpeed to set
	 */
	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	/**
	 * @return the failurePropability
	 */
	public double getFailurePropability() {
		return failurePropability;
	}

	/**
	 * @param failurePropability the failurePropability to set
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
	public void setCurrentSpeed(double currentSpeed) {
		this.currentSpeed = currentSpeed;
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
	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
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
	public static int getSerialNumber() {
		return SerialNumber;
	}
	/**
	 * @param serialNumber the serialNumber to set
	 */
	public static void setSerialNumber(int serialNumber) {
		SerialNumber = serialNumber;
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
