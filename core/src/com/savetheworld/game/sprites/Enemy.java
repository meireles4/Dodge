package com.savetheworld.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.savetheworld.game.SaveTheWorld;

import java.util.Random;

public class Enemy {
    private static final int COLUMNS = 6;

    private Texture enemy;
    private Vector2 position;
    private Rectangle bounds;
    private Random rand;


    public Enemy (){
        enemy = new Texture("enemyTexture.png");
    }

    public void spawn(float playerPosition){
        rand = new Random();

        //Generate number between 0 and 5
        int x = rand.nextInt(COLUMNS) * 64;

        position = new Vector2( x , playerPosition + SaveTheWorld.HEIGHT );
        bounds = new Rectangle(position.x, position.y, enemy.getWidth(), enemy.getHeight());
    }

    public boolean collides(Rectangle player){
        return player.overlaps(bounds);
    }

    public void dispose(){
        enemy.dispose();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return enemy;
    }
}
