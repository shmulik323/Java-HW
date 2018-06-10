/**
 * 
 */
package game.racers;

import java.util.Observable;

import game.racers.decorator.state.RacerBrokeDown;
import game.racers.decorator.state.RacerState;

/**
 * @author shmuel moha 204568323
 * @author alex weizman 314342064
 *
 */
public abstract class IRacer extends Observable  {
	RacerState Finished;
	RacerState Moving;
	RacerState BrokeDown;
	RacerState Disabled;
	RacerState racerState;
	public IRacer(){
		BrokeDown=new RacerBrokeDown(this);
		Finished=new RacerFinished(this);
		Moving=new RacerMoving(this);;
		Disabled=new RacerDisabled(this);;
		racerState=Moving;;
	}
	public abstract void addAttribute(String name,Object obj);
	public void setRacerState(RacerState newState) {
		racerState=newState;
	}
	public void racerDisable() {
		racerState.isDisabled();
	}
	public void racerBrokeDown() {
		racerState.isBrokeDown();
	}
	public void racerMoving() {
		racerState.isMoving();
	}
	public void racerFinished() {
		racerState.isFinished();
	}

}