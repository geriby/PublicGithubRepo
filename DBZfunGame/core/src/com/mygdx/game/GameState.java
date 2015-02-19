package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

/**
 * Created by Ostap on 13.10.2014.
 */
public abstract class GameState {
    protected GameStateManager gsm;

    protected GameState(GameStateManager gsm){
        this.gsm = gsm;
    }

    public abstract  void init();
    public abstract  void  update(Touchpad touchpad, Boolean kamehama);
    public abstract void draw(Touchpad touchpad);
//    public abstract void handleInput();
    public abstract void dispose();


}
