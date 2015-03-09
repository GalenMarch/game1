package game1;

import java.util.Random;
import java.util.LinkedList;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class ActiveBlocks {
    
    public int boardWidth;
    public int boardHeight;
    
    public LinkedList<Blocks> activeBlocks;
    
    public ActiveBlocks(LinkedList<Blocks> activeBlocks) {
        this.activeBlocks = activeBlocks;
    }
    
    public LinkedList<Blocks> createNewList() {
        LinkedList<Blocks> temp = new LinkedList<Blocks>();
        temp.addFirst(new Blocks
            (new Posn(((int) (Math.random()*450)+25),0),
            boardWidth,
            boardHeight));
        activeBlocks = temp;  
        return activeBlocks;
    } 
    
    public LinkedList<Blocks> updateList() {
        while (activeBlocks.iterator().hasNext()) {
            Blocks block = activeBlocks.iterator().next();
            block.fall();
        }
        activeBlocks.push(new Blocks
            (new Posn(((int) (Math.random()*450)+25),0),
            boardWidth,
            boardHeight));
        if (activeBlocks.peekLast().isDeadHuh()) {
            activeBlocks.removeLast();
        } else {
            return activeBlocks;
        }
        return activeBlocks;
    }
    
    public WorldImage drawImage() {
         while (activeBlocks.iterator().hasNext()) {
             Blocks block = activeBlocks.iterator().next();
             block.drawImage();              
         }
    }
                
    
}
