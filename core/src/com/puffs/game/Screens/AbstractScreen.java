package com.puffs.game.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.puffs.game.PuffsDriver;

/**
 * Created by happysaila on 2016/10/16.
 */
public abstract class AbstractScreen implements Screen {
    PuffsDriver game;
    public AbstractScreen(PuffsDriver game) {
        this.game = game;
    }

    @Override
    public abstract void render(float delta);
    public abstract void update(float delta);

    public void Dispose(){
        game.Dispose();
    }

    //region rubbish
    @Override
    public void show() {

    }



    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    //endregion
}
