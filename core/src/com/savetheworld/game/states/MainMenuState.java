package com.savetheworld.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.savetheworld.game.SaveTheWorld;

public class MainMenuState extends State {

    private Texture bg;
    private Texture playBtn;

    private BitmapFont highScore;
    private String highScoreString;
    private float fontWidth;

    public MainMenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, SaveTheWorld.WIDTH, SaveTheWorld.HEIGHT);
        bg = new Texture("bg.png");
        playBtn = new Texture("btn_playgame.png");

        highScore = new BitmapFont();
        highScoreString = "Best Score: " + SaveTheWorld.highScore;
        highScore.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        highScore.getData().setScale(1.5f);

        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(highScore, highScoreString);
        fontWidth = glyphLayout.width;
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){

            //Convert input coordinates x and y to gameWorld x and y
            float x = (Gdx.input.getX() * SaveTheWorld.WIDTH) / Gdx.graphics.getWidth();
            float y = (Gdx.input.getY() * SaveTheWorld.HEIGHT) / Gdx.graphics.getHeight();

            //if touch inside the playBtn start PlayState
            if( x >= cam.position.x - playBtn.getWidth()/2 && x <= cam.position.x + playBtn.getWidth()/2 &&
                    y <= cam.position.y + playBtn.getHeight()/2  && y >= cam.position.y - playBtn.getHeight()/2){
                gsm.set( new PlayState(gsm));
                dispose();
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, 0, 0);
        sb.draw(playBtn, cam.position.x - playBtn.getWidth()/2, cam.position.y - playBtn.getHeight()/2 );
        highScore.draw(sb, highScoreString, cam.position.x - fontWidth/2 , cam.position.y - (cam.viewportHeight / 4) );
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        playBtn.dispose();
        //System.out.println("Main Menu Disposed");
    }
}
