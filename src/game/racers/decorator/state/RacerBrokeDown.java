/**
 * 
 */
package game.racers.decorator.state;

import game.racers.IRacer;

/**
 * @author shmul
 *
 */
public class RacerBrokeDown implements RacerState {
	IRacer racer;
	public RacerBrokeDown(IRacer newRacer) {
		racer= newRacer;
	}
	@Override
	public void isFinished() {
		

	}

	/* (non-Javadoc)
	 * @see game.racers.decorator.state.RacerState#isBrokeDown()
	 */
	@Override
	public void isBrokeDown() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see game.racers.decorator.state.RacerState#isMoving()
	 */
	@Override
	public void isMoving() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see game.racers.decorator.state.RacerState#isDisabled()
	 */
	@Override
	public void isDisabled() {
		// TODO Auto-generated method stub

	}

	public String toString() {
		return "BrokeDown";
	}
}
