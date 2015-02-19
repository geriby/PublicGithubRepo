package com.mygdx.game.units.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.*;
import com.mygdx.game.units.heros.EnergyBall;

import java.util.List;
import java.util.Random;

/**
 * Created by Ostap on 28.10.2014.
 */
public class Cell extends com.mygdx.game.units.Character {

    private final float speed = 0.5f;
    private List<Texture> cellImages;
    private Animation animation, animationLeft;
    private EnergyBall energyBall;
    private int index = 0;
    private Texture img;
    private TextureRegion[] frames;
    private TextureRegion currentFrame;
    private Random rn;

    public Cell(){
        rn = new Random();
        img = new Texture("images/cell/goku_enemy_walk.png");
        int index =0;
        frames = new TextureRegion[14];
        TextureRegion[][] tmp = TextureRegion.split(img,img.getWidth()/7 , img.getHeight()/2);
        for(int j = 0; j < 2 ; j++){
            for(int i = 0 ; i < 7; i++){
                frames[index++] = tmp[j][i];
            }
        }
        animation = new Animation(1.9f, frames);
        currentFrame = animation.getKeyFrame(1);

        this.x = rn.nextInt(2) == 1 ? DragonBall.WIDTH : 0;
        this.y = DragonBall.HEIGHT / 10;
    }

    public void update(float heroPosition){
          if(heroPosition > x){
              x += speed;
              index = index > 10 ? 0 : ++index;
              currentFrame = animation.getKeyFrame(index);
          }else{
              if(heroPosition <x){
                  x -= speed;
                  index = index < 12 || index > 22  ? 12 : ++index;
                  currentFrame = animation.getKeyFrame(index);
              }

          }
    }

    public void draw(SpriteBatch batch){
        batch.begin();
        batch.draw(currentFrame, x, y, Gdx.graphics.getHeight()/5,  Gdx.graphics.getHeight()/4);
        batch.end();
    }
}
