package com.seungbin.ssgame.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.seungbin.ssgame.SSGame;

/**
 * Created by seriskale on 11/29/2016.
 */

public class TrainingStage extends AbstractStage{

    public TrainingStage(SSGame game){
        super(game);
        texture = new Texture(Gdx.files.internal("images/Stage/Stage1.png"));
        background1 = new TextureRegion(texture, 5, 88, 512, 256);
        background2 = new TextureRegion(texture, 8, 356, 511, 230);
        ground = new TextureRegion(texture, 8, 620, 512, 47);
        platform = new TextureRegion(texture, 389, 591, 129, 25);
        createGround();
        createBoundaries();
    }

    @Override
    void createGround() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(TS_W/2,TGR_B2DH/2);
        Body body = super.getWorld().createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(TS_W/2, TGR_B2DH/2);
        body.createFixture(shape,GROUND_DENSITY);
        shape.dispose();
    }
    @Override
    void createBoundaries() {
        BodyDef bodyDef = new BodyDef();
        Body body = super.getWorld().createBody(bodyDef);
        EdgeShape shape = new EdgeShape();
        shape.set(VERTICES_1,VERTICES_2);
        body.createFixture(shape,GROUND_DENSITY);
        shape.set(VERTICES_2,VERTICES_3);
        body.createFixture(shape,GROUND_DENSITY);
        shape.set(VERTICES_3,VERTICES_4);
        body.createFixture(shape,GROUND_DENSITY);
        shape.dispose();
    }

    @Override
    void createPlatform() {
    }

    @Override
    public void renderBackground() {
        game.batch.draw(background1, 0, TBG1_HP, TS_W, TBG1_H);
        game.batch.draw(background2, 0, TBG2_HP, TS_W, TBG2_H);
        game.batch.draw(ground, 0, 0, TS_W, TGR_H);
    }

    @Override
    public float getGroundHeight(){
        this.groundHeight = TGR_H;
        return groundHeight;
    }
}
