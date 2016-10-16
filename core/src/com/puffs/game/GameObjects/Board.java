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

    public Block getPosition(Position position){
        return grid[position.y][position.x];
    }

    public void highLightGreen(int x, int y){
        game.sb.draw(green, boardStartPosition.x + x*blockSize, boardStartPosition.y + y*blockSize,
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

//    endregion
}
