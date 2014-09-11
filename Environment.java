import java.awt.Color;

import acm.graphics.*;


public class Environment implements GameElement, Parameters {
	
	public Environment(GCanvas screen){
		this.screen = screen;
	}
	public void Draw(){
		addBackground();
		buildTheWall();
	}

	
	/** Creates background */	
	
	private void addBackground(){
		GImage background = new GImage("background.jpg");
		screen.add(background);
	}
	
	/** Creates new brick */	
	private void addBrick(Color color, int positionX, int positionY){
		Brick brick[] = new Brick[NBRICK_ROWS*NBRICKS_PER_ROW];
		brick[bricksCounter]= new Brick(screen, BRICK_WIDTH, BRICK_HEIGHT, color, positionX, positionY);
		brick[bricksCounter].Draw();
		bricksCounter++;
	}
	
/** Creates a row of bricks */	
	private void fillTheRow(int positionY, Color color){
		int positionX = 0;
		for (int i=0; i<NBRICKS_PER_ROW; i++){
			addBrick(color, positionX, positionY);
			positionX+=(BRICK_WIDTH+BRICK_SEP);
		}
	}

/** Create all 10 rows of bricks */ 	
	private void buildTheWall(){
		fillTheRow(BRICK_Y_OFFSET, Color.red);
		fillTheRow(BRICK_HEIGHT+BRICK_SEP+BRICK_Y_OFFSET, Color.red);
		
		fillTheRow((BRICK_HEIGHT+BRICK_SEP)*2+BRICK_Y_OFFSET, Color.orange);
		fillTheRow((BRICK_HEIGHT+BRICK_SEP)*3+BRICK_Y_OFFSET, Color.orange);
		
		fillTheRow((BRICK_HEIGHT+BRICK_SEP)*4+BRICK_Y_OFFSET, Color.yellow);
		fillTheRow((BRICK_HEIGHT+BRICK_SEP)*5+BRICK_Y_OFFSET, Color.yellow);
		
		fillTheRow((BRICK_HEIGHT+BRICK_SEP)*6+BRICK_Y_OFFSET, Color.green);
		fillTheRow((BRICK_HEIGHT+BRICK_SEP)*7+BRICK_Y_OFFSET, Color.green);
		
		fillTheRow((BRICK_HEIGHT+BRICK_SEP)*8+BRICK_Y_OFFSET, Color.cyan);
		fillTheRow((BRICK_HEIGHT+BRICK_SEP)*9+BRICK_Y_OFFSET, Color.cyan);
	}
	
/**Instance variables */	
	private GCanvas screen;
	private int bricksCounter = 0;

}
