package game1;

import java.util.Random;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class Blocks {
    
    public Posn posn;
    
    public int width = 50;
    public int height = 50;
    
    public int blockSpeed;
    
    public IColor color = new Blue();
    
    // All falling blocks will be 50 pixels x 50 pixels and blue.
    
    public Blocks(Posn posn, int blockSpeed) {
        this.posn = posn;
        this.blockSpeed = blockSpeed;
    }
    
    // initialize() moves a block to a random x coordinate at the top of 
    // the playing field.
    
    public Blocks initialize() {
        posn = new Posn((int)(Math.random()*450+25), 0);
        return new Blocks(posn, blockSpeed);
    }
    
    // fall() moves a given block down the playing field at the block's
    // assigned speed.
    
    public Blocks fall() {
        posn = new Posn((this.posn.x), this.posn.y + blockSpeed);
        return new Blocks(posn, blockSpeed);
    }
    
    // If a block dissapears below the bottom of the playing field, it becomes
    // "dead".  This method allows the methods in FallingBlocksWorld to know
    // when a block needs to be initialized again.
    
    public boolean isDeadHuh() {
        if (posn.y > 525) {
            return true;
        } else {
            return false;
        }
    }
    
    // If a block is within 50 pixels (both vertically and horizontally) of 
    // a user block, the user has been hit.
    
    public boolean hitUserHuh(User user) {
        if ((Math.abs(this.posn.y - user.posn.y) <= 50)
                &&
                (Math.abs(this.posn.x - user.posn.x) <= 50))
            return true;
        else return false;   
    }
    
    public WorldImage drawImage() {
        return new RectangleImage(this.posn, this.width, this.height, this.color);
    }
    
    public String toString() {
        return ("Block speed: " + blockSpeed + " x pos: " + 
                this.posn.x + " y pos: " + this.posn.y);
    }
    
}
