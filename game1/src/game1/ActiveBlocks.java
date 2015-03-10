package game1;

import java.util.Random;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class ActiveBlocks {
    
    public int boardWidth;
    public int boardHeight;
    
    public Blocks[] blockArray;
    
    public ActiveBlocks(Blocks[] blockArray) {
        this.blockArray = blockArray;
    }
    
    public ActiveBlocks initialize() {
        int arrayLength = blockArray.length;
        for (int i = 0; i < arrayLength; i++) {
            blockArray[i] = new Blocks(
                                       new Posn(((int)Math.random()*450)+25,0),
                                       this.boardWidth,
                                       this.boardHeight,
                                       Blocks.blockSpeed);
        }
        return new ActiveBlocks(blockArray);
    }
    
    public ActiveBlocks fallBlocks() {
        int arrayLength= blockArray.length;
        for (int i = 0; i < arrayLength; i++) {
            if (blockArray[i].isDeadHuh()) {
                blockArray[i] = new Blocks(
                                     new Posn(((int)Math.random()*450)+25,0),
                                     this.boardWidth,
                                     this.boardHeight,
                                     Blocks.blockSpeed);
            } else
                blockArray[i].fall();
        }
        return new ActiveBlocks(blockArray);
    }
    
    public WorldImage drawImage() {
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
    
    
    
    
    
    
    
    
    
//    public ActiveBlocks(LinkedList<Blocks> activeBlocks) {
//        this.activeBlocks = activeBlocks;
//    }
//    
//    public LinkedList<Blocks> createNewList() {
//        LinkedList<Blocks> temp = new LinkedList<Blocks>();
//        temp.addFirst(new Blocks
//            (new Posn(((int) (Math.random()*450)+25),0),
//            boardWidth,
//            boardHeight));
//        activeBlocks = temp;  
//        return activeBlocks;
//    } 
//    
//    public LinkedList<Blocks> updateList() {
//        while (activeBlocks.iterator().hasNext()) {
//            Blocks block = activeBlocks.iterator().next();
//            block.fall();
//        }
//        activeBlocks.push(new Blocks
//            (new Posn(((int) (Math.random()*450)+25),0),
//            boardWidth,
//            boardHeight));
//        if (activeBlocks.peekLast().isDeadHuh()) {
//            activeBlocks.removeLast();
//        } else {
//            return activeBlocks;
//        }
//        return activeBlocks;
//    }
//    
//    public Blocks returnThreat() {
//        return activeBlocks.getLast();
//    }
//    
//    public boolean isEmpty() {
//        return activeBlocks.isEmpty();
//    }
//    
//    public Blocks get(int i) {
//        return activeBlocks.get(i);
//    }
    
}
    

    
                
    

