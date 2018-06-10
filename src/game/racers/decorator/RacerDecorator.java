package game.racers.decorator;

import game.racers.IRacer;

public abstract class RacerDecorator extends IRacer {
	IRacer decoratedRacer;

	public RacerDecorator(IRacer racerToDecorat) {
		this.decoratedRacer=racerToDecorat;
	}
	@Override
	public void addAttribute(String name, Object obj) {
		this.decoratedRacer.addAttribute(name, obj);
	}
	@Override
	public void introduce() {
		this.decoratedRacer.introduce();
	}
}
