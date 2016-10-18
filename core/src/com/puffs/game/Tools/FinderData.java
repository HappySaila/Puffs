package com.puffs.game.Tools;

import com.puffs.game.GameObjects.Blocks.Block;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by happysaila on 2016/10/18.
 * returns all  blocks involved in path finding.
 * used to clear all blocks stored data so that a new search can be done
 */
public class FinderData {
    ArrayList<Block> openList;
    ArrayList<Block> closedList;
    Stack<Block> path;

    public FinderData(ArrayList<Block> openList, ArrayList<Block> closedList, Stack<Block> path) {
        this.openList = openList;
        this.closedList = closedList;
        this.path = path;
    }

    public void clearData(){
        for (Block b:openList) {
            b.resetNode();
        }
        for (Block b:closedList) {
            b.resetNode();
        }
        for (Block b:path) {
            b.resetNode();
        }
    }

    public ArrayList<Block> getOpenList() {
        return openList;
    }

    public ArrayList<Block> getClosedList() {
        return closedList;
    }

    public Stack<Block> getPath() {
        return path;
    }
}
