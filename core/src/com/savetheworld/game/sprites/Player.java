package com.savetheworld.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.savetheworld.game.SaveTheWorld;

public class Player {
    private static final float INITIAL_POSITION_X = SaveTheWorld.WIDTH/2;
    private static final float INITIAL_POSITION_Y = 0;
    private static final int SIDE_MOVE_AMOUNT = 64;
    private int movement = 6;

    private Vector3 position;
    private Rectangle bounds;

    private Texture player;

    public Player(){
        player = new Texture("playerTexture.png");
        bounds = new Rectangle(INITIAL_POSITION_X - player.getWidth()/2, INITIAL_POSITION_Y, player.getWidth(), player.getHeight());

        position = new Vector3(INITIAL_POSITION_X - player.getWidth()/2, INITIAL_POSITION_Y, 0);
    }

    public void update(){
        position.add(0, movement, 0);
        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public Texture getTexture() {
        return player;
    }

    public void moveLeft(){
        position.add(-SIDE_MOVE_AMOUNT, 0, 0);
    }

    public void moveRight(){
        position.add(SIDE_MOVE_AMOUNT, 0, 0);
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){
        player.dispose();
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }
}
