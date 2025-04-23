package assignment9;

import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Game {
    private Snake snake;
    private Food food;

	
	public Game() {
		StdDraw.enableDoubleBuffering();
        snake = new Snake();
        food = new Food();
	}
	
	public void play() {
	    showIntroScreen();
	    
	    while (snake.isInbounds()) {
	        int dir = getKeypress();
	        if (dir != -1) {
	            snake.changeDirection(dir);
	        }
	        snake.move();

	        if (snake.eatFood(food)) {
	            food.generateNewPosition();
	        }

	        updateDrawing();

	        try {
	            Thread.sleep(100);  // Sleep for 100 milliseconds before updating the game
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    showGameOverScreen();
	}
			/*
			 * 1. Pass direction to your snake
			 * 2. Tell the snake to move
			 * 3. If the food has been eaten, make a new one
			 * 4. Update the drawing
			 */

	
	private int getKeypress() {
		if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			return 1;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			return 2;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			return 3;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
			return 4;
		} else {
			return -1;
		}
	}
	
	/**
	 * Clears the screen, draws the snake and food, pauses, and shows the content
	 */
	private void updateDrawing() {
		StdDraw.clear();
        snake.draw();
        food.draw();
        StdDraw.text(0.9, 0.95, "Score: " + snake.snakeSize());
        StdDraw.pause(50);
        StdDraw.show();
		
		/*
		 * 1. Clear screen
		 * 2. Draw snake and food
		 * 3. Pause (50 ms is good)
		 * 4. Show
		 */
	}
	
	private void showIntroScreen() {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.GREEN);

        // Draw title
        StdDraw.text(0.5, 0.7, "Snake Game");

        // Draw instructions
        StdDraw.text(0.5, 0.5, "Use W, A, S, D to control the snake");
        StdDraw.text(0.5, 0.4, "Eat the red food to grow");

        // Draw start prompt
        StdDraw.text(0.5, 0.2, "Press any key to start!");

        StdDraw.show();

        // Wait for the user to press any key to start
        while (!StdDraw.hasNextKeyTyped()) {
            StdDraw.pause(100);
        }

        // Clear the key buffer
        StdDraw.nextKeyTyped();
    }

    private void showGameOverScreen() {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.RED);

        // Draw game over message
        StdDraw.text(0.5, 0.5, "Game Over!");
        StdDraw.text(0.5, 0.4, "Score: " + snake.snakeSize());

        StdDraw.show();
    }
	
	public static void main(String[] args) {
		Game g = new Game();
		g.play();
	}
}

