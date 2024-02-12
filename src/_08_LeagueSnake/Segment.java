package _08_LeagueSnake;

/*
 * This class will be used to represent each part of the moving snake.
 * 
 * 1. Add x and y member variables.
 *    They will hold the corner location of each segment of the snake.
 * 
 * 2. Add a constructor with parameters to initialize each variable.
 */
public class Segment {
    int x = 0;
    int y = 0;
    
    public Segment(int x, int y) {
    	this.x = x;
    	this.y = y;
    }
}
