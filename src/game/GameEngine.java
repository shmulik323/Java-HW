package game;


import game.arenas.AerialArena;
import game.arenas.LandArena;
import game.racers.Airplane;
import game.racers.Car;
import game.racers.Helicopter;
import game.racers.Horse;
import utilities.ArenaType;

public class GameEngine {

	private static GameEngine instance = null;
	private AerialArena airArena ;
	private LandArena landArena ;
	private ArenaType activeArena;

	protected GameEngine() {
		// Exists only to defeat instantiation.
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static GameEngine getInstance() {
		if (instance == null) {
			instance = new GameEngine();
		}
		return instance;
	}

	public boolean setArena(Object arena) {
		if(arena instanceof AerialArena) {
			this.setAirArena((AerialArena)arena);
			activeArena = ArenaType.AERIALARENA;
			this.setActiveArena(activeArena);;
		}
		if(arena instanceof LandArena) {
			this.setLandArena((LandArena)arena);
			activeArena = ArenaType.LANDARENA;
			this.setActiveArena(activeArena);;
		}
		return false;


	}

	public boolean addRacer(Object racer) {
		if(racer instanceof Airplane) {
			this.getAirArena().addRacer((Airplane) racer);
			return true;
		}
		if(racer instanceof Helicopter) {
			this.getAirArena().addRacer((Helicopter) racer);
			return true;
		}
		if(racer instanceof Car) {
			this.getLandArena().addRacer((Car) racer);
			return true;
		}
		if(racer instanceof Horse) {
			this.getLandArena().addRacer((Horse) racer);
			return true;
		}
		return false;

	}

	public void initRace() {
		if(getActiveArena() == ArenaType.AERIALARENA) {
			airArena.initRace();
		}
		if(getActiveArena() == ArenaType.LANDARENA) {
			landArena.initRace();
		}

	}

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
	}

	/**
	 * @return the activeArena
	 */
	public ArenaType getActiveArena() {
		return activeArena;
	}

	/**
	 * @param activeArena the activeArena to set
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
	 * @param airArena the airArena to set
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
	 * @param landArena the landArena to set
	 */
	public void setLandArena(LandArena landArena) {
		this.landArena = landArena;
	}

}
