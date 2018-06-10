package game.racers;

import game.racers.decorator.state.RacerState;

public class RacerFinished implements RacerState {
	IRacer racer;
	public RacerFinished(IRacer iRacer) {
		racer =iRacer;
	}

	@Override
	public void isFinished() {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	public String toString() {
		return "Finished";
	}

}
