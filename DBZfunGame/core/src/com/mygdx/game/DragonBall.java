package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.controllers.GameStateManager;
import com.mygdx.game.units.heros.Goku;

public class DragonBall extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;
	public static OrthographicCamera cam;
    private GameStateManager gsm;
    public static int WIDTH;
    public static int HEIGHT;
    private Touchpad touchpad;
    private TextButton fightButton;
    private Touchpad.TouchpadStyle touchpadStyle;
    private Skin touchpadSkin, fightSkin;
    private Drawable touchBackground;
    private Drawable touchKnob;
    private Stage stage;
    private Image image;
    private TextureRegion fightButtonPress;
    private TextureRegion fightButtonNorm;
    private Boolean kamehama = false;

	@Override
	public void create () {
		batch = new SpriteBatch();
        img = new Texture("images/maps/jungle.jpg");
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        cam = new OrthographicCamera(WIDTH, HEIGHT);
        image = new Image(new Texture(Gdx.files.internal("images/levelcontroll/Game-Over.png")));
        fightButtonNorm = new TextureRegion(new Texture("images/fightButton/button_norm.png"));
        fightButtonPress = new TextureRegion(new Texture("images/fightButton/button_pressed.png"));
        cam.translate(WIDTH/2,HEIGHT/2);
        setJoistick();
        setFightButton();
	    gsm = new GameStateManager();
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0, 2 * WIDTH, (int) (HEIGHT + 0.2 * HEIGHT));
        batch.end();
        Gdx.input.setInputProcessor(stage);
        gsm.update(touchpad, kamehama);
        gsm.draw(touchpad);
        stage.draw();

        cam.translate(WIDTH/2, HEIGHT/2);
        batch.setProjectionMatrix(cam.combined);
        cam.update();

        if(Goku.getHealth() < 0){
            image.setPosition(WIDTH/9, HEIGHT/4);
            stage.addActor(image);
        }

	}

    private void setJoistick() {
        touchpadSkin = new Skin();
        touchpadSkin.add("touchBackground", new Texture("images/data/touchBackground.png"));
        touchpadSkin.add("touchKnob", new Texture("images/data/touchKnob.png"));
        touchpadStyle = new Touchpad.TouchpadStyle();
        touchBackground = touchpadSkin.getDrawable("touchBackground");
        touchKnob = touchpadSkin.getDrawable("touchKnob");
        touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;
        touchpad = new Touchpad(10, touchpadStyle);
        touchpad.setBounds(15, 15, 200, 200);
        stage = new Stage();
        stage.addActor(touchpad);
        Gdx.input.setInputProcessor(stage);
    }


    private void setFightButton(){
        Skin skin = new Skin();
        skin.add("norm_but",fightButtonNorm);
        skin.add("press_but",fightButtonPress);
        ImageButton kickButton = new ImageButton(skin.getDrawable("norm_but"),skin.getDrawable("press_but"));
        kickButton.setPosition((int)(0.8*WIDTH),HEIGHT/25);
        kickButton.setSize(HEIGHT/5,HEIGHT/5);
        stage.addActor(kickButton);
        kickButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                kamehama = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                kamehama = false;
            }
        });

    }

}
