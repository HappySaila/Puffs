package com.puffs.game.Tools;

import com.puffs.game.GameObjects.Blocks.Block;
import com.puffs.game.GameObjects.Blocks.Grass;
import com.puffs.game.GameObjects.Board;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by happysaila on 2016/10/17.
 * will create a path of flow for a bullet to travel
 */
public class Finder {

    public static Stack<Block> calculatePath(Block start, Block end, Board board){
        ArrayList<Block> closed = new ArrayList<Block>();
        ArrayList<Block> open = new ArrayList<Block>();
        Block startBlock = start;
        Block endBlock = end;
        Block currentBlock = startBlock;

        open.add(currentBlock);

        while(currentBlock!=endBlock){
//          get adjacent nodes
            Block above = board.getAbove(currentBlock);
            Block below = board.getBelow(currentBlock);
            Block left = board.getLeft(currentBlock);
            Block right = board.getRight(currentBlock);

            ArrayList<Block> adjacentBlocks = new ArrayList<Block>(4);
            adjacentBlocks.add(above);
            adjacentBlocks.add(below);
            adjacentBlocks.add(left);
            adjacentBlocks.add(right);

            for (Block block: adjacentBlocks) {
                if (board.isWalkable(block) && block.isOpen()){
//                block can be added to open list. f,g + h values can be calculated
                    int g = getG(block);//will calculate g of blocks current position
                    if (g < block.getG() || block.getG()==0){
//                    block must be update
                        int h = getH(block, endBlock);
                        int f = h+g;
                        block.setNode(f,g,h,currentBlock);
                    }
                    if (!open.contains(block)){
                        open.add(block);
                    }
                }
            }
//          add current node into closed list and remove it from open list
            closed.add(currentBlock);
            open.remove(currentBlock);
//          choose lowest f value, check if this block is the destionation block
            currentBlock = getLowestF(open);
        }
        //formulate stack
        return formulateStack(endBlock);



    }

    private static Stack<Block> formulateStack(Block block){
        Stack<Block> path = new Stack<Block>();
        while(block.hasParent()){
            path.push(block);
            block = block.getParent();
        }
        path.push(block);
        return path;
    }

    private static int getH(Block initialBlock, Block endBlock){
        int x = Math.abs(initialBlock.getPosition().x - endBlock.getPosition().x);
        int y = Math.abs(initialBlock.getPosition().y - endBlock.getPosition().y);

        return (x+y)*10;
    }

    private static int getG(Block block){
        int g = 0;
        while(block.hasParent()){
            block = block.getParent();
            g+=10;
        }
        return g;
    }

    private static Block getLowestF(ArrayList<Block> open){
        Block lowest = open.get(0);
        for (Block block:open) {
            if (block.getF() < lowest.getF()){
                lowest = block;
            }
        }
        return lowest;
    }

}
