package com.puffs.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.puffs.game.Screens.GameScreen;

import java.util.Stack;

public class PuffsDriver extends Game {
	public SpriteBatch sb;
	public static final int SCR_W = 1200;
	public static final int SCR_H = 600;
	public static Texture node;

	Stack<Screen> screens;

	@Override
	public void create () {
		sb = new SpriteBatch();
		screens = new Stack<Screen>();
		AddScreen(new GameScreen(this));
		node = new Texture("node.png");
	}

	public void Dispose(){
		if (screens.size()>1){
			screens.pop();
		}
		setScreen(screens.peek());
	}

	public void AddScreen(Screen screen){
		screens.push(screen);
		setScreen(screens.peek());
	}

	@Override
	public void render () {
		super.render();
	}
}
