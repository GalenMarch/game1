package game1;

import static game1.FallingBlocksWorld.boardHeight;
import static game1.FallingBlocksWorld.boardWidth;
import tester.*;

import java.util.Random;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class Testing {
   
    public static final int boardWidth = 500;
    public static final int boardHeight = 500;
    
    Blocks testBlock = new Blocks(new Posn(250,250),20);
    Blocks deadBlock = new Blocks(new Posn(250, 526),20);
    Blocks initializedBlock = new Blocks(new Posn(250,0),20);
    Blocks blockToRight = new Blocks(new Posn(200,475),20);
    Blocks blockToLeft = new Blocks( new Posn(300,475),20);
    Blocks blockAbove = new Blocks(new Posn(250,425),20);
    
    User user = new User(new Posn(250,475), boardWidth, boardHeight);
    User user2left = new User(new Posn(240,475), boardWidth, boardHeight);
    User user2right = new User(new Posn(260,475), boardWidth, boardHeight);
    User userAtRightEdge = new User(new Posn(475,475), boardWidth, boardHeight);
    User userAtLeftEdge = new User (new Posn(25,475), boardWidth, boardHeight);
    User userNearRightEdge = new User (new Posn(465,475), boardWidth, boardHeight);
    User userNearLeftEdge = new User (new Posn(35,475), boardWidth, boardHeight);
   
    // Testing methods in User class
    
    public boolean testUserMove(Tester tester) {
        return
                tester.checkExpect(this.user.move("left"),
                        this.user2left, "test moving the user to the left" + " /n") &&
                tester.checkExpect(this.user.move("right"),
                        this.user2right, "test moving the user to the right" + " /n");
                
    }
    
    public boolean testUserAtEdge(Tester tester) {
        return
        tester.checkExpect(this.userAtRightEdge.move("right"),
                this.userAtRightEdge, "test moving the user to the right at right edge" + " /n") &&
        tester.checkExpect(this.userAtRightEdge.move("left"),
                this.userNearRightEdge, "text moving the user to the left at right edge" + " /n") &&
        tester.checkExpect(this.userAtLeftEdge.move("left"),
                this.userAtLeftEdge, "test moving the user to the left at left edge" + " /n") &&
        tester.checkExpect(this.userAtLeftEdge.move("right"),
                this.userNearLeftEdge, "test moving the user to the right at left edge" + " /n");
    }
    
    // Testing methods in Blocks class
    
    public boolean testIsDeadHuh(Tester tester) {
        return
                tester.checkExpect(this.deadBlock.isDeadHuh(),
                        true, "testing isDeadHuh method") &&
                tester.checkExpect(this.testBlock.isDeadHuh(),
                        false, "testing isDeadHuh method with live block");
        
    }
    
    public boolean testInitialize(Tester tester) {
        return
                tester.checkExpect(this.testBlock.initialize().posn.y,
                        0, "testing initialze");
    }
    
    public boolean testHitUserHuh(Tester tester) {
        return
                tester.checkExpect(this.blockToRight.hitUserHuh(user),
                        true, "testing hitUserHuh with block 50 to right of user") &&
                tester.checkExpect(this.blockToLeft.hitUserHuh(user),
                        true, "testing hitUserHuh with block 50 to left of user") &&
                tester.checkExpect(this.blockAbove.hitUserHuh(user),
                        true, "testing hitUserHuh with block 50 above") &&
                tester.checkExpect(this.initializedBlock.hitUserHuh(user),
                        false, "testing hitUserHuh with distant block");
        
    }
    
    // Testing FallingBlocksWorld
    
    int speed;
    boolean gameOver;
    
    User newUser = new User(new Posn(25,475), boardWidth, boardHeight);
    
    Blocks[] keyEventArray = {testBlock};
    Blocks[] onTickArrayOne = {testBlock, deadBlock};
    
    FallingBlocksWorld keyEventWorld = new FallingBlocksWorld(user, 
            keyEventArray, speed, 0, gameOver);
    FallingBlocksWorld keyEventWorldLeft = new FallingBlocksWorld(user2left,
            keyEventArray, speed, 0, gameOver);
    FallingBlocksWorld keyEventWorldRight = new FallingBlocksWorld(user2right,
            keyEventArray, speed, 0, gameOver);
    
    FallingBlocksWorld onTickWorldOne = new FallingBlocksWorld(newUser,
             onTickArrayOne, speed, 0, gameOver);
    World onTickWorldTwo = onTickWorldOne.onTick();
    
    public boolean testKeyEvent(Tester tester) {
        return
                tester.checkExpect(this.keyEventWorld.onKeyEvent("right"),
                        this.keyEventWorldRight, "Testing key event when right") &&
                tester.checkExpect(this.keyEventWorld.onKeyEvent("left"),
                        this.keyEventWorldLeft, "Testing key event when left") &&
                tester.checkExpect(this.keyEventWorld.onKeyEvent("up"),
                        this.keyEventWorld, "testing key event with invalid input");
    }
 
    public boolean testOnTick(Tester tester) {
        return
                tester.checkExpect(this.onTickWorldOne.onTick(),
                        this.onTickWorldTwo, "testing onTick");
    }   
    
 
    
    
    
    
   
}
