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
    
    public Posn posn;
    
    public int score;
    public int speed;
    public int frames;
    public int interval;      
    
    public User user;
    static FallingBlocksWorld world;
    
    public Blocks block1 = new Blocks(this.posn, 
                                      this.boardWidth, 
                                      this.boardHeight,
                                      3);
    
    public Blocks block2 = new Blocks(this.posn, 
                                      this.boardWidth, 
                                      this.boardHeight,
                                      5);
    
    public Blocks block3 = new Blocks(this.posn, 
                                      this.boardWidth, 
                                      this.boardHeight,
                                      6);
    
    public Blocks block4 = new Blocks(this.posn, 
                                      this.boardWidth, 
                                      this.boardHeight,
                                      10);
    
    public Blocks block5 = new Blocks(this.posn, 
                                      this.boardWidth, 
                                      this.boardHeight,
                                      15);
    
    public Blocks block6 = new Blocks(this.posn, 
                                      this.boardWidth, 
                                      this.boardHeight,
                                      17);
    
    public Blocks block7 = new Blocks(this.posn, 
                                      this.boardWidth, 
                                      this.boardHeight,
                                      20);
    
    public Blocks block8 = new Blocks(this.posn, 
                                      this.boardWidth, 
                                      this.boardHeight,
                                      23);
    
    public Blocks block9 = new Blocks(this.posn, 
                                      this.boardWidth, 
                                      this.boardHeight,
                                      25);
    
    public Blocks block10 = new Blocks(this.posn, 
                                      this.boardWidth, 
                                      this.boardHeight,
                                      30);
    
    public Blocks[] theArray = {block1, block2, block3, block4, block5,
                                  block6, block7, block8, block9, block10};
    
    public ActiveBlocks activeBlocks = new ActiveBlocks(theArray);
    
    public FallingBlocksWorld() {
        this(5); 
    }
    
    public FallingBlocksWorld(int speed) {
        super();
        this.user = new User(new Posn(250,475), boardWidth, boardHeight);
        this.activeBlocks = activeBlocks.initialize();
        this.speed = speed;
        this.frames = 0;
        this.score = 0;
        this.gameOver = false;
    }
    
    public FallingBlocksWorld(User user, ActiveBlocks activeBlocks, int speed, int frames, 
            int score, int interval, boolean gameOver) {
        this.user = user;
        this.activeBlocks = activeBlocks;
        this.speed = speed;
        this.frames = frames;
        this.score = score;
        this.interval = interval;
        this.gameOver = gameOver;
    }
              
    public World onTick() {
        activeBlocks = activeBlocks.fallBlocks();
        return new FallingBlocksWorld(user, activeBlocks, speed, frames+1, score+10, interval, gameOver);
    }
    
    public World onKeyEvent(String direction) {
        if (direction.equals("left") || (direction.equals("right"))) {
            return new FallingBlocksWorld(user.move(direction), activeBlocks, speed, frames, score, 
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
    
    public WorldImage gameOverScreen() {
        TextImage gameOver = new TextImage(
                             new Posn(200,200),
                             "Better luck next time, pal. Final Score: " + score,
                             new Red());
        return new OverlayImages(screen(), gameOver);
    }
    
    public TextImage scoreBox() {
        return new TextImage(
                new Posn(67,20),
                "Your Score: " + score,
                new Blue());
    }
    
    public WorldImage makeImage() {
        return new OverlayImages(screen(), new OverlayImages(scoreBox(),
                                           new OverlayImages(user.drawImage(),
                                           activeBlocks.drawImage()))); 
    }
    
    public static void main(String[] args) { 
        FallingBlocksWorld theWorld = new FallingBlocksWorld();
        theWorld.bigBang(boardWidth,boardHeight,0.15);    
    }
   
}
