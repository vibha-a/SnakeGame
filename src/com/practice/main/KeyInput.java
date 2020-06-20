package com.practice.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			//TODO change to dictionary
			
			if(tempObject.getId() == ID.Snake) {
				if(key == KeyEvent.VK_UP && tempObject.getVelY() == 0) {
					((Snake) tempObject).calcXPos(1);
					
				}
				if(key == KeyEvent.VK_DOWN && tempObject.getVelY() == 0) {
					((Snake) tempObject).calcXPos(2);

				}
				if(key == KeyEvent.VK_LEFT && tempObject.getVelX() == 0) {
					/*int dir = tempObject.getVelY() < 0 ? -1 : 1;
					tempObject.setY(tempObject.getY() - tempObject.getY() % tempObject.getHeight() + (tempObject.getHeight() * dir));
					tempObject.setVelX(-(tempObject.getVelMag()));
					tempObject.setVelY(0);*/
					((Snake) tempObject).calcXPos(3);
					
				}
				if(key == KeyEvent.VK_RIGHT && tempObject.getVelX() == 0) {
					/*int dir = tempObject.getVelY() < 0 ? -1 : 1;
					tempObject.setY(tempObject.getY() - tempObject.getY() % tempObject.getHeight() + (tempObject.getHeight() * dir));
					tempObject.setVelX(tempObject.getVelMag());
					tempObject.setVelY(0);*/
					((Snake) tempObject).calcXPos(4);
				}

			}
				
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
	}
	
}
