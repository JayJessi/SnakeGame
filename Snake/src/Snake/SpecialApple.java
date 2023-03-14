package Snake;

import java.util.List;

import hdm.shared.Toolbox;
import processing.core.PApplet;

public class SpecialApple implements GameObject{

	int xPos;
	int yPos;
	int length;
	boolean dead;
	int j = 0;
	int color=0;

	SpecialApple(SnakeGame game, PApplet p) {
		newApple(game,p);
	}

	@Override
	public void draw(SnakeGame game, List<GameObject> object, PApplet p) {
		
		for(int i = 0; i<6*5; i+=5) {
			if(j==0) {
				p.fill(255, 0, 0);
			}else if(j==1) {
				p.fill(255, 131, 0);
			}else if(j==2) {
				p.fill(255, 255, 0);
			}else if(j==3) {
				p.fill(0, 245, 0);
			}else if(j==4) {
				p.fill(0, 80, 255);
			}else if(j==5) {
				p.fill(155, 0, 255);
			}
			p.rect(xPos, yPos+i, 30, 5);
			j++;		
			if(j>=6) {
			j=0;
		}
		}	
		if(color>=6) {
			color = 0;
		}
		j=color;
	}

	@Override
	public boolean isDead(SnakeGame game, List<GameObject> object, PApplet p) {
		newApple(game,p);	
		dead = true;
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
