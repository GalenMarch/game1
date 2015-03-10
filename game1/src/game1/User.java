package game1;

import java.util.Random;
import java.util.LinkedList;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class User { 
    
    public Posn posn;
    
    public int boardWidth;
    public int boardHeight;
    
    public int width = 50; 
    public int height = 50;
    
    public int userSpeed = 10;
    
    public IColor color = new Green();
    
    
    public User(Posn posn, int boardWidth, int boardHeight) {
        this.posn = posn;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }
    
        
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
}
