package com.practice.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Snake extends GameObject{
	
	Random r = new Random();
	int nextTurn;
	int remain, turnDirection;
	boolean turnFlag = false;
	

	public Snake(int x, int y, int width, int height, ID id) {
		super(x, y, width, height, id);
		
		//velX = r.nextInt(10) - 5;
		//velY = r.nextInt(10) - 5;
		
	}
	
	public void tick() {
		
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - width);
		y = Game.clamp(y, 0, Game.HEIGHT - (2  * height));
		
		if(turnFlag == true) {
			if(remain <= 0) {
				turn(turnDirection);
				
			}
			else {
				remain -= velMag;
			}
		}

		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);
		
		
	}
	
	public void calcXPos (int direction) {
		if(velX > 0) {
			//System.out.println("greater");
			nextTurn = x - x % width + width;
			remain = nextTurn - x;
						
		}
		else if(velX < 0) {
			//System.out.println("less");

			nextTurn = x - x % width - width;
			remain = x - nextTurn;

		}
		else if(velY < 0) {
			
			nextTurn = y - y % height - height;
			remain = y - nextTurn;
		}
		else if(velY > 0) {
			
			nextTurn = y - y % height + height;
			remain = nextTurn - y;
		}
		
		turnFlag = true;
		turnDirection = direction;
		
	}
	
	public void turn(int direction) {
		
		switch (direction) {
		
			case 1:		//Up
				this.velY = - velMag;
				this.velX = 0;
				break;
			case 2:		//Down
				this.velY = velMag;
				this.velX = 0;
				break;
			case 3:		//Left
				this.velY = 0;
				this.velX = - velMag;
				break;
			case 4:		//Right
				this.velY = 0;
				this.velX = velMag;
				break;
		}
		
		turnFlag = false;
		
	}
	
}
