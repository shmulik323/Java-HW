package game.racers;

import game.racers.decorator.state.RacerState;

public class RacerDisabled implements RacerState {
	IRacer racer;
	public RacerDisabled(IRacer iracer) {
		racer =iracer;
	}

	@Override
	public void isFinished() {
		

	}

	@Override
	public void isBrokeDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void isMoving() {
		// TODO Auto-generated method stub

	}

	@Override
	public void isDisabled() {
		((Racer)racer).getArena().getDisabledRacers().add((Racer) racer);
		((Racer)racer).getArena().getActiveRacers().remove((Racer) racer);

	}

	public String toString() {
		return "Disabled";
	}
}
