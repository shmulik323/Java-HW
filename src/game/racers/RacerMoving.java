package game.racers;

import game.racers.decorator.state.RacerState;

public class RacerMoving implements RacerState {
	IRacer racer;
	public RacerMoving(IRacer iracer) {
		racer=iracer;
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
		return "Moving";
	}
}
