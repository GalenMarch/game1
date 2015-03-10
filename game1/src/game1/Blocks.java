package game1;

import java.util.Random;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class Blocks {
    
    public Posn posn;
    
    public int boardWidth;
    public int boardHeight;
    
    public int width = 50;
    public int height = 50;
    
    public static int blockSpeed;
    
    public IColor color = new Red();
    
    public Blocks(Posn posn, int boardWidth, int boardHeight, int blockSpeed) {
        this.posn = posn;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.blockSpeed = blockSpeed;
    }
    
    public Blocks fall() {
        return new Blocks(new Posn((this.posn.x), this.posn.y + blockSpeed),
                          this.boardWidth, this.boardHeight, this.blockSpeed);
    }
    
    public boolean isDeadHuh() {
        if (posn.y >= 550) {
            return true;
        } else {
            return false;
        }
    }
    
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
    
}
