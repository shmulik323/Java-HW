package game.racers.decorator;

import game.racers.IRacer;
import utilities.EnumContainer.Color;

public class ColoredRacer extends RacerDecorator {

	public ColoredRacer(IRacer racerToDecorat,Color color) {
		super(racerToDecorat);
		racerToDecorat.addAttribute("color", color);
	}
	@Override
	public void addAttribute(String name, Object obj) {
		super.addAttribute(name, obj);
	}
	@Override
	public void introduce() {
		super.introduce();
	}

}
