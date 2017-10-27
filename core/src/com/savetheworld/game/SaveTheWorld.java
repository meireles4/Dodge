package com.savetheworld.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.StringBuilder;
import com.savetheworld.game.states.GameStateManager;
import com.savetheworld.game.states.MainMenuState;

public class SaveTheWorld extends ApplicationAdapter {
	public static final int WIDTH = 384;  //largura
	public static final int HEIGHT = 640; //altura

    public static final String TITTLE = "Save The World";

    private GameStateManager gsm;
	public static SpriteBatch batch;

    public static int highScore;
    public static final String HIGH_SCORE_FILE = "HighScore";

    private Music music;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
        gsm = new GameStateManager();
        Gdx.gl.glClearColor(1, 0, 0, 1);

        //Get highScore from preferences
        Preferences prefs = Gdx.app.getPreferences(HIGH_SCORE_FILE);
        if( prefs.contains(HIGH_SCORE_FILE)){
            highScore = prefs.getInteger(HIGH_SCORE_FILE);
        }else{
            prefs.putInteger(HIGH_SCORE_FILE, 0);
            prefs.flush();
        }

        //Create main menu state
        gsm.push(new MainMenuState(gsm));

        //Start playing music
        music = Gdx.audio.newMusic(Gdx.files.internal("bensound-memories.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();

	}

	@Override
	public void render () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
    }

    @Override
    public void dispose() {
        super.dispose();
        music.dispose();
    }
}
