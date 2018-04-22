package factory;
import game.arenas.Arena;
import game.racers.Racer;
import utilities.EnumContainer.Color;
import game.arenas.exceptions.RacerTypeException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RaceBuilder {
	private static RaceBuilder instance=null;
	Arena reflectArena;
	Method method;
	ClassLoader cl = ClassLoader.getSystemClassLoader();
	Class<?> c;
	Constructor<?> con;
	 Racer newRacer;
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
	public Arena  buildArena(String arenaType, double length, int maxRacers) {
	
		 try {
			 c = cl.loadClass(arenaType);
			 con = c.getConstructor(double.class, int.class); 
			 reflectArena =(Arena) con.newInstance(length,maxRacers);
				return reflectArena;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | ClassNotFoundException | NoSuchMethodException | SecurityException e) {
			e.getStackTrace();
		}
		 return reflectArena;
	}
	public Racer buildRacer(String racerType, String name, double maxSpeed, double
			acceleration, utilities.EnumContainer.Color color){

	
		try {
				c = cl.loadClass(racerType);
				 con = c.getConstructor(String.class, double.class, double.class, Color.class);
				 this.newRacer=(Racer)con.newInstance(name,maxSpeed,acceleration,color);		 		 
			return newRacer;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | ClassNotFoundException | NoSuchMethodException | SecurityException e) {
			return newRacer;
		}
		 
		
	}
	public Racer buildWheeledRacer(String racerType, String name, double maxSpeed, 
			double acceleration, Color color, int numOfWheels) {

		 try {
				c = cl.loadClass(racerType);
				 con = c.getConstructor(String.class, double.class, double.class, Color.class,int.class);
			return (Racer)con.newInstance(name,maxSpeed,acceleration,color,numOfWheels);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | ClassNotFoundException | NoSuchMethodException | SecurityException e) {
			
		
		}
		return null;
	}
	
}
