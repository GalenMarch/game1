package game1;

import tester.*;

import java.util.Random; 

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

// Moving Blocks Project game manual:
//
// DODGING FALLING BLOCKS!
// 
// The goal of this game is to stay alive as long as possible by avoiding the 
// blocks falling from the top of the screen.  The blocks fall at various speeds
// and you will die if one lands on you or you collide with the side of one as 
// it falls.  Good luck!
//
// A field of play where the blocks move:
// My game will be played on a black background
//
// A set of "live" blocks controlled by the player:
// The player will control a single block that can move left
// and right across the bottom of the playing field.
//
// A set of "dead" blocks that are no longer plater controlled:
// The set of falling blocks that must be dodged cannot be controlled by the 
// player.
//
// A scoring system:
// For each "tick" (0.15 seconds in my game) that the player remains alive, ten
// points will be awarded.
//
// A win or fail state:
// The game is lost when the player hits a falling block.
//
// A control mechanism:
// The player will use the left and right arrow keys to control their block.

public class FallingBlocksWorld extends World {
    
    public static final int boardWidth = 500;
    public static final int boardHeight = 500;
    public boolean gameOver;
    
    public int score;
    public int speed;  
    
    public User user;
    
    // The variables below determine the speeds of the ten blocks that 
    // continuously fall and get reset during game play.  The range in speeds
    // make for dynamic and unpredictable game play, and by grouping these 
    // variables together, the difficulty of the game can easily be manipulated.
    
    public int speed0 = 8;
    public int speed1 = 9;
    public int speed2 = 10;
    public int speed3 = 11;
    public int speed4 = 15;
    public int speed5 = 17;
    public int speed6 = 18;
    public int speed7 = 22;
    public int speed8 = 27;
    public int speed9 = 31;
    
    // The ten blocks defined below each get a placeholder posn (0,0) and one
    // of the ten speeds defined above.  Then these ten blocks are stored in
    // the array blockArray.
    
    public Blocks block0 = new Blocks(new Posn(0,0),
                                      speed0);
    
    public Blocks block1 = new Blocks(new Posn(0,0), 
                                      speed1);
    
    public Blocks block2 = new Blocks(new Posn(0,0), 
                                      speed2);
    
    public Blocks block3 = new Blocks(new Posn(0,0), 
                                      speed3);
    
    public Blocks block4 = new Blocks(new Posn(0,0), 
                                      speed4);
    
    public Blocks block5 = new Blocks(new Posn(0,0), 
                                      speed5);
    
    public Blocks block6 = new Blocks(new Posn(0,0), 
                                      speed6);
    
    public Blocks block7 = new Blocks(new Posn(0,0), 
                                      speed7);
    
    public Blocks block8 = new Blocks(new Posn(0,0), 
                                      speed8);
    
    public Blocks block9 = new Blocks(new Posn(0,0), 
                                      speed9);
    
    public Blocks[] blockArray = {block0, block1, block2, block3, block4, block5,
                                block6, block7, block8, block9};
   
    
    public FallingBlocksWorld() {
        this(5); 
    }
    
    // The method below is called on to create the world when the game starts.
    // A for loop goes through blockArray and initializes each block so it is 
    // ready to start falling from the top of the screen when gameplay starts.
    
    public FallingBlocksWorld(int speed) {
        Blocks[] temp = blockArray;
        int arrayLength = blockArray.length;
        for (int i = 0; i < arrayLength-1; i++) {
            temp[i].initialize();
        }
        this.user = new User(new Posn(250,475), boardWidth, boardHeight);
        this.blockArray = temp;
        this.speed = speed;
        this.score = 0;
        this.gameOver = false;
    }
    
    // The method below is called on by onTick() to return the updated world.
    
    public FallingBlocksWorld(User user, Blocks[] blockArray, int speed, 
            int score, boolean gameOver) {
        this.user = user;
        this.blockArray = blockArray;
        this.speed = speed;
        this.score = score;
        this.gameOver = gameOver;
    }
     
    // During onTick(), a for loop goes through blockArray and checks to see
    // if any if the blocks have hit the user.  If a given block has not, the for
    // loop then checks to see if it needs to be initialized again.  If that is
    // also not the case, then the block has the fall() method called on it and
    // it is moved down the playing field.
    
    public World onTick() {
        Blocks[] temp = blockArray;
        int arrayLength = blockArray.length;     
        for (int i = 0; i < arrayLength-1; i++) {
            if (temp[i].hitUserHuh(user)) {
                gameOver = true;
                break;
            }
            else if (temp[i].isDeadHuh()) {
                temp[i].initialize();
            } else
                temp[i].fall();
        }
        blockArray = temp;
        return new FallingBlocksWorld(user, blockArray, speed, score+10, gameOver);
    }
    
    // onKeyEvent moves the user block either left or right by calling the move()
    // method in the user class.  If a key event is not either left or right, 
    // nothing happens.
    
    public World onKeyEvent(String direction) {
        if (direction.equals("left") || (direction.equals("right"))) {
            return new FallingBlocksWorld(user.move(direction), blockArray, speed, score, 
                    gameOver);
        } else {
            return this;
        }
    }
    
    // If the boolean variable gameOver is assigned "true" during onTick, the
    // worldends() method will end the game and display a message.
    
        public WorldEnd worldEnds() {
        if (gameOver) {
            return new WorldEnd(true, new OverlayImages(this.makeImage(),
                    new TextImage(new Posn(boardWidth / 2, boardHeight / 2),
                            ("Better luck next time, pal. Final Score: " + this.score),
                            13,
                            new White())));
        } else {
            return new WorldEnd(false, this.makeImage());
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
                new Posn(250,20),
                "Your Score: " + score,
                new Black());
    }
    
    public WorldImage yellowBox() {
        return new RectangleImage(
                                  new Posn(250, 15),
                                  500,
                                  30,
                                  new Yellow());
    }
    
    public WorldImage makeBlocksImage() {
        return new OverlayImages(blockArray[0].drawImage(),
                                 new OverlayImages(blockArray[1].drawImage(),
                                 new OverlayImages(blockArray[2].drawImage(),
                                 new OverlayImages(blockArray[3].drawImage(),
                                 new OverlayImages(blockArray[4].drawImage(),
                                 new OverlayImages(blockArray[5].drawImage(),
                                 new OverlayImages(blockArray[6].drawImage(),
                                 new OverlayImages(blockArray[7].drawImage(),
                                 new OverlayImages(blockArray[8].drawImage(),
                                 blockArray[9].drawImage())))))))));
                
    }
    
    public WorldImage makeImage() {
        return new OverlayImages(screen(), new OverlayImages(makeBlocksImage(),
                                           new OverlayImages(user.drawImage(),
                                           new OverlayImages(yellowBox(),
                                           scoreBox())))); 
    }
     
    public static void main(String[] args) { 

//        Tester.runReport(new Testing(), false, false);
        
        FallingBlocksWorld theWorld = new FallingBlocksWorld();
        theWorld.bigBang(boardWidth,boardHeight,0.15);    
        
    }
   
}
