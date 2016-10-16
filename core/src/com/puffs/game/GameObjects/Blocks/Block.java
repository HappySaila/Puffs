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

    }
}
