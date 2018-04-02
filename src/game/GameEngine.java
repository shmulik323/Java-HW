package game;


import game.arenas.AerialArena;
import game.arenas.LandArena;
import game.arenas.NavalArena;
import game.racers.Airplane;
import game.racers.Car;
import game.racers.Helicopter;
import game.racers.Horse;
import game.racers.RowBoat;
import game.racers.SpeedBoat;
import utilities.ArenaType;

public class GameEngine {

	private static GameEngine instance = null;
	private AerialArena airArena ;
	private LandArena landArena ;
	private NavalArena navalArena ;
	private ArenaType activeArena;

	protected GameEngine() {
		// Exists only to defeat instantiation.
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**
	 * gets the instance of the GameEngine
	 * @return instance
	 */
	public static GameEngine getInstance() {
		if (instance == null) {
			instance = new GameEngine();
		}
		return instance;
	}
	/**
	 * Sets the needed arena depending on the given parameter
	 * @param arena
	 * @return boolean value
	 */
	public boolean setArena(Object arena) {
		if(arena instanceof AerialArena) {
			this.setAirArena((AerialArena)arena);
			activeArena = ArenaType.AERIALARENA;
			this.setActiveArena(activeArena);
			return true;
		}
		if(arena instanceof LandArena) {
			this.setLandArena((LandArena)arena);
			activeArena = ArenaType.LANDARENA;
			this.setActiveArena(activeArena);
			return true;
		}
		if(arena instanceof NavalArena) {
			this.setNavalArena((NavalArena)arena);
			activeArena = ArenaType.NEVALARENA;
			this.setActiveArena(activeArena);
			return true;
		}
		return false;


	}
	/**
	 * adds new racer type to the race into the matching arena
	 * @param racer
	 * @return boolean value
	 */
	public boolean addRacer(Object racer) {
		if(this.getActiveArena() == ArenaType.AERIALARENA) {
		if(racer instanceof Airplane) {
			return this.getAirArena().addRacer((Airplane) racer);
		
		}
		if(racer instanceof Helicopter) {
			return this.getAirArena().addRacer((Helicopter) racer);

		}
		return false;
		}
		if(this.getActiveArena() == ArenaType.LANDARENA) {
		if(racer instanceof Car) {
			return this.getLandArena().addRacer((Car) racer);
		
		}
		if(racer instanceof Horse) {
			return this.getLandArena().addRacer((Horse) racer);
			
		}
		return false;
		}
		if(this.getActiveArena() == ArenaType.NEVALARENA) {
		if(racer instanceof SpeedBoat) {
			return this.getNavalArena().addRacer((SpeedBoat) racer);
			 
		}
		if(racer instanceof RowBoat) {
			return this.getNavalArena().addRacer((RowBoat) racer);
		}
		return false;
		}
		return false;
	}
	/**
	 * Initialization of the race in the matching arena we chose
	 */
	public void initRace() {
		if(getActiveArena() == ArenaType.AERIALARENA) {
			airArena.initRace();
		}
		if(getActiveArena() == ArenaType.LANDARENA) {
			landArena.initRace();
		}
		if(getActiveArena() == ArenaType.NEVALARENA) {
			navalArena.initRace();
		}

	}
	/**
	 * starts the race in the matching arena type
	 * plays the turns while they are active racers
	 * prints the finished racers
	 */
	public void startRace() {
		if(getActiveArena() == ArenaType.AERIALARENA) {
			while(getAirArena().hasActiveRacers()) {
				airArena.playTurn();
			}
			airArena.printFinish();

		}
		if(getActiveArena() == ArenaType.LANDARENA) {
			while(getLandArena().hasActiveRacers()) {
				landArena.playTurn();
			}
			landArena.printFinish();

		}
		if(getActiveArena() == ArenaType.NEVALARENA) {
			while(getNavalArena().hasActiveRacers()) {
				navalArena.playTurn();
			}
			navalArena.printFinish();

		}
	}

	/**
	 * @return the activeArena
	 */
	public ArenaType getActiveArena() {
		return activeArena;
	}

	/**
	 * @param activeArena' the activeArena to set
	 */
	public void setActiveArena(ArenaType activeArena) {
		this.activeArena = activeArena;
	}

	/**
	 * @return the airArena
	 */
	public AerialArena getAirArena() {
		return airArena;
	}

	/**
	 * @param airArena, the airArena to set
	 */
	public void setAirArena(AerialArena airArena) {
		this.airArena = airArena;
	}

	/**
	 * @return the landArena
	 */
	public LandArena getLandArena() {
		return landArena;
	}

	/**
	 * @param landArena, the landArena to set
	 */
	public void setLandArena(LandArena landArena) {
		this.landArena = landArena;
	}

	/**
	 * @return the navalArena
	 */
	public NavalArena getNavalArena() {
		return navalArena;
	}

	/**
	 * @param navalArena the navalArena to set
	 */
	public void setNavalArena(NavalArena navalArena) {
		this.navalArena = navalArena;
	}

	public String getArenaType() {
		if(this.getActiveArena()==null) {
			return null;
		}
		return this.getActiveArena().toString();
	}

}
