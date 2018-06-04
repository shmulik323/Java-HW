/**
 * 
 */
package game.racers.decorator;

import game.racers.Racer;

/**
 * @author shmul
 *
 */
public interface RacerClone extends Cloneable {

    public Racer clone();

    // for testing purposes , we want to know the hashcode
    public int getHashCode();
}
