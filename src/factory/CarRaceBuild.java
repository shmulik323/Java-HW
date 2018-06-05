package factory;

import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.land.Car;

public class CarRaceBuild implements CarRaceBuildPlan {

	@Override
	public Arena initRace(int numOfRacers, String arenaType) {
		ArenaFactory getTheArena=new ArenaFactory();
		Arena arena=getTheArena.getArena(arenaType);
		arena.setMAX_RACERS(numOfRacers);
		int i;
		for(i=0;i<numOfRacers;i++) {
			try {
				arena.addRacer(new Car());
			} catch (RacerLimitException | RacerTypeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return arena;

	}

}
