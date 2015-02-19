package com.mygdx.game.controllers;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.mygdx.game.units.enemies.Cell;
import com.mygdx.game.units.heros.Goku;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ostap on 13.10.2014.
 */

public class PlayState extends GameState {
    private SpriteBatch batch;
    private Long botCreated;
    private Goku goku;
    private List<Cell> enemies;

    public PlayState(GameStateManager gsm){
        super(gsm);
        init();
    }

    public void init(){
        enemies = new LinkedList<Cell>();
        batch = new SpriteBatch();
        goku = new Goku();
//        enemies.add(new Cell());
        botCreated = Calendar.getInstance().getTimeInMillis();
    }

    public  void  update(Touchpad touchpad, Boolean kamehama){
//        if (Calendar.getInstance().getTimeInMillis() - botCreated > 10000){
//            botCreated = Calendar.getInstance().getTimeInMillis();
//            enemies.add(new Cell());
//        }
        goku.update(enemies, touchpad, kamehama);
        if(enemies.size() > 0) {
            for (Cell cell : enemies) {
                cell.update(goku.getX());
            }
        }
    }
    public  void draw(Touchpad touchpad){
        goku.draw(batch, touchpad);
        if(enemies.size() > 0){
            for(int i = 0; i < enemies.size(); i++){
                enemies.get(i).draw(batch);
            }
        }
    }
    public void dispose(){}

}
