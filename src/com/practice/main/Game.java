package com.practice.main;

import java.util.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -1890191545605463095L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	private Random r;
	private Handler handler;
	
	public Game() {
		
		handler = new Handler();
				
		this.addKeyListener(new KeyInput(handler)); //Listen for key
		
		new Window(WIDTH, HEIGHT, "Lettuce", this);

		r = new Random();
		
		int foodX = r.nextInt(WIDTH);
		int foodY = r.nextInt(HEIGHT);

		
		foodX = foodX / 20  * 20 + 20 * (int) Math.round((double)(foodX % 20) / 20);
		
		foodY = foodY / 20  * 20 + 20 * (int) Math.round((double)(foodY % 20) / 20);
		
		int snakeX = r.nextInt(WIDTH);
		int snakeY = r.nextInt(HEIGHT-5*20);

		
		snakeX = snakeX / 20  * 20 + 20 * (int) Math.round((double)(snakeX % 20) / 20);
		
		snakeY = snakeY / 20  * 20 + 20 * (int) Math.round((double)(snakeY % 20) / 20);
		
		
		handler.addObject(new Food(foodX, foodY, 20, 20, ID.Food));
						
		LinkedList<Snake> snakeSegments = new LinkedList<Snake> ();
		
		Snake tempSegment = new Snake(snakeX, snakeY, 20, 20, ID.Snake);
		
		handler.addObject(tempSegment);
		
		snakeSegments.add(tempSegment);
		
		for(int i = 0; i < 5; i++) {
			snakeY+=20;
			
			tempSegment = new Snake(snakeX, snakeY, 20, 20, ID.Snake);
			
			handler.addObject(tempSegment);
			
			snakeSegments.add(tempSegment);
		}
		

		
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(2);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.green);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		g.dispose();
		bs.show();
		
	}
	
	public static int clamp(int var, int min, int max) {
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}

	public static void main(String[] args) {
		new Game();
	}

}
