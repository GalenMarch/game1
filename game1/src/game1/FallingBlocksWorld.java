package game1;

import java.util.Random;
import java.util.LinkedList;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

        // Moving Blocks Project game manual:
        //
        // DODGING FALLING BLOCKS
        //
        // A field of play where the blocks move:
        // My game will be played on a black background
        //
        // A set of "live" blocks controlled by the player:
        // The player will control a single block that can move left
        // and right across the bottom of the playing field.
        //
        // A set of "dead" blocks that are no longer plater controlled:
        // The object of my game is to dodge randomly-generated blocks as they
        // fall from the top of the playing field.  In addition, the player 
        // has three lives.  Each time the player is hit with a block, the player
        // controlled block "dies" and remains in the spot where it was struck, reducing
        // the amount of space the player has to dodge blocks later in the game.  This 
        //will ideally add more challenge to the game as it progresses.
        //
        // A scoring system:
        // For each second the player remains alive, ten points will be awarded
        //
        // A win or fail state:
        // The game is lost when the player loses all three lives (i.e. when the player
        // is hit with falling blocks three times)
        //
        // A control mechanism:
        // The player will use the left and right arrow keys to control their block

public class FallingBlocksWorld extends World {
    
    public static final int boardWidth = 500;
    public static final int boardHeight = 500;
    public boolean gameOver;
    public Random random = new Random();
    
    public int score;
    public int speed;
    public int frames;
    public int interval;
            
    
    public User user;
    public ActiveBlocks activeBlocks;
    static FallingBlocksWorld world;
    
    public FallingBlocksWorld() {
        this(5); 
    }
    
    public FallingBlocksWorld(int speed) {
        super();
        this.user = new User(new Posn(250,475), boardWidth, boardHeight);
        this.speed = speed;
        this.frames = 0;
        this.score = 0;
        this.gameOver = false;
    }
    
    public FallingBlocksWorld(User user, int speed, int frames, int score,
                              int interval, boolean gameOver) {
        this.user = user;
        this.speed = speed;
        this.frames = frames;
        this.score = score;
        this.interval = interval;
        this.gameOver = gameOver;
    }
    
    public World onTick() {
        return new FallingBlocksWorld(user, speed, frames+1, score+10, interval, gameOver);
    }
    
    public World onKeyEvent(String direction) {
        if (direction.equals("left") || (direction.equals("right"))) {
            return new FallingBlocksWorld(user.move(direction), speed, frames, score, 
                    interval, gameOver);
        } else {
            return this;
        }
    }
    
    public WorldImage screen() {
        return new RectangleImage(
        new Posn(boardWidth/2,boardHeight/2),
        boardWidth,
        boardHeight,        
        new Black());    
    }
    
    public TextImage scoreBox() {
        return new TextImage(
                new Posn(67,20),
                "Your Score: " + score,
                new Blue());
    }
    
    public WorldImage makeImage() {
        return new OverlayImages(screen(), new OverlayImages(scoreBox(),
                                           user.drawImage()));
    }
    
    public static void main(String[] args) { 
        FallingBlocksWorld theWorld = new FallingBlocksWorld();
        theWorld.bigBang(boardWidth,boardHeight,0.15);    
    }
   
}
