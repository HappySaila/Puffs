package com.puffs.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.puffs.game.GameObjects.Board;
import com.puffs.game.PuffsDriver;
import com.puffs.game.Tools.ClickManager;
import com.puffs.game.Tools.Utils;

/**
 * Created by happysaila on 2016/10/16.
 */
public class GameScreen extends AbstractScreen {
//    region variables
    Board board;
    ClickManager clickManager;
//    endregion

//    region constructors
    public GameScreen(PuffsDriver game) {
        super(game);
        board = new Board(30, 30, game);
        clickManager = new ClickManager(board, game);
    }
//    endregion

//    region methods
    @Override
    public void render(float delta) {
        game.sb.begin();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        board.render(game.sb);
        update(delta);
        game.sb.end();
    }

    public void update(float delta){
        if (Utils.pressed(Input.Keys.Q)){
            System.exit(0);
        }
        clickManager.update(delta);
    }
//    endregion
}
