package com.savetheworld.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.savetheworld.game.SaveTheWorld;

public class GameOverState extends State {
    private Texture bg;
    private Texture gameOver;
    private Texture replay;
    //private Texture menuBtn;

    private int lastGameScore;

    protected GameOverState(GameStateManager gsm, int score) {
        super(gsm);
        cam.setToOrtho(false, SaveTheWorld.WIDTH, SaveTheWorld.HEIGHT);
        bg = new Texture("bg.png");
        gameOver = new Texture("gameOverBlack.png");
        replay = new Texture("btn_replay.png");
        //menuBtn = new Texture("menuBtn.png");

        lastGameScore = score;

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            //Convert input coordinates x and y to gameWorld x and y
            float x = (Gdx.input.getX() * SaveTheWorld.WIDTH) / Gdx.graphics.getWidth();
            float y = (Gdx.input.getY() * SaveTheWorld.HEIGHT) / Gdx.graphics.getHeight();

            //if touch inside the playBtn start PlayState
            if( x >= cam.position.x - replay.getWidth()/2 && x <= cam.position.x + replay.getWidth()/2 &&
                    y <= cam.position.y + replay.getHeight()  && y >= cam.position.y){
                gsm.set( new PlayState(gsm));
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
        sb.draw(bg,0, 0);
        sb.draw(gameOver, cam.position.x - gameOver.getWidth()/2, cam.position.y);
        sb.draw(replay, cam.position.x - replay.getWidth()/2, cam.position.y - replay.getHeight());
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        gameOver.dispose();
        replay.dispose();
    }
}
