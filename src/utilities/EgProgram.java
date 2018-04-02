package utilities;

import factory.RaceBuilder;
import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import utilities.EnumContainer.Color;

// see output at end of file

public class EgProgram {

	public static void main(String[] args) {
		Arena arena = null;
		Racer[] racers = new Racer[6];
		RaceBuilder rb = RaceBuilder.getInstance();
		Fate.setSeed(477734503);

		arena = rb.buildArena("game.arenas.air.AerialArena", 1500, 4);
		racers[0] = rb.buildRacer("game.racers.air.Airplane", "Bob", 220, 10, Color.BLUE);
		racers[1] = rb.buildRacer("game.racers.air.Airplane", "John", 175, 20, Color.BLUE);
		racers[2] = rb.buildRacer("game.racers.air.Airplane", "Frank", 180, 15, Color.BLUE);
		racers[3] = rb.buildRacer("game.racers.air.Airplane", "Matt", 230, 8, Color.BLUE);
		racers[4] = rb.buildRacer("game.racers.Car", "car", 15, 1, Color.GREEN);
		racers[5] = rb.buildRacer("game.racers.air.Airplane", "Alby", 200, 8, Color.BLUE);
		for (Racer r : racers) {

			try {
				System.out.println("Racer enterd the arena!");
				r.introduce();
				arena.addRacer(r);
			} catch (RacerLimitException e) {
				System.out.println("[Error] " + e.getMessage());
			} catch (RacerTypeException e) {
				System.out.println("[Error] " + e.getMessage());
			}

		}
		arena.initRace();
		System.out.println("Strat Race!");
		while (arena.hasActiveRacers()) {
			arena.playTurn();
		}
		System.out.println("Race Compleated!");
		arena.showResults();

	}
}

/*Racer enterd the arena!
[Airplane] name: Bob, SerialNumber: 1, maxSpeed: 220.0, acceleration: 10.0, NumOfWheels: 0.
Racer enterd the arena!
[Airplane] name: John, SerialNumber: 2, maxSpeed: 175.0, acceleration: 20.0, NumOfWheels: 0.
Racer enterd the arena!
[Airplane] name: Frank, SerialNumber: 3, maxSpeed: 180.0, acceleration: 15.0, NumOfWheels: 0.
Racer enterd the arena!
[Airplane] name: Matt, SerialNumber: 4, maxSpeed: 230.0, acceleration: 8.0, NumOfWheels: 0.
Racer enterd the arena!
[Car] name: car, SerialNumber: 5, maxSpeed: 15.0, acceleration: 1.0, null
[Error] Invalid Racer of type "Car" for Aerial arena.
Racer enterd the arena!
[Airplane] name: Alby, SerialNumber: 6, maxSpeed: 200.0, acceleration: 8.0, NumOfWheels: 0.
[Error] Arena is full! (4 active racers exist). racer #6 was not added
Strat Race!
Frank Has a new mishap! (false, 3, 0.90)
Matt Has a new mishap! (true, 3, 0.62)
John Has a new mishap! (false, 4, 0.38)
Bob Has a new mishap! (false, 2, 1.00)
Matt Has a new mishap! (false, 2, 0.62)
Race Compleated!
#0 -> name: Frank, SerialNumber: 3, maxSpeed: 180.0, acceleration: 15.0, NumOfWheels: 0.
#1 -> name: Bob, SerialNumber: 1, maxSpeed: 220.0, acceleration: 10.0, NumOfWheels: 0.
#2 -> name: John, SerialNumber: 2, maxSpeed: 175.0, acceleration: 20.0, NumOfWheels: 0.
#3 -> name: Matt, SerialNumber: 4, maxSpeed: 230.0, acceleration: 8.0, NumOfWheels: 0.*/
