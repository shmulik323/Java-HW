package game.racers.decorator.state;

public interface RacerState {
 void isFinished();
 void isBrokeDown();
 void isMoving();
 void isDisabled();
 String toString();
}
