package com.puffs.game.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.puffs.game.GameObjects.Blocks.Block;
import com.puffs.game.GameObjects.Blocks.Grass;
import com.puffs.game.GameObjects.Blocks.GrassOrb;
import com.puffs.game.GameObjects.Blocks.Orb;
import com.puffs.game.PuffsDriver;
import com.puffs.game.Tools.Position;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by happysaila on 2016/10/16.
 * responsible for controlling the layout of the game.
 * creates the grid so objects can be placed into them.
 */
public class Board {
    //region variable
    private Block[][] grid;
    private int length;
    private int width;
    private int blockSize;
    private Position boardStartPosition;
    PuffsDriver game;

    float delta;
    ArrayList<Orb> orbs;

//    highLight textures
    Texture green;
    Texture red;



    //endregion

    //region constructors
    public Board(int length, int width, PuffsDriver game) {
        this.game = game;
        this.length = length;
        this.width = width;
        grid = new Block[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Block(new Position(j,i));
            }
        }
        blockSize = (PuffsDriver.SCR_H-50)/length;

        boardStartPosition = new Position(PuffsDriver.SCR_W/2 - (blockSize/2 * width),
                PuffsDriver.SCR_H/2 - (blockSize/2 * length));

//        textures
        green = new Texture("Blocks/green.png");
        red = new Texture("Blocks/red.png");

        orbs = new ArrayList<Orb>();
    }
    //endregion

    //region methods
    public void addOrb(Orb orb){
        orbs.add(orb);
    }

    public boolean checkBlockEmpty(int x, int y){
//        will return true if the current block is taken
        Position position = new Position(x,y);
        if (getPosition(position).getBlockType().getClass() == Grass.class){
//            the current position has a block
            return true;
        }else {
            return false;
        }
    }

    public void createOrb(int x, int y){
        boolean canCreate = true;
        try {
            for (int i = x-1; i < x+2; i++) {
                for (int j = y-1; j < y+2; j++) {
                    if (!checkBlockEmpty(i, j)){
                        canCreate = false;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Board cannot fit the orb exception");
        }
        if (canCreate){
//            create orb
            orbs.add(new Orb(new Position(x,y), this));
            for (int i = x-1; i < x+2; i++) {
                for (int j = y-1; j < y+2; j++) {
                    grid[j][i].setBlockType(new GrassOrb());
                }
            }
            System.out.println("orb added");
        }else{
            System.out.println("Orb can only be placed on grass exception");
        }
    }

    public Position getBoardStartPosition(){
        return boardStartPosition;
    }

    public void highLightGreen(int x, int y){
        game.sb.draw(green, boardStartPosition.x + x*blockSize, boardStartPosition.y + y*blockSize,
                blockSize, blockSize);
    }

    public void highLightRed(int x, int y){
        game.sb.draw(red, boardStartPosition.x + x*blockSize, boardStartPosition.y + y*blockSize,
                blockSize, blockSize);
    }

    public void build(int x, int y){
        grid[y][x].upgrade();
    }

    public void render(SpriteBatch sb){
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                Texture temp = grid[j][i].getTexture();
                sb.draw(temp, boardStartPosition.x + (i * blockSize),
                        boardStartPosition.y + (j * blockSize),
                        blockSize / 2, blockSize / 2,
                        blockSize, blockSize,
                        1, 1,
                        90 * (j - i),//rotation
                        0, 0, temp.getWidth(), temp.getHeight(), false, false);
            }
        }
        renderOrbs(sb);
    }

    private void renderOrbs(SpriteBatch sb){
        for (Orb o:orbs) {
            o.render(sb);
        }
    }

    public void renderPath(Stack<Block> path){
        for (Block block:path) {
            block.upgrade();
        }
    }

    public boolean isWalkable(Block block){
//        will return true if bullets can be fired on a square at the position parameter
        return (block.getBlockType().getClass() == Grass.class);
    }

    //endregion

//    region getters and setters
    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public Block getAbove(Block block){
//        will return the block above block, will return null if reached the end of the board
        Block above = null;
        try {
            above = getPosition(new Position(block.getPosition().x, block.getPosition().y+1));
        } catch (Exception e) {
            above = null;
        }
        return above;
    }

    public Block getBelow(Block block) {
//        will return the block below block parameter
        Block below = null;
        try {
            below = getPosition(new Position(block.getPosition().x, block.getPosition().y-1));
        } catch (Exception e) {
            below = null;
        }
        return below;
    }

    public Block getLeft(Block block){
//        will return the block left block parameter
        Block left = null;
        try {
            left = getPosition(new Position(block.getPosition().x-1, block.getPosition().y));
        } catch (Exception e) {
            left = null;
        }
        return left;
    }

    public Block getRight(Block block){
//        will return the block left block parameter
        Block right = null;
        try {
            right = getPosition(new Position(block.getPosition().x+1, block.getPosition().y));
        } catch (Exception e) {
            right = null;
        }
        return right;
    }

    public Block getPosition(Position position){
        return grid[position.y][position.x];
    }

//    endregion
}
