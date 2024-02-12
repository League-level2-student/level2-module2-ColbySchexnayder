package _08_LeagueSnake;

import java.util.ArrayList;

import processing.core.PApplet;

public class LeagueSnake extends PApplet {
	static final int WIDTH = 800;
	static final int HEIGHT = 800;

	/*
	 * Game variables
	 * 
	 * Put all the game variables here.
	 */
	Segment head;
	ArrayList<Segment> tail = new ArrayList<Segment>();
	int foodX;
	int foodY;
	int size = 10;
	int direction = UP;
	int foodEaten = 0;

	/*
	 * Setup methods
	 * 
	 * These methods are called at the start of the game.
	 */
	@Override
	public void settings() {
		size(WIDTH, HEIGHT);
	}

	@Override
	public void setup() {
		head = new Segment(WIDTH / 2, HEIGHT / 2);
		frameRate(20);
		dropFood();
	}

	void dropFood() {
		// Set the food in a new random location
		foodX = ((int) random(80) * 10);
		foodY = ((int) random(80) * 10);
	}


	@Override
	public void draw() {
		background(0, 0, 0);
		move();
		
		drawFood();
		drawSnake();
	}

	void drawFood() {
		// Draw the food
		fill(255, 0, 0);
		rect(foodX, foodY, size, size);
	}

	void drawSnake() {
		// Draw the head of the snake followed by its tail
		fill(0, 255, 0);
		rect(head.x, head.y, size, size);
		manageTail();
		eat();
		textSize(20);
		fill(255, 255, 255);
		text("Food Eaten: "+foodEaten, 30, 30);
	}

	void drawTail() {
		// Draw each segment of the tail
		for (Segment s : tail) {
			rect(s.x, s.y, size, size);
		}
	}

	/*
	 * Tail Management methods
	 * 
	 * These methods make sure the tail is the correct length.
	 */

	void manageTail() {
		// After drawing the tail, add a new segment at the "start" of the tail and
		// remove the one at the "end"
		// This produces the illusion of the snake tail moving.
		checkTailCollision();
		drawTail();
		tail.add(new Segment(head.x, head.y));
		tail.remove(0);
	}

	void checkTailCollision() {
		// If the snake crosses its own tail, shrink the tail back to one segment
		for (Segment s : tail) {
			if (s.x == head.x && s.y == head.y) {
				foodEaten = 0;
				tail.clear();
				return;
			}
		}
	}

	/*
	 * Control methods
	 * 
	 * These methods are used to change what is happening to the snake
	 */

	@Override
	public void keyPressed() {
		// Set the direction of the snake according to the arrow keys pressed
		if (key == CODED) {
			if (keyCode == LEFT && direction != RIGHT) {
				direction = LEFT;
			}
			if (keyCode == RIGHT && direction != LEFT) {
				direction = RIGHT;
			}
			if (keyCode == UP && direction != DOWN) {
				direction = UP;
			}
			if (keyCode == DOWN && direction != UP) {
				direction = DOWN;
			}
		}
	}

	void move() {
		// Change the location of the Snake head based on the direction it is moving.

		if (direction == UP) {
			// Move head up
			head.y -= 10;
		} else if (direction == DOWN) {
			// Move head down
			head.y += 10;
		} else if (direction == LEFT) {
			head.x -= 10;
		} else if (direction == RIGHT) {
			head.x += 10;
		}
		checkBoundaries();
	}

	void checkBoundaries() {
		// If the snake leaves the frame, make it reappear on the other side
		if (head.x < 0) {
			head.x = WIDTH - 10;
		}
		if (head.x > WIDTH) {
			head.x = 0;
		}
		if (head.y < 0) {
			head.y = HEIGHT - 10;
		}
		if (head.y > HEIGHT) {
			head.y = 0;
		}
	}

	void eat() {
		// When the snake eats the food, its tail should grow and more
		// food appear
		if (head.x == foodX && head.y == foodY) {
			foodEaten++;
			dropFood();
			tail.add(new Segment(head.x, head.y));
		}
	}

	static public void main(String[] passedArgs) {
		PApplet.main(LeagueSnake.class.getName());
	}
}
