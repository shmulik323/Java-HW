package factory;

import game.arenas.Arena;

public interface CarRaceBuildPlan {

	public Arena initRace(int numOfRacers,String arenaType);
	
}
