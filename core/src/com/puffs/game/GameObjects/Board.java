package com.puffs.game.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.puffs.game.GameObjects.Blocks.Block;
import com.puffs.game.GameObjects.Blocks.Grass;
import com.puffs.game.PuffsDriver;
import com.puffs.game.Tools.Position;

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
    }
    //endregion

    //regoin methods
    public boolean checkBlock(Position position){
//        will return true if the current block is taken
        if (getPosition(position)!=null){
//            the current position has a block
            return true;
        }else {
            return false;
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
        grid[x][y].upgrade();
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
                        0, 0, 30, 30, false, false);
            }
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
