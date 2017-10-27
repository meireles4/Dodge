package com.savetheworld.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.savetheworld.game.SaveTheWorld;
import com.savetheworld.game.sprites.Enemy;
import com.savetheworld.game.sprites.Player;

public class PlayState extends State {

    private static final float WAITING_TIME = 1f;
    private Player player;
    private Array<Enemy> enemies;
    private Texture bg;

    private boolean colided;
    private float timeAfterColision;

    private float timeLeftToEnemySpawn = 1f;

    private int score;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, SaveTheWorld.WIDTH, SaveTheWorld.HEIGHT);

        enemies = new Array<Enemy>();
        bg = new Texture("bg.png");
        player = new Player();

        timeAfterColision = 0;
        colided = false;
        score = 0;

    }

    @Override
    protected void handleInput() {

        if(Gdx.input.justTouched() && !colided){
            int x = Gdx.input.getX();

            if( x < Gdx.graphics.getWidth() / 2 && player.getPosition().x > 0) {
                player.moveLeft();
            }
            else if( x >= Gdx.graphics.getWidth() / 2 && player.getPosition().x < 254){
                player.moveRight();
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        player.update();
        cam.position.y = player.getPosition().y + 300;

        if(!colided){

            timeLeftToEnemySpawn -= dt;

            if( timeLeftToEnemySpawn < 0){
                Enemy e = new Enemy();
                e.spawn(player.getPosition().y);

                enemies.add(e);

                timeLeftToEnemySpawn = 1f;
            }

            for(int i = 0; i < enemies.size; i++){
                Enemy e = enemies.get(i);

                if( e.getPosition().y + e.getTexture().getHeight() < cam.position.y - (cam.viewportHeight / 2)) {
                    e.dispose();
                    enemies.removeIndex(i);
                    score++;
                    break;
                }

                if( e.collides(player.getBounds())){
                    colided = true;
                    player.setMovement(0);

                    System.out.println("COLIDED");
                    break;


                }
            }
        }else{
            if(timeAfterColision >= WAITING_TIME) {
                if( score > SaveTheWorld.highScore) {
                    //update highScore
                    SaveTheWorld.highScore = score;

                    //Update highScore in preferences
                    Preferences prefs = Gdx.app.getPreferences(SaveTheWorld.HIGH_SCORE_FILE);
                    prefs.putInteger(SaveTheWorld.HIGH_SCORE_FILE, SaveTheWorld.highScore);
                    prefs.flush();
                }
                gsm.set(new GameOverState(gsm, score));
            }
            else
                timeAfterColision += dt;
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        sb.draw(bg, 0, cam.position.y - (cam.viewportHeight / 2));

        for(int i = 0; i < enemies.size; i++){
            Enemy e = enemies.get(i);

            sb.draw(e.getTexture(), e.getPosition().x, e.getPosition().y);

        }

        sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);

        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        player.dispose();

        //System.out.println("Play State Disposed");
    }
}
