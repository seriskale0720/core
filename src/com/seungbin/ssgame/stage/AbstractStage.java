package com.seungbin.ssgame.stage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.seungbin.ssgame.SSGame;
import com.seungbin.ssgame.utils.Constants;

/**
 * Created by seriskale on 11/29/2016.
 */

public abstract class AbstractStage implements Constants {
    private World world;
    Texture texture;
    TextureRegion background1;
    TextureRegion background2;
    TextureRegion ground;
    TextureRegion platform;
    SSGame game;
    float groundHeight;


    public AbstractStage(SSGame game) {
        this.game = game;
        world = new World(GRAVITY, true);
    }
    public World getWorld(){
        return world;
    }

    abstract void createGround();

    abstract void createPlatform();

    abstract void createBoundaries();

    public abstract void renderBackground();

    public abstract float getGroundHeight();


}
