/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;

import java.awt.event.*;

import javax.swing.JOptionPane;

public class Breakout extends GraphicsProgram implements Parameters {
/** Dimensions of game board (usually the same) */
	public static final int WIDTH = APPLICATION_WIDTH;
	public static final int HEIGHT = APPLICATION_HEIGHT;
	

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT); //Set window size	
		Environment envrmnt = new Environment(getGCanvas());
		envrmnt.Draw();
		createBall();	
		addKeyListeners();
		addMouseListeners(); //Creates listener for mouse

/*		Rocket rocket = new Rocket(getGCanvas());
		rocket.setLocation(100, 100);
		rocket.bang();*/

	}
	
	public void bbbang(){
		GImage fireball = new GImage("fireball.gif");
		fireball.setLocation(100, 100);
		add(fireball);
		fireball.sendToFront();
		fireball.setSize(0, 0);
		for(int i=0; i<200; i++){
			add(fireball);
			pause(1);
			remove(fireball);
			fireball.setSize(i, i);
		}
	}
		
	
/** Process the gameplay */	
	private void play(){
		velX=0;
		velY=-6;
		 while (true) { 
			 moveBall(); 
			 checkForCollision(); 
			 pause(DELAY); 
		 }
	}
	
/** Moves the paddle with the mouse */	
	public void mouseMoved(MouseEvent e){
		//Shows coordinates
		if(label!=null) remove(label);
		label = new GLabel("X = "+e.getX()+" Y = "+e.getY() , WIDTH-100, HEIGHT);
		add(label);
		
		// Checks if paddle is out of window
		if(e.getX()<WIDTH-PADDLE_WIDTH){
			if(paddle!=null) remove(paddle);
			createPaddle(e);
		}
	}

/** Waits for the click and starts the game */	
	public void mouseClicked(MouseEvent e){
		if(!isGameStarted){
			isGameStarted = true;
			Runnable runnable = new Runnable() {
			    public void run() {
			    	//I call the play() method in a new thread. This way mouseClicked method can be successfully finished.
			        play();
			    }
			};

			new Thread(runnable).start();
		}
	}	

	
		
/** Creates paddle */	
	private void createPaddle(MouseEvent e){
		paddle = new GImage("paddle.gif",e.getX(), HEIGHT-PADDLE_Y_OFFSET);
		paddle.setSize(PADDLE_WIDTH, PADDLE_HEIGHT);
		add(paddle);
	}

/** Creates ball */	
	private void createBall(){
		ball = new GImage("ball.gif", WIDTH/2-BALL_RADIUS, HEIGHT-(PADDLE_Y_OFFSET+2*BALL_RADIUS));
		ball.setSize(BALL_RADIUS*2, BALL_RADIUS*2);
		add(ball);
	}
	
	private void moveBall(){
		ball.move(velX, velY);
	}
	
	private void checkForCollision(){
		ballActualLocation = ball.getLocation();
		if ((ballActualLocation.getX()<0)||ballActualLocation.getX()>WIDTH-BALL_RADIUS*2){
			velX=-velX;
		}
		if (ballActualLocation.getY()<0){
			velY=-velY;
		}
			
		if (ballActualLocation.getY()>HEIGHT){
			gameOver();
		}
		
		//Checking the ball side to collide with object
		if(velY<0){
			if(velX<0)checkObject = getElementAt(ball.getX()+BALL_RADIUS*2, ball.getY());
			else checkObject = getElementAt(ball.getX()+BALL_RADIUS*2, ball.getY());
		}
		else{
			if(velX<0)checkObject = getElementAt(ball.getX(), ball.getY()+BALL_RADIUS*2);
			else checkObject = getElementAt(ball.getX()+BALL_RADIUS*2, ball.getY()+BALL_RADIUS*2);
		}
		
		if(checkObject!=null){
			/* Check what the object the collision happened with */
			if(checkObject instanceof GRect){
				remove(checkObject);
				velY=-velY;
			}
			else if ((checkObject.getX()!=0)&&(checkObject.getWidth()==paddle.getWidth())){
				pullToDirection();
				velY=-velY;
			}
		}
	}
	
	/** If paddle is moving pull the ball to the right direction */	
	private void pullToDirection(){
		double testX=paddle.getX();
		try {
		    Thread.sleep(50);
		} catch (InterruptedException e) {
		    // Handle here
		}
		if (paddle.getX()<testX){
			velX-=2;
		} else if (paddle.getX()>testX){
			velX+=2;
		}
	}
	
	
	//* Says that the gamer is looser and shutdown the game
	private void gameOver(){
		JOptionPane.showMessageDialog(null, "U R LOOSER X_X");
		System.exit(0);
	}
	
/** Instance variables */	
	private GImage paddle;
	private GImage ball;
	private GLabel label;
	
	private int velX, velY; //Velocity
	private boolean isGameStarted = false;
	private GObject checkObject;
	private GPoint ballActualLocation;
	

}
