package Snake;

import java.util.ArrayList;
import java.util.List;
import hdm.shared.Toolbox;
import processing.core.PApplet;

public class Snake implements GameObject {

	static List<Integer> x = new ArrayList<>();
	static List<Integer> y = new ArrayList<>();
	static List<Tail> tails = new ArrayList<>();

	Timer time = new Timer();

	static int length = 5;
	static int factor = 6;
	static int newLength = length;
	int speed = 30;
	int cube = 30;
	int direction = 4;
	int firstY = 0;
	int firstX = 0;
	int xPos = 720 / 2;
	int yPos = 720 / 2 + 30;
	boolean up = false;
	boolean down = false;
	boolean right = false;
	boolean left = false;
	int lastDirection = 4;

	int number;
	int size = 0;
	boolean kright;
	boolean isDead = false;

	int xApple;
	int yApple;
	int xSApple;
	int ySApple;

	List xGhost1;
	List yGhost1;
	List xGhost2;
	List yGhost2;
	List xGhost3;
	List yGhost3;

	Snake(int length, PApplet p, Apple apple) {
		int h = 0;
		while (h < newLength) {
			x.add(0);
			y.add(0);
			h++;
		}
	}

	public void collide(SnakeGame game, List<GameObject> object, PApplet p) {

		// mit Wand
		if (xPos < 0 || xPos > 660 || yPos < 90 || yPos > 720) {
			isDead = true;
		}

		// mit Snake
		int getX;
		for (int i = 0; i < x.size(); i++) {
			getX = x.get(i);
			if (getX == xPos) {
				if (y.get(i) == yPos) {
					isDead = true;
				}
			}
		}

		// mit Apfel
		if (xPos == xApple && yPos == yApple) {
			object.get(0).isDead(game, object, p);
			game.points++;
			length++;
			newTail(p);
			createTails(length, p);
			Toolbox.playAudioFileAsychronously("/Snake/PointSound.wav");
		}

		//beim SpecialApfel bewegen sich die GhostSakes für 5 Sekunden nicht mehr
		// mit SpecialApfel
		if (xPos == xSApple && yPos == ySApple) {
			object.get(9).isDead(game, object, p);
			game.points++;
			length++;
			newTail(p);
			createTails(length, p);
			Toolbox.playAudioFileAsychronously("/Snake/SpecialSound.wav");
		}

		// mit Schnecke
		if (xPos == object.get(3).getXPos() && yPos == object.get(3).getYPos()) {
			object.get(3).isDead(game, object, p);
			game.points++;
			length++;
			newTail(p);
			createTails(length, p);
			Toolbox.playAudioFileAsychronously("/Snake/PointSound.wav");
		}

		if (xPos == object.get(4).getXPos() && yPos == object.get(4).getYPos()) {
			object.get(4).isDead(game, object, p);
			game.points++;
			length++;
			newTail(p);
			createTails(length, p);
			Toolbox.playAudioFileAsychronously("/Snake/PointSound.wav");
		}

		if (xPos == object.get(5).getXPos() && yPos == object.get(5).getYPos()) {
			object.get(5).isDead(game, object, p);
			game.points++;
			length++;
			newTail(p);
			createTails(length, p);
			Toolbox.playAudioFileAsychronously("/Snake/PointSound.wav");
		}

		// mit GhostSnake
			if (xGhost1.contains(xPos) && yGhost1.contains(yPos)) {
				isDead = true;
			}

			if (xGhost2.contains(xPos) && yGhost2.contains(yPos)) {
				isDead = true;
			}
			if (xGhost3.contains(xPos) && yGhost3.contains(yPos)) {
				isDead = true;
			}
		
	}

	public void draw(SnakeGame game, List<GameObject> object, PApplet p) {
		xApple = object.get(0).getXPos();
		yApple = object.get(0).getYPos();
		xGhost1 = object.get(6).getList(0);
		yGhost1 = object.get(6).getList(1);
		xGhost2 = object.get(7).getList(0);
		yGhost2 = object.get(7).getList(1);
		xGhost3 = object.get(8).getList(0);
		yGhost3 = object.get(8).getList(1);
		xSApple = object.get(9).getXPos();
		ySApple = object.get(9).getYPos();

		if (x.get(0) == 0) {
			p.fill(120, 33, 114);
			p.rect(xPos, yPos, cube, cube);
		} else {
			
// Schlange wird erstellt
			p.fill(120, 33, 114);
			p.rect(x.get(0), y.get(0), cube, cube);
			p.fill(133, 53, 124);
			createTails(length, p);
			lastDirection = direction;
		}
	}

	public void move(SnakeGame game, List<GameObject> object, PApplet p) {
		// Steuerung
		// hoch
		if (p.key == 'w') {
			direction = 0;
			up = true;
		}

		if (p.key == 's') {
			direction = 2;
			down = true;
		}

		if (p.key == 'a') {
			direction = 3;
			left = true;
		}

		if (p.key == 'd') {
			direction = 1;
			right = true;
		}

		if (up) {
			if (lastDirection != 2) {
				newXY(xPos, yPos, length);
				yPos -= speed;
				up = false;
			} else {
				direction = lastDirection;
				if (direction == 0) {
					up = true;
				} else if (direction == 1) {
					right = true;
				} else if (direction == 2) {
					down = true;
				} else if (direction == 3) {
					left = true;
				}
				up = false;
			}
		}
		// runter
		if (down) {
			if (lastDirection != 0) {
				newXY(xPos, yPos, length);
				yPos += speed;
				down = false;
			} else {
				direction = lastDirection;
				if (direction == 0) {
					up = true;
				} else if (direction == 1) {
					right = true;
				} else if (direction == 2) {
					down = true;
				} else if (direction == 3) {
					left = true;
				}
				down = false;
			}
		}
		// links
		if (left) {
			if (lastDirection != 1) {
				newXY(xPos, yPos, length);
				xPos -= speed;
				left = false;
			} else {
				direction = lastDirection;
				if (direction == 0) {
					up = true;
				} else if (direction == 1) {
					right = true;
				} else if (direction == 2) {
					down = true;
				} else if (direction == 3) {
					left = true;
				}
				left = false;
			}
		}
		// rechts
		if (right) {
			if (lastDirection != 3) {
				newXY(xPos, yPos, length);
				xPos += speed;
				right = false;
			} else {
				direction = lastDirection;
				if (direction == 0) {
					up = true;
				} else if (direction == 1) {
					right = true;
				} else if (direction == 2) {
					down = true;
				} else if (direction == 3) {
					left = true;
				}
				right = false;
			}
		}
	}

	public void newXY(int xPos, int yPos, int length) {
		size++;
		firstX = xPos;
		firstY = yPos;
		x.add(0, xPos);
		y.add(0, yPos);
		if ((length) + 1 > newLength) {
			x.remove(length);
			y.remove(length);
		}
	}

	public void newTail(PApplet p) {
		// Koordinaten liste verlängern
		x.add(0);
		y.add(0);
	}

	public void createTails(int length, PApplet p) {
		for (int i = 1; i < length; i++) {
			tails.add(1, new Tail(x.get(i), y.get(i), p));

			if (tails.size() > length - 1) {
				tails.remove(length - 1);
			}

			if (tails.size() > length - 1) {
				tails.remove(length - 1);
			}
		}
	}

	@Override
	public boolean isDead(SnakeGame game, List<GameObject> object, PApplet p) {
		return isDead;
	}

	@Override
	public int getXPos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getYPos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List getList(int number) {
		// TODO Auto-generated method stub
		return null;
	}
}