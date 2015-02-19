package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

/**
 * Created by Ostap on 13.10.2014.
 */
public class GameStateManager {
    private GameState gameState;

    public static final int MENU = 0;
    public static final int PLAY = 893746;

    public GameStateManager() {
        setState(PLAY);
    }

    public void setState(int state) {
        if(gameState !=null) gameState.dispose();
        if (state == MENU) {

        }

        if (state == PLAY) {
            gameState = new PlayState(this);
        }

    }

    public void update(Touchpad touchpad, Boolean kamehama) {
        gameState.update(touchpad, kamehama);
    }

    public void draw(Touchpad touchpad) {
        gameState.draw(touchpad);
    }


}
