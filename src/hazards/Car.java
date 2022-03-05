package hazards;

import java.util.Random;

import engine.Sprite;
import gameObjects.Warning;

public class Car extends Hazard {

	
	int delay = 0;
	
	Warning warn;
	
	public Car () {
		this.setX(0);
	}
	
	public Car (boolean flip, boolean sprite) {
		this.setX(0);
		this.setY(160);
		if (sprite) {
			this.setSprite(new Sprite("resources/sprites/policeCar.txt"));
			this.getAnimationHandler().setFrameTime(100);
		} else {
			this.setSprite(new Sprite("resources/sprites/regularCar.txt"));
		}
		this.getAnimationHandler().setFlipHorizontal(true);
		if (flip) {
			this.setX(245);
			this.getAnimationHandler().setFlipHorizontal(false);
		}
	}
	
	@Override
	public void frameEvent () {
		if (warn.isDone()) {
			if (delay == 0) {
				if (!this.getAnimationHandler().flipHorizontal()) {
					this.setX(this.getX() - 20);
					if (this.getX() <  0) {
						forget();
					}
				} else {
					this.setX(this.getX() + 20);
					if (this.getX() >  245) {
						forget();
					}
				}
			} else {
				delay = delay - 1;
			}
		}
	}
	
	@Override
	public void draw () {
		if (delay == 0 && warn.isDone()) {
			super.draw();
		} 
	}
	
	@Override
	public void spawnHazard()
	{
		

		Random r = new Random ();
		
		boolean direction = r.nextBoolean();
		Car regularCar = new Car (direction,false);
		regularCar.declare();
		
		regularCar.warn = new Warning (100);
		if (direction) {
			regularCar.warn.declare(regularCar.getX() - 16,regularCar.getY());
		} else {
			regularCar.warn.declare(regularCar.getX(), regularCar.getY());
		}
		
		
		int amountOfMetors = r.nextInt(5) + 3;
		for (int i = 0; i < amountOfMetors;i++) {
			Car c = new Car (direction, true);
			c.declare();
			c.setY(160 + (r.nextInt(30) - 15));
			c.delay = r.nextInt(100) + 100;
			c.warn = new Warning (0);
		
		}
	}
	
	
}
