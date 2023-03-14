package Snake;
import java.util.List;

import hdm.shared.Toolbox;
import processing.core.PApplet;

public class Snail implements GameObject{

	int xPos;
	int yPos;
	static int numberOfSnails=0;
	int yourNumber;
	boolean left;
	boolean right;
	boolean up;
	boolean down;
	int direction;
	boolean wrongDirection = false;
	
	int personalDirection[];

	Snail(SnakeGame game, PApplet p){
		newSnail(game, p);
		numberOfSnails ++;
		yourNumber = numberOfSnails;
	}

	@Override
	public void draw(SnakeGame game, List<GameObject> object, PApplet p) {
		p.fill(207, 185, 151);
		p.rect(xPos, yPos, 30, 30);
	}
	
	public void newSnail(SnakeGame game, PApplet p) {
	
		xPos = Toolbox.randomInt(90, 660);
		yPos = Toolbox.randomInt(90, 660);
	
		if ((xPos % 30) < 15) {
			xPos -= (xPos % 30);
		} else {
			int differenz = 30 - xPos % 30;
			xPos += differenz;
		}
			//für y
		if ((yPos % 30) < 15) {
			yPos -= (yPos % 30);
		} else {
			int differenz = 30 - yPos % 30;
			yPos += differenz;
		}
	}
	
	@Override
	public void move(SnakeGame game, List<GameObject> object, PApplet p) {
		collide(game, object, p);
		if(wrongDirection == false) {
			direction = Toolbox.randomInt(0, 4);
			if(yourNumber == 2) {
			}
			}
		if(wrongDirection) {
		}
		
		//Richtungen bestimmen
		if(direction == 0) {
			yPos -= 30;
		}else if(direction == 1) {
			xPos += 30;
		}else if(direction == 2) {
			yPos += 30;
			
		}else if(direction == 3) {
			xPos -= 30;
		}
		wrongDirection = false;
	}

	@Override
	public void collide(SnakeGame game, List<GameObject> object, PApplet p) {
		//hedge
		if(xPos < 90) {
			wrongDirection = true;
			direction = Toolbox.randomInt(0, 2);
		}else if(xPos > 630) {
			wrongDirection = true;
			direction = Toolbox.randomInt(0, 3);
			
			if(direction == 1) {
				direction = 3;
			}
		}else if(yPos < 120) {
			wrongDirection = true;
			direction = Toolbox.randomInt(1, 3);
		}else if(yPos > 630) {
			wrongDirection = true;
			direction = Toolbox.randomInt(0, 3);
			if(direction == 2) {
				direction = 0;
			}
				}
		
	}

	@Override
	public boolean isDead(SnakeGame game, List<GameObject> object, PApplet p) {
		newSnail(game, p);
		return false;
	}

	@Override
	public int getXPos() {
		return xPos;
		
	}

	@Override
	public int getYPos() {
		return yPos;
	}

	@Override
	public List getList(int number) {
		// TODO Auto-generated method stub
		return null;
	}


}
