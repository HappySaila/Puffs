package com.puffs.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.puffs.game.GameObjects.Blocks.Block;
import com.puffs.game.GameObjects.Blocks.Grass;
import com.puffs.game.GameObjects.Board;
import com.puffs.game.PuffsDriver;

import java.util.Stack;

/**
 * Created by happysaila on 2016/10/16.
 * will manage which block was clicked in the grid
 */
public class ClickManager {
    Board board;
    PuffsDriver game;
    Position gridStart;
    Position gridEnd;
    float timer;
    float clickDelay;
    Position clickPosition;

//    testing path finding
    Block start;
    Block end;
    Stack<Block> path;
    public ClickManager(Board board, PuffsDriver game) {
        this.game = game;
        this.board = board;
        clickDelay = 0.25f;
        clickPosition = new Position(0,0);
        gridStart = board.getBoardStartPosition();
        gridEnd = new Position(gridStart.x+(board.getWidth() * board.getBlockSize()),
                gridStart.y+(board.getLength() * board.getBlockSize()));
    }

    public void update(float delta){
        timer+=delta;
        int x = 0;//grid array positions
        int y = 0;
        clickPosition.x = Gdx.input.getX();
        clickPosition.y = PuffsDriver.SCR_H - Gdx.input.getY();

        if (clickPosition.x > gridStart.x && clickPosition.x < gridEnd.x &&
                clickPosition.y > gridStart.y && clickPosition.y < gridEnd.y){
            x = (clickPosition.x - gridStart.x)/board.getBlockSize();
            y = (clickPosition.y - gridStart.y)/board.getBlockSize();
            if (board.getPosition(new Position(x,y)).getBlockType().getClass() == Grass.class){
                board.highLightGreen(x,y);
            }else{
                board.highLightRed(x,y);
            }
        }
        if(timer>clickDelay){
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                timer = 0;
                board.build(x,y);
                //handle click output
            }
            if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
                timer = 0;
                board.createOrb(x,y);
                //handle click output
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.N)){
            start = board.getPosition(new Position(x,y));
            board.build(x,y);
            board.build(x,y);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.M)){
            end = board.getPosition(new Position(x,y));
            board.build(x,y);
            board.build(x,y);
            board.build(x,y);
            board.build(x,y);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.B)){
            path = Finder.calculatePath(start, end, board);
        }

        if (path!=null){
            board.renderPath(path);
        }
    }

}
