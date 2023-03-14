package Snake;

import java.util.ArrayList;
import java.util.List;

import hdm.shared.Toolbox;
import processing.core.PApplet;

public class GhostSnake implements GameObject {
	List<Integer> x = new ArrayList<>();
	List<Integer> y = new ArrayList<>();
	List<Tail> tails = new ArrayList<>();

	int length = 5;
	static int numberOfGhostSnakes = 0;
	int yourNumber;
	int direction;
	int xPos;
	int yPos;
	int firstX;
	int firstY;
	int size = 0;
	boolean wrongDirection = false;
	
	int dir0;
	int dir1;
	int dir2;
	int dir3;
	int specialDir3;
	int lastDirection;
	
	int[]yourDirections = new int[3];

	GhostSnake(SnakeGame game, PApplet p) {
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
		int h = 0;
		while (h < length-1) {
			x.add(0);
			y.add(0);
			h++;
		}

		x.add(0, xPos);
		y.add(0, yPos);
		
		tails.add(new Tail(x.get(1), y.get(1), p, game.tailNumber));
		game.tailNumber++;
		tails.add(new Tail(x.get(2), y.get(2), p, game.tailNumber));
		game.tailNumber++;
		tails.add(new Tail(x.get(3), y.get(3), p, game.tailNumber));
		game.tailNumber++;
		tails.add(new Tail(x.get(4), y.get(4), p, game.tailNumber));
		
		yourNumber = numberOfGhostSnakes;
		numberOfGhostSnakes++;
	}

	@Override
	public void move(SnakeGame game, List<GameObject> object, PApplet p) {
		if(wrongDirection == false) {
			direction = Toolbox.randomInt(0, 4);
			}
		//nicht durch sich selbst durch
				if(lastDirection != direction) {
					int i = Toolbox.randomInt(0, 3);
					if(lastDirection == 0) {
						yourDirections = new int[]{0, 1, 3};
						direction = yourDirections[i];
					}else if(lastDirection == 1) {
						yourDirections = new int[]{0, 1, 2};
						direction = yourDirections[i];
					}else if(lastDirection == 2) {
						yourDirections = new int[]{1, 2, 3};
						direction = yourDirections[i];
					}else if(lastDirection == 3) {
						yourDirections = new int[]{0, 2, 3};
						direction = yourDirections[i];
					}
					
				}

		// damit snail im Raster bleibt
		// für x
		if ((xPos % 30) < 15) {
			xPos -= (xPos % 30);
		} else {
			int differenz = 30 - xPos % 30;
			xPos += differenz;
		}
		// für y
		if ((yPos % 30) < 15) {
			yPos -= (yPos % 30);
		} else {
			int differenz = 30 - yPos % 30;
			yPos += differenz;
		}

		// Richtungen bestimmen
		if (xPos <= 30) {
			direction = 1;
		} else if (xPos >= 660) {
			direction = 3;
		}

		if (yPos <= 90) {
			direction = 2;
		} else if (yPos >= 690) {
			direction = 0;
		}
		if (direction == 0) {
			yPos -= 30;
			newXY(xPos, yPos, length);
		} else if (direction == 1) {
			xPos += 30;
			newXY(xPos, yPos, length);
		} else if (direction == 2) {
			yPos += 30;
			newXY(xPos, yPos, length);
		} else if (direction == 3) {
			xPos -= 30;
			newXY(xPos, yPos, length);
		}
	}

	@Override
	public void collide(SnakeGame game, List<GameObject> object, PApplet p) {
		//hedge
		if(xPos < 60) {
			wrongDirection = true;
			direction = Toolbox.randomInt(0, 2);
		}else if(xPos > 660) {
			wrongDirection = true;
			
				yourDirections = new int []{0,2,3};
				int i = Toolbox.randomInt(0, 2);
				direction = yourDirections[i];
				direction = 3;
				System.out.println("2");
			
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
	public void draw(SnakeGame game, List<GameObject> object, PApplet p) {
		p.fill(0, 127, 255);
		p.rect(xPos, yPos, 30, 30);
		p.fill(77, 180, 230);
		tails.get(0).draw(x.get(1), y.get(1), p);
		p.fill(97, 190, 235);
		tails.get(1).draw(x.get(2), y.get(2), p);
		p.fill(117, 200, 240);
		tails.get(2).draw(x.get(3), y.get(3), p);
		p.fill(137, 210, 245);
		tails.get(3).draw(x.get(4), y.get(4), p);
		wrongDirection = false;
	
		lastDirection = direction;
		
	}

	@Override
	public boolean isDead(SnakeGame game, List<GameObject> object, PApplet p) {
		return false;
	}

	@Override
	public int getXPos() {
		// TODO Auto-generated method stub
		return xPos;
	}

	@Override
	public int getYPos() {
		// TODO Auto-generated method stub
		return yPos;
	}
	
	public List getList(int number) {
		if(number == 0) {
		return x;
		}else {
			return y;
		}
	}


	public void newXY(int xPos, int yPos, int length) {
		length = length;
		size++;
		firstX = xPos;
		firstY = yPos;
		x.add(0, xPos);
		y.add(0, yPos);
		if ((length) + 1 > length) {
			x.remove(length);
			y.remove(length);
		}
	}
	
}