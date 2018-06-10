/**
 * 
 */
package game.racers.decorator;

import game.racers.Racer;

/**
 * @author shmul
 *
 */
public class CloneFactory{

    public Racer getRacer(Racer racer){
        Racer clone = racer.clone();
		return clone;
    }


}
