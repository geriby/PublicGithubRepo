package com.mygdx.game.units.heros;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Ostap on 29.10.2014.
 */
public class EnergyBall extends com.mygdx.game.units.Character {

    private final int speed = 2;
    private Texture img;
    private Boolean right;
    private Boolean stop = true;

    public EnergyBall(){
        img = new Texture("images/goku/ball.png");
        this.x = -1000;
        this.y = Gdx.graphics.getHeight()/7;
    }

    public void update(){
        if(!stop)
           x = right ? x + speed : x - speed;
    }

    public void draw(SpriteBatch batch){
        batch.begin();
        batch.draw(img, x, y, Gdx.graphics.getHeight()/10, Gdx.graphics.getHeight()/10);
        batch.end();
    }

    public void setInvisible(){
         x = -10000;
         stop = true;
    }

    public void setVisible(float x, Boolean right){
        this.x = x;
        this.right = right;
        stop =false;
    }

}


