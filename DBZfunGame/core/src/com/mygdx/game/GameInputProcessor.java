package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

/**
 * Created by Ostap on 13.10.2014.
 */
public class GameInputProcessor extends InputAdapter {

    public boolean keyDown(int k){
        if(k == Input.Keys.UP)
            GameKeys.setKeys(GameKeys.UP, true);
        if(k == Input.Keys.LEFT)
            GameKeys.setKeys(GameKeys.LEFT, true);
        if(k == Input.Keys.RIGHT)
            GameKeys.setKeys(GameKeys.RIGHT, true);
        if(k == Input.Keys.DOWN)
            GameKeys.setKeys(GameKeys.DOWN, true);
        return true;
    }

    public boolean keyUp(int k){
        if(k == Input.Keys.UP)
            GameKeys.setKeys(GameKeys.UP, false);
        if(k == Input.Keys.LEFT)
            GameKeys.setKeys(GameKeys.LEFT, false);
        if(k == Input.Keys.RIGHT)
            GameKeys.setKeys(GameKeys.RIGHT, false);
        if(k == Input.Keys.DOWN)
            GameKeys.setKeys(GameKeys.DOWN, false);
        return true;
    }
}
