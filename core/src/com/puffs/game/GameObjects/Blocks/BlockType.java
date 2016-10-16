package com.puffs.game.GameObjects.Blocks;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by happysaila on 2016/10/16.
 */
public class BlockType {
    int defense;
    int health;
    int cost;
    Texture texture;

    public Texture getTexture(){
        return texture;
    }
}
