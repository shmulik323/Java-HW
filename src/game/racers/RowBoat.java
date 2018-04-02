package game.racers;

import game.arenas.NavalArena;
import utilities.Point;
/**
 * @author shmuel moha 204568323
 * @author alexs vizman 314342064
 *
 */
public class RowBoat {

	private String name;
	private double maxSpeed, currentSpeed;
	private double acceleration;
	private Point currentLocation =new Point(0,0);
	private Point finish;
	private NavalArena arena;
	private int place;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public RowBoat(String name, double maxSpeed, double acceleration) {
		this.setAcceleration(acceleration);
		this.setMaxSpeed(maxSpeed);
		this.setName(name);
	}
	
	public RowBoat(String name) {
		this.setName(name);
		this.setAcceleration(10);
		this.setMaxSpeed(75);
	}

	/**
	 * initialization of the race, arena type starting location and finish location
	 * @param arena
	 * @param start
	 * @param finish
	 */
	public void initRace(NavalArena arena, Point start, Point finish) {
		this.setArena(arena);
		this.setCurrentLocation(start.getX());
		this.setFinish(finish);
	}
	/**
	 * moves the racer and returns the current location
	 * @param friction
	 * @return point object, current location
	 */
	public Point move(double friction) {
		if((this.getMaxSpeed()-this.getCurrentSpeed() >0)){
			this.setCurrentSpeed(this.getCurrentSpeed()+(this.getAcceleration()*friction));
			this.setCurrentLocation(this.getCurrentLocation().getX()+this.getCurrentSpeed());
			return this.getCurrentLocation();
		}
		this.setCurrentSpeed(maxSpeed);
		this.setCurrentLocation(this.getCurrentLocation().getX()+this.getCurrentSpeed());
		return this.getCurrentLocation();
	}
	/**
	 * @return full String "RacerType" "place" "name" and "(MaxSpeed,Acceleration)"
	 */
	public String toString() {
		return "RowBoat"+" "+"#"+this.place+" "+this.name+" "+"("+this.getMaxSpeed()+", "+this.getAcceleration()+")";
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
	 * @return the currentLocation
	 */
	public Point getCurrentLocation() {
		return currentLocation;
	}
	/**
	 * @param d the currentLocation to set
	 */
	public void setCurrentLocation(double d) {
		this.currentLocation.setX(d);
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
	 * @return the arena
	 */
	public NavalArena getArena() {
		return arena;
	}
	/**
	 * @param arena the arena to set
	 */
	public void setArena(NavalArena arena) {
		this.arena = arena;
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
	 * @return the place
	 */
	public int getPlace() {
		return place;
	}
	/**
	 * @param place the place to set
	 */
	public void setPlace(int place) {
		this.place = place;
	}


}
