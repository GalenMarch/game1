package game1;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class User { 
    
    public Posn posn;
    
    public int boardWidth;
    public int boardHeight;
    
    // The variables below specify the constants of the user block.  The user
    // will control a 50 pixel X 50 pixel green block that always moves at 
    // a speed of 10 pixels per keystroke.
    
    public int width = 50; 
    public int height = 50;
    
    public int userSpeed = 10;
    
    public IColor color = new Green();
    
    
    public User(Posn posn, int boardWidth, int boardHeight) {
        this.posn = posn;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }
    
    // The two methods below are used in the method move() to keep the 
    // edges of the block from going out of bounds.
    
    public boolean canMoveRightHuh() {
        if (posn.x >= boardWidth-25) {
            return false;
        } else {
            return true;
        }
    } 
    
    public boolean canMoveLeftHuh() {
        if (posn.x <= 25) { 
            return false;
        } else {
            return true;
        }
    }
    
    // If the user tries to move right or left when it is at the respective 
    // edge of the playing field, it will just simply be returned at its 
    // current position.
    
    public User move(String direction) {
        if (direction.equals("left") && (this.canMoveLeftHuh() == true)) {
            return new User(new Posn(this.posn.x - userSpeed, this.posn.y),
            this.boardWidth, this.boardHeight);
        } else if (direction.equals("left") && (this.canMoveLeftHuh() == false)) {
            return this;
        } else if (direction.equals("right") && (this.canMoveRightHuh() == true)) {
            return new User(new Posn(this.posn.x + userSpeed, this.posn.y),
            this.boardWidth, this.boardHeight);
        } else if (direction.equals("right") && (this.canMoveRightHuh() == false)) {
            return this;
        } else return this;
    } 
           
    public WorldImage drawImage() {
        return new RectangleImage(this.posn, this.width, this.height, this.color);
    }
    
    public String toString() {
        return ("X pos: " + this.posn.x + " y pos: " + this.posn.y);
    }
}
