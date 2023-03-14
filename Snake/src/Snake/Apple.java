package Snake;

import java.util.List;
import hdm.shared.Toolbox;
import processing.core.PApplet;

public class Apple implements GameObject {

	int xPos;
	int yPos;
	int length;
	boolean dead;

	Apple(SnakeGame game, PApplet p) {
		newApple(game,p);
	}

	@Override
	public void draw(SnakeGame game, List<GameObject> object, PApplet p) {

		p.fill(255, 0, 0);
		p.rect(xPos, yPos, 30, 30);
	}

	@Override
	public boolean isDead(SnakeGame game, List<GameObject> object, PApplet p) {
		newApple(game,p);	
		return false;
	}

	public void newApple(SnakeGame game, PApplet p) {
		xPos = Toolbox.randomInt(30, 660);
		yPos = Toolbox.randomInt(90, 660);

		length = 1;

		if ((xPos % 30) < 15) {
			xPos -= (xPos % 30);  
		} else {
			int differenz = 30 - xPos % 30;
			xPos += differenz;
		}
		if ((yPos % 30) < 15) {
			yPos -= (yPos % 30);
		} else {
			int differenz = 30 - yPos % 30;
			yPos += differenz;
		}
	}
	
	public int getXPos() {
		return xPos;
	}
	public int getYPos() {
		return yPos;
	}
	
	@Override
	public void move(SnakeGame game, List<GameObject> object, PApplet p) {
		// TODO Auto-generated method stub
	}

	@Override
	public void collide(SnakeGame game, List<GameObject> object, PApplet p) {
		// TODO Auto-generated method stub
	}

	@Override
	public List getList(int number) {
		// TODO Auto-generated method stub
		return null;
	}
}
