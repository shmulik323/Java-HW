package game.racers;

import game.arenas.Arena;
import utilities.Point;
import utilities.EnumContainer.Color;
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public Racer(String name, double maxSpeed, double acceleration, Color color) {
		this.name=name;
		this.maxSpeed=maxSpeed;
		this.acceleration=acceleration;
		this.color=color;
	}
	public void introduce() {

		
	}
	public void initRace(Arena arena, Point start, Point finish) {
		this.arena=arena;
		this.currentLocation=start;
		this.finish=finish;
	}
	public Point move(double friction) {
		if(this.currentSpeed<this.maxSpeed) {
			currentSpeed+=acceleration*friction;
		}
		currentLocation.setX(currentLocation.getX()+currentSpeed);
		
	// has a chance for failure ( see section 4.2 )
		return currentLocation;
		}
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

}
