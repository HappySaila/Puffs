package com.puffs.game.GameObjects.Blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.puffs.game.GameObjects.Board;
import com.puffs.game.Tools.Position;

/**
 * Created by happysaila on 2016/10/16.
 */
public class Orb {
    Position position;
    Texture texture;
    Board board;
    float delta;

    public Orb(Position position, Board board) {
        this.board = board;
        this.position = position;
        texture = new Texture("Blocks/orb.png");
    }

    public void render(SpriteBatch sb){
        delta += Gdx.graphics.getDeltaTime();
        sb.draw(texture, board.getBoardStartPosition().x + ((position.x-1) * board.getBlockSize()),
                board.getBoardStartPosition().y + ((position.y-1) * board.getBlockSize()),
                board.getBlockSize()*3 / 2, board.getBlockSize()*3 / 2,
                board.getBlockSize()*3, board.getBlockSize()*3,
                1, 1,
                delta,//rotation
                0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }
}
