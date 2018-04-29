package factory;
import game.arenas.Arena;
import game.racers.Racer;
import utilities.EnumContainer.Color;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author shmuel moha 204568323
 * @author alex weizman 314342064
 *
 */
public class RaceBuilder {
	private static RaceBuilder instance=null;//Singleton
	ClassLoader classLoder = ClassLoader.getSystemClassLoader();//getting the class loader
	Class<?> reflectClass;
	Constructor<?> reflectConstractor;
	
	
	protected RaceBuilder() {
		// Exists only to defeat instantiation
	}
	/**
	 * gets the instance of the RaceBuilder
	 * @return instance
	 */
	public static RaceBuilder getInstance() {
		if (instance == null) {
			instance = new RaceBuilder();
		}
		return instance;
	}
	/**
	 * 
	 * @param arenaType
	 * @param length
	 * @param maxRacers
	 * @return a dynamic class (a reflection)
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public Arena  buildArena(String arenaType, double length, int maxRacers) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		reflectClass = classLoder.loadClass(arenaType);//loading the class from path
		reflectConstractor = reflectClass.getConstructor(double.class, int.class);//loading the contractor 
		return(Arena) reflectConstractor.newInstance(length,maxRacers);//returning a new instance of the provided class
	}
	/**
	 * 
	 * @param racerType
	 * @param name
	 * @param maxSpeed
	 * @param acceleration
	 * @param color
	 *  @return a dynamic class (a reflection)
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public Racer buildRacer(String racerType, String name, double maxSpeed, double
			acceleration, utilities.EnumContainer.Color color) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{

		reflectClass = classLoder.loadClass(racerType);
		reflectConstractor = reflectClass.getConstructor(String.class, double.class, double.class, Color.class);
		return (Racer)reflectConstractor.newInstance(name,maxSpeed,acceleration,color);

	}
	/**
	 * 
	 * @param racerType
	 * @param name
	 * @param maxSpeed
	 * @param acceleration
	 * @param color
	 * @param numOfWheels
	 *  @return a dynamic class (a reflection)
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public Racer buildWheeledRacer(String racerType, String name, double maxSpeed, 
			double acceleration, Color color, int numOfWheels) throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		reflectClass = classLoder.loadClass(racerType);
		reflectConstractor = reflectClass.getConstructor(String.class, double.class, double.class, Color.class,int.class);
		return (Racer)reflectConstractor.newInstance(name,maxSpeed,acceleration,color,numOfWheels);

	}

}
