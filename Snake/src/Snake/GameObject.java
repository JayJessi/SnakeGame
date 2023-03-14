package Snake;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public interface GameObject{
	
	int xPos=0;
	int yPos=0;
	
	List<Integer> x = new ArrayList<Integer>();
	List<Integer> y = new ArrayList<Integer>();
	
	void move(SnakeGame game, List<GameObject> object, PApplet p);
	
	void collide(SnakeGame game, List<GameObject> object, PApplet p);
	
	void draw(SnakeGame game, List<GameObject> object, PApplet p);
	
	boolean isDead(SnakeGame game, List<GameObject> object, PApplet p);

	int getXPos();
	
	int getYPos();
	
	List getList(int number);
}
