package javagame;

import java.awt.Rectangle;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState {
	
	Image playNow;
	Image exitGame;
	
	public String mouse = "No Input";
	public Menu (int state){
	}
	
	public void init (GameContainer gc, StateBasedGame sbg) throws SlickException{
		playNow = new Image ("res/playNow.png");
		exitGame = new Image ("res/exitGame.png");	
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		int posY = 1000 - ypos;
		
		g.drawString("The Best 2D Game Ever, Period", 800, 50);
		g.drawString(mouse, 800, 100);
		playNow.draw(100, 100);
		exitGame.draw(100, 200);
		//											Collision!!
		Rectangle T = new Rectangle(0, 500, 2000, 100);
		Rectangle M = new Rectangle(xpos - 10, posY - 10, 30, 30);
		
		if (T.intersects(M)){
		g.drawOval(xpos - 10, posY - 10, 30, 30);
		}

	}
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		mouse = "x: " + xpos + "  y:" + ypos;
		int posY = 1000 - ypos;
		
		//playNow Button
		if ((xpos > 100 && xpos < 311) && (posY > 109 && posY < 160)){
			if (Mouse.isButtonDown(0)){
				sbg.enterState(1);
			}
		}
		//exit Button
		if ((xpos > 100 && xpos < 311) && (posY > 209 && posY < 260)){
			if (Mouse.isButtonDown(0)){
				System.exit(0);
			}
		}
	}
	
	public int getID(){
		return 0;
	}
	
}