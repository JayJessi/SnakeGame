package Snake;
import processing.core.PApplet;

public class Tail {
	
	int x;
	int y;
	int yourNumber;
	
	//für Snake
	Tail(int x, int y, PApplet p){	
		this.x = x;
		this.y = y;
		draw(x, y, p);
	}
	
	//für Ghostsnake
	Tail(int x, int y, PApplet p, int tailNumber){	
		
		this.x = x;
		this.y = y;
		this.yourNumber = tailNumber;
		
		if(yourNumber == 0) {
			p.fill(77, 180, 230);
		}else if(yourNumber == 1){
			p.fill(97, 190, 235);
		}else if(yourNumber == 2) {
			p.fill(117, 200, 240);
		}else if(yourNumber == 3) {
			p.fill(137, 210, 245);
		}else {
			p.fill(0, 127, 255);
		}
		p.rect(x, y, 30, 30);
	}

public void draw(int x, int y, PApplet p) {
	if(x != 0) {
	p.rect(x, y, 30, 30);
	}
}
}
