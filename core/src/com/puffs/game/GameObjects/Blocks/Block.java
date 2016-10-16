package com.puffs.game.GameObjects.Blocks;

import com.badlogic.gdx.graphics.Texture;
import com.puffs.game.Tools.Position;

/**
 * Created by happysaila on 2016/10/16.
 * any game object that can be placed onto the board
 */
public class Block {
    Position position;
    BlockType blockType;


    public Block(Position position) {
        this.position = position;
        blockType = new Grass();
    }

    public Texture getTexture(){
        return blockType.getTexture();
    }

    public void upgrade(){
        if (blockType.getClass() == Grass.class){
            blockType = new Wood();
        }else if(blockType.getClass() == Wood.class){
            blockType = new Brick();
        }else if(blockType.getClass() == Brick.class){
            blockType = new Concrete();
        }else if(blockType.getClass() == Concrete.class){
            blockType = new Metal();
        }
    }

//    region getters
    public BlockType getBlockType(){
        return blockType;
    }

    public void setBlockType(BlockType blockType){
        this.blockType = blockType;
    }
//    endregion
}
