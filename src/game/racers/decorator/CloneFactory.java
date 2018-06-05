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
        return racer.clone();
    }


}
