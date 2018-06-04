package factory;

import game.arenas.Arena;
import game.arenas.air.AerialArena;
import game.arenas.land.LandArena;
import game.arenas.naval.NavalArena;

public class ArenaFactory {
	
    public Arena getArena(String arenaType){
        switch (arenaType){
            case "air":
                return new AerialArena();   //creates new AerialArena
            case "land":
                return new LandArena();    //creates new LandArena
            case "naval":
                return new NavalArena();  //creates new NavalArena
            default:
                return null;
        }
    }
}
