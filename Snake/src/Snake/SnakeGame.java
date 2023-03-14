package Snake;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import hdm.shared.Toolbox;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;



public class SnakeGame extends PApplet {

	static SnakeGame game;
	Apple apple;
	Snake snake;
	Hedge hedge;
	Snail snail1;
	Snail snail2;
	Snail snail3;
	GhostSnake ghostSnake1;
	GhostSnake ghostSnake2;
	GhostSnake ghostSnake3;
	SpecialApple specialApple;

	Timer time = new Timer();
	List<GameObject> objects = new ArrayList<>();

	PFont snakeFont;
	PFont gameFont;
	PImage myImage;

	boolean hasStarted = false;
	boolean specialA = false;
	boolean ghostSnakesOut = false;
	int width = 720;
	int height = 780;
	int cube = 30;
	int numberSnails = 5;
	int tailNumber;
	int color = 255;
	int safedTime = 5;
	int xRect = 150;
	int yRect = 270;
	int buttonWidth = 14 * cube;
	int buttonHeigth = 6 * cube;

	// timmer
	int timerX = 620;
	int timerY = 40;
	int minutes = 0;
	int seconds = 0;
	int seconds2 = 0;
	int newSeconds = 0;
	int length = 11;
	static int points = 0;
	int numberOfObjects = 0;
	int j = 0;
	
	int xApple;
	int yApple;

	SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	public static void main(String args[]) {
		// "--present" for activating full screen mode.
		PApplet.main(new String[] { SnakeGame.class.getName() });
		game = new SnakeGame();

	}

	public void settings() {
		size(width, height);
	}

	public void setup() {
		Toolbox.playAudioFileAsychronously("/Snake/BackgroundMusic.wav");
		myImage = loadImage("src/Snake/PlayButton.jpg");
		snakeFont = createFont("Arial", 30);
		gameFont = createFont("Retro Gaming", 165);
		
		// Objekte im array erstellen
		objects.add(apple = new Apple(game, this));
		objects.add(snake = new Snake(length, this, apple));
		objects.add(hedge = new Hedge());
		objects.add(snail1 = new Snail(game, this));
		objects.add(snail2 = new Snail(game, this));
		objects.add(snail3 = new Snail(game, this));
		objects.add(ghostSnake1 = new GhostSnake(game, this));
		objects.add(ghostSnake2 = new GhostSnake(game, this));
		objects.add(ghostSnake3 = new GhostSnake(game, this));
		objects.add(specialApple = new SpecialApple(game, this));

		// Eckdaten usw
		noStroke();
		fill(220);
		rect(0, 0, width, 2 * cube);

		fill(30);
		textFont(snakeFont);
		textSize(50);
		text("SNAKE", 270, 45);
		startSpielfeld();
		snake.tails.add(0, null);
	}

	public void draw() {
		if (hasStarted == false) {
			image(myImage, 150, 300, 14 * cube, 6 * cube);
			if (gameStart(mouseX, mouseY, xRect, yRect, buttonWidth, buttonHeigth) && mousePressed) {
				Toolbox.playAudioFileAsychronously("/Snake/Mouse.wav");
				hasStarted = true;
				noStroke();
				fill(7, 115, 122);
				rect(0, 0, width, 2 * cube);

				fill(255);
				textFont(snakeFont);
				textSize(50);
				text("SNAKE", 270, 45);
				time.reset();
			}
		} else {
			newSeconds = seconds;
			int newSeconds2 = seconds2;

			xApple = apple.getXPos();
			yApple = apple.getYPos();

			spielfeld();

			if (newSeconds < seconds) {
				snail1.move(game, objects, this);
				snail2.move(game, objects, this);
				snail3.move(game, objects, this);
				if(ghostSnakesOut == false) {
				ghostSnake1.move(game, objects, this);
				ghostSnake2.move(game, objects, this);
 				ghostSnake3.move(game, objects, this);
			}
			}

			if(ghostSnakesOut) {
				if(j == 0) {
				safedTime = seconds;
				j++;
				}
				if(seconds == safedTime +5) {
					specialApple.dead=false;
					ghostSnakesOut = false;
					j=0;
				}
			}
			if(specialApple.dead) {
				ghostSnakesOut = true;
				specialA = false;
			}
			
			if (newSeconds2 < seconds2) {
				snake.move(game, objects, this);
				specialApple.color++;
			}
			
			apple.draw(game, objects, this);
			snake.draw(game, objects, this);
			snail1.draw(game, objects, this);
			snail2.draw(game, objects, this);
			snail3.draw(game, objects, this);
			ghostSnake1.draw(game, objects, this);
			ghostSnake2.draw(game, objects, this);
			ghostSnake3.draw(game, objects, this);
			
			 if(seconds == 0 || seconds%30 == 0) {
				 specialA = true;
			 }
			if(specialA) {
				specialApple.draw(game, objects, this);
			}
			
			// snake dead?
			if (objects.get(1).isDead(game, objects, this)) {
				endGame();
			}

			timePoints();
			snake.collide(game, objects, this);
			ghostSnake1.collide(game, objects, this);
			ghostSnake2.collide(game, objects, this);
			ghostSnake3.collide(game, objects, this);
		}
	}

	//zweizeiten weil die snake schneller als der rest ist
	int getTime() {
		long time2 = time.getElapsedTime();
		time2 /= 1000000000;
		return (int) time2;
	}

	int getTime2() {
		long time2 = time.getElapsedTime();
		time2 /= 100000000;
		return (int) time2;
	}

	public void spielfeld() {
		// Spielfeld
		fill(152, 255, 152);
		stroke(88, 184, 132);

		for (int i = 0; i <= width; i += cube) {
			for (int j = 60; j <= height; j += cube) {
				rect(i, j, cube, cube);
			}
		}

		hedge.draw(game, objects, this);

		// background
		noStroke();
		fill(7, 115, 122);
		rect(600, 0, 120, 2 * cube);
		rect(0, 0, 200, 2 * cube);

		// time
		fill(255);
		textSize(30);

		seconds = getTime();
		seconds2 = getTime2();
	}

	public void startSpielfeld() {
		//Spielfeld bevor spiel startet
		noStroke();
		fill(220);
		rect(0, 0, width, 2 * cube);

		fill(30);
		textFont(snakeFont);
		textSize(50);
		text("SNAKE", 270, 45);

		fill(50);
		stroke(200);

		for (int i = 0; i <= width; i += cube) {
			for (int j = 60; j <= height; j += cube) {
				rect(i, j, cube, cube);
			}
		}

		fill(100);
		for (int i = 0; i <= game.width; i += game.cube) {
			rect(i, 60, game.cube, game.cube);
			rect(i, 750, game.cube, game.cube);
			rect(0, i, game.cube, game.cube);
			rect(690, i, game.cube, game.cube);
		}

		// background
		noStroke();
		fill(220);
		rect(600, 0, 120, 2 * cube);
		rect(0, 0, 200, 2 * cube);

		seconds = getTime();
		seconds2 = getTime2();
	}

	public void timePoints() {
		textFont(snakeFont);
		fill(color);
		// minutes
		if (seconds >= 60) {
			minutes += 1;
			seconds = 0;
			time.reset();
		}

		// seconds
		if (seconds < 10) {
			text("0" + minutes + ":0" + seconds, timerX, timerY);
		} else {
			text("0" + minutes + ":" + seconds, timerX, timerY);
		}
		// Points
		if (points < 10) {
			text("Points:00" + points, 20, timerY);
		} else if (points < 100) {
			text("Points:0" + points, 20, timerY);
		} else {
			text("Points:" + points, 20, timerY);
		}
	}

	public void addPoint() {
		points++;
	}

	//erst wenn Button gedrückt wird wird das Game gestarted
	static boolean gameStart(int mouseX, int mouseY, int Xrect, int Yrect, int width, int height) {
		if ((mouseX >= Xrect) && (mouseX <= (Xrect + width)) && (mouseY >= Yrect) && (mouseY <= (Yrect + height))) {
			return true;
		} else {
			return false;
		}
	}

	public void endGame() {
		Toolbox.playAudioFileAsychronously("/Snake/GameOver.wav");
		noLoop();
		startSpielfeld();
		fill(230);
		textFont(gameFont);
		text("GAME", 105, 360);
		text("OVER", 105, 510);
		color = 0;
	}
}
