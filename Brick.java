import java.awt.Color;
import acm.graphics.*;

public class Brick implements GameElement{
	
	public Brick(GCanvas screen, int width, int height, Color color, int positionX, int positionY){
		this.screen = screen;

		brick = new GRect(positionX, positionY, width, height);
		brick.setFilled(true);
		brick.setFillColor(color);
	}
	
	public void Draw(){
		screen.add(brick);
	}
	
	
	public String toString(){
		return brick.getColor()+"brick. Position: X = "+brick.getX()+" Y = "+brick.getY();
	}
	
	
/**Instance variables  */	
	private GRect brick; /** Brick form(rectangle) */
	private GCanvas screen;
	

}
