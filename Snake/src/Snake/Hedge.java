package Snake;
import java.util.List;

import processing.core.PApplet;

public class Hedge implements GameObject{

	@Override
	public void draw(SnakeGame game, List<GameObject> object, PApplet p) {
		p.fill(15, 67, 54);
		for (int i = 0; i <= game.width; i += game.cube) {
			p.rect(i, 60, game.cube, game.cube);
			p.rect(i, 750, game.cube, game.cube);
			p.rect(0, i, game.cube, game.cube);
			p.rect(690, i, game.cube, game.cube);
		}
		
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
	public boolean isDead(SnakeGame game, List<GameObject> object, PApplet p) {
		// TODO Auto-generated method stub
		return false;
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
