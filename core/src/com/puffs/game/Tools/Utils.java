package com.puffs.game.Tools;

import com.badlogic.gdx.Gdx;

import java.util.Random;

/**
 * Created by happysaila on 2016/10/16.
 * used to do common calculations
 */
public class Utils {
    public static int generate(int i){
//        will generate a random number between 0 and i-1
        Random rand = new Random();
        return rand.nextInt(i);
    }

    public static boolean pressed(int i){
        if (Gdx.input.isKeyJustPressed(i)){
            return true;
        }
        return false;
    }
}
