package com.puffs.game.Tools;

/**
 * Created by happysaila on 2016/10/16.
 * holds an x and a y coordinate.
 */
public class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Position position){
        return this.x == position.x && this.y == position.y;
    }


}
