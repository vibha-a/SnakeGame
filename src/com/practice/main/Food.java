package com.practice.main;

import java.awt.Color;
import java.awt.Graphics;

public class Food extends GameObject{
	
	public Food(int x, int y, int width, int height, ID id) {
		super(x, y, width, height, id);
		
	}
	
	public void tick() {
		
		
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
		
		
	}

}
