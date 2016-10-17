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
    Node node;


    public Block(Position position) {
        this.position = position;
        blockType = new Grass();
        node = new Node(this);
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

    public Block getParent(){
        return node.parent;
    }

    public Position getPosition(){
        return position;
    }

    public int getF(){
        return node.f;
    }
    public int getG(){
        return node.g;
    }

    public boolean hasParent(){
        return node.parent !=null;
    }

    public boolean isOpen(){
        return !node.closed;
    }

    public void setBlockType(BlockType blockType){
        this.blockType = blockType;
    }

    public void setNode(int f, int g, int h, Block parent){
        node.f = f;
        node.g = g;
        node.h = h;
        node.parent = parent;
    }

//    endregion
}
/**
 * Created by happysaila on 2016/10/17.
 * be used to represent f,g and h values
 */
class Node {
    public int f;
    public int g;
    public int h;
    public Block parent;
    public Block block; //used to determine the position of the node in the array
    public boolean closed = false; //if block is closed, the block will not be checked

    public Node(Block block) {
        this.block = block;
    }
}
