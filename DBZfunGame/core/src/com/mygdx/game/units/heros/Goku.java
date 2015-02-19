package com.mygdx.game.units.heros;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.mygdx.game.*;
import com.mygdx.game.units.enemies.Cell;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by Ostap on 15.10.2014.
 */


public class Goku extends com.mygdx.game.units.Character {
    private Animation animation, kameAnimation;
    private EnergyBall energyBall;
    private int index = 0;
    private TextureRegion currentFrame;
    private Boolean targetFromRight = true;
    private Sound wavTakeThat;
    private Music mp3Kamehame;
    private Stage stage;
    private ShapeRenderer sr;
    private static int health = 360;
    private int width = Gdx.graphics.getHeight()/6;
    private int height = Gdx.graphics.getHeight()/4;

    private final double speed = 1.5;


    public Goku() {
        initObjects();
        initHeroPosition();
        initSound();
    }

    public void update(List<Cell> enemies, Touchpad touchpad, Boolean kamehama) {
        drawHPbar();
        if(!setKamehameha(kamehama)) walk(touchpad);
        removeEnemy(enemies);
        wrap();
    }


    private void walk(Touchpad touchpad){
        if (touchpad.getKnobPercentX() > 0) {
            index = index < 22 || index > 32 ? 22 : ++index;
            currentFrame = animation.getKeyFrame(index);
            targetFromRight = true;
        }
        if (touchpad.getKnobPercentX() < 0) {
            index = index < 33 || index > 42  ? 33 : ++index;
            currentFrame = animation.getKeyFrame(index);
            targetFromRight = false;
        }

        if (touchpad.getKnobPercentY() > 0.5) {
            energyBall.setVisible(x, targetFromRight);
            if (new Random().nextInt(7) > 5) {
                wavTakeThat.play();
            }
        }
    }

    private void drawHPbar(){
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor((255 * (100 - scaleHealth(health))) / 100, (255 * scaleHealth(health)) / 100, 0, 1);
        sr.arc(100f,Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/6,55,50,health);
        sr.end();
        stage.draw();
    }

    private void removeEnemy(List<Cell> enemies){
        if (enemies.size() == 0) energyBall.setInvisible();
        Iterator<Cell> i = enemies.iterator();
        while (i.hasNext()) {
            Cell cell = i.next();
            energyBall.update();
            if(isOverlap(cell.getX())){
                health--;
            }
            if ((energyBall.getX() - 25 < cell.getX()) && (energyBall.getX() + 25 > cell.getX())) {
                energyBall.setInvisible();
                i.remove();
            }
        }
    }

    public void draw(SpriteBatch batch, Touchpad touchpad) {
        batch.begin();
        x += touchpad.getKnobPercentX()*speed;
        batch.draw(currentFrame, x, y, width, height);
        batch.end();
        energyBall.draw(batch);
        DragonBall.cam.position.set(x, y,0);
    }

    protected void wrap() {
        if (x < 0) x = 0;
        if (x > (Gdx.graphics.getWidth() -  Gdx.graphics.getHeight()/10)) x =  Gdx.graphics.getWidth() -  Gdx.graphics.getHeight()/10;
    }
    public EnergyBall getEnergyBall() {
        return energyBall;
    }

    private TextureRegion[] getFrames(int num, int cols, int rows, String imgPath){
        int index = 0;
        Texture img = new Texture(imgPath);
        TextureRegion[] frames = new TextureRegion[num];
        TextureRegion[][] tmp = TextureRegion.split(img,img.getWidth()/cols , img.getHeight()/rows);
        for(int j = 0; j < rows ; j++){
            for(int i = 0 ; i < cols; i++){
                frames[index++] = tmp[j][i];
            }
        }
        return frames;
    }


    private void initObjects(){
        energyBall = new EnergyBall();
        stage = new Stage();
        Image image = new Image(new Texture(Gdx.files.internal("images/goku/goku_face.png")));
        image.setPosition(50,Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/4);
        image.setSize(Gdx.graphics.getHeight()/5,Gdx.graphics.getHeight()/5);
        stage.addActor(image);
        sr  = new ShapeRenderer();
        animation = new Animation(1.6f, getFrames(28,7,4,"images/goku/goku_walk.png"));
        kameAnimation = new Animation(3f, getFrames(9,9,1,"images/goku/Kamehama.png"));
    }

    private void initSound(){
        Sound wavImReady = Gdx.audio.newSound(Gdx.files.internal("sounds/goku/im_ready.wav"));
        wavTakeThat = Gdx.audio.newSound(Gdx.files.internal("sounds/goku/take_that.wav"));
        mp3Kamehame = Gdx.audio.newMusic(Gdx.files.internal("sounds/goku/kamehameha.wav"));
        wavImReady.play();
    }

    private void initHeroPosition(){
        currentFrame = animation.getKeyFrame(0);
        x = DragonBall.WIDTH / 2;
        y = DragonBall.HEIGHT / 10;
    }

    private Boolean isOverlap(float x){
        return((x > this.x)&&(x < this.x+width)
                ||(x+width > this.x)&&(x+width < this.x+width));
    }

    private int scaleHealth(int health){
        return (health*100)/360;
    }

    private Boolean setKamehameha(Boolean kamehameha){
        if(kamehameha){
            System.out.println(index);
            if(!mp3Kamehame.isPlaying()){
                mp3Kamehame.play();
                if(index > 9) index=0;
            }
            currentFrame = kameAnimation.getKeyFrame(++index);
            return true;
        }
        return false;
    }


    public static int getHealth(){
        return health;
    }

}
