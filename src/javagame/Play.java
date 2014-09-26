package javagame;

import java.awt.Rectangle;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

		public class Play extends BasicGameState{
		
			Image MapScroller;
			
			Animation webb, movingUp, movingDown, movingLeft, movingRight;
			
			int[] duration = {200,200};
			
			boolean quit = false;
			public float webbPositionX = 0;
			public float webbPositionY = 0;
			float shiftX = webbPositionX +  950;
			float shiftY = webbPositionY + 	500;
			
			int CollisionNum = 0;
			boolean Collision = false;
			
			
	public Play (int state){
	}
	
	public void init (GameContainer gc, StateBasedGame sbg) throws SlickException{
		MapScroller = new Image ("res/MapScroller.png");
		Image[] walkUp = {new Image("res/buckysBack.png"),new Image("res/buckysBack.png")};
		Image[] walkDown = {new Image("res/buckysFront.png"),new Image("res/buckysFront.png")};
		Image[] walkLeft = {new Image("res/buckysLeft.png"),new Image("res/buckysLeft.png")};
		Image[] walkRight = {new Image("res/buckysRight.png"),new Image("res/buckysRight.png")};
		
		movingUp = new Animation(walkUp, duration, false);
		movingDown = new Animation(walkDown, duration, false);
		movingLeft = new Animation(walkLeft, duration, false);
		movingRight = new Animation(walkRight, duration, false);

		webb = movingDown;
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		MapScroller.draw(webbPositionX, webbPositionY);
		webb.draw(shiftX, shiftY);
		
		g.drawString("Webbs X: "+ webbPositionX +"\nWebbs Y:" + webbPositionY, 1000, 20);
			
		//Menu Up
		if (quit == true){
			g.drawString("Resume (R)", 550, 300);
			g.drawString("Main Menu (M)", 550, 350);
			g.drawString("Quit (Q)", 550, 400);
			}
		
		//Collision Rectangles

		float BottomRowX = webbPositionX + 0;
		float BottomRowY = webbPositionY + 1021;
		
		float PlayerCollX = shiftX;
		float PlayerCollY = shiftY;
		
		g.drawRect(BottomRowX, BottomRowY, 1917, 57);
		//player
		g.drawRect(PlayerCollX, PlayerCollY, 40, 40);
		
		Rectangle BotRow = new Rectangle((int) BottomRowX, (int) BottomRowY, 1879, 57);
		Rectangle Player = new Rectangle((int) PlayerCollX, (int) PlayerCollY, 40, 40);
		
		
		if (BotRow.intersects(Player)){
			g.drawString("COLLISION DETCTED!!!", 450, 400);
			Collision = true;
		}else{
			Collision = false;
		}
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();
		
		
					//Player Controls
		while(Collision == true){
			if (input.isKeyDown(Input.KEY_S)){
				webbPositionY += delta * .2f;
			}else if (input.isKeyDown(Input.KEY_W)){
				webbPositionY -= delta * .2f;
			}
		}
		//Up
		if (input.isKeyDown(Input.KEY_W)){
			webb = movingUp;
			webbPositionY += .2f * delta;
			if (webbPositionY > 500){
				webbPositionY -= delta * .2f;
			}
		}
		//Down
		if (input.isKeyDown(Input.KEY_S)){
			webb = movingDown;
			webbPositionY -= delta * .2f;
			if (webbPositionY < -2460){
				webbPositionY += delta * .2f;
			}
		}
		
		//Left
		if (input.isKeyDown(Input.KEY_A)){
			webb = movingLeft;
			webbPositionX += delta * .2f;
			if (webbPositionX > 950){
				webbPositionX -= delta * .2f;
			}
		}
		//Right
		if (input.isKeyDown(Input.KEY_D)){
			webb = movingRight;
			webbPositionX -= delta * .2f;
			if (webbPositionX < -2010){
				webbPositionX += delta * .2f;
			}
		}
	
		//Escape
		if (input.isKeyDown(Input.KEY_ESCAPE)){
			quit = true;
		}
		//Menu = Up
		if (quit == true){
			if (input.isKeyDown(Input.KEY_R)){
				quit = false;
			}
			if (input.isKeyDown(Input.KEY_M)){
				sbg.enterState(0);
			}
			if (input.isKeyDown(Input.KEY_Q)){
				System.exit(0);
				}
			}
	}
		
	
	public int getID(){
		return 1;
	}
	
}