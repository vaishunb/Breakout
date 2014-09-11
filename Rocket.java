
import acm.graphics.*;

public class Rocket extends Breakout implements GameElement {
	private static final int WIDTH = 34;
	private static final int HEIGHT = 100;
	private static final int FIREBALL_SIZE = 200;
	
	Rocket(GCanvas screen){
		this.screen = screen;
		rocket = new GImage("rocket.gif");
		rocket.setSize(WIDTH, HEIGHT);
	}
	
	public void Draw(){
		screen.add(rocket);
	}
	
	public void setLocation(int x, int y){
		rocket.setLocation(x, y);
	}
	
	public void bang(){
		GImage fireball = new GImage("fireball.gif");
		fireball.setLocation(rocket.getX(), rocket.getY()-fireball.getHeight()/2);
		fireball.setSize(0, 0);
		for(int i=0; i<FIREBALL_SIZE; i++){
			screen.add(fireball);
			pause(30);
			screen.remove(fireball);
			fireball.setSize(i, i);
		}
	}
	
	private GImage rocket;
	private GCanvas screen;

}
