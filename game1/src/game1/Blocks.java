package game1;

import java.util.Random;
import java.util.LinkedList;

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
    
    public int blockSpeed = 25;
    
    public IColor color = new Red();
    
    public Blocks(Posn posn, int boardWidth, int boardHeight) {
        this.posn = posn;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }
    
    public Blocks fall() {
        return new Blocks(new Posn((this.posn.x), this.posn.y - blockSpeed),
                          this.boardWidth, this.boardHeight);
    }
    
    public boolean isDeadHuh() {
        if (posn.y <= 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public WorldImage drawImage() {
        return new RectangleImage(this.posn, this.width, this.height, this.color);
    }
    
}
