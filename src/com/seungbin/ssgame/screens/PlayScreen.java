package com.seungbin.ssgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.seungbin.ssgame.SSGame;
import com.seungbin.ssgame.objects.Kenshin;
import com.seungbin.ssgame.objects.Hero;
import com.seungbin.ssgame.overlay.Controller;
import com.seungbin.ssgame.role.Player;
import com.seungbin.ssgame.stage.AbstractStage;
import com.seungbin.ssgame.stage.TrainingStage;
import com.seungbin.ssgame.utils.Constants;

/**
 * Created by seriskale on 11/17/2016.
 */

public class PlayScreen implements Screen, Constants {
    private SSGame game;
    private OrthographicCamera cam;
    private Viewport viewport;
    private AbstractStage playStage;
    private Box2DDebugRenderer b2dr;
    private Hero userHero, aiHero;
    private Controller controller;
    private Player player;
    private float accumulator;

    public PlayScreen(SSGame game) {
        this.game = game;
        setUpCamera();
        controller = new Controller(game.batch);
        b2dr = new Box2DDebugRenderer();
        playStage = new TrainingStage(game);
        player = new Player(controller, playStage);
    }
    private void setUpCamera(){
        cam = new OrthographicCamera();
        viewport = new FitViewport(VIEWPORT_W, VIEWPORT_H, cam);
    }
    private void update(float dt){
        accumulator += dt;
        while (accumulator >= dt) {
            playStage.getWorld().step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }
        player.handleInput();
        player.update(dt);
        cam.position.x = player.positionX();
        cam.position.y = player.positionY();
        cam.update();
    }
    @Override
    public void show() {
    }
    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        playStage.renderBackground();
        player.draw(game.batch);
        game.batch.end();
        b2dr.render(playStage.getWorld(), cam.combined);
        controller.draw();
        }
    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
        controller.resize(width, height);

    }
    @Override
    public void pause() {
    }
    @Override
    public void resume() {
    }
    @Override
    public void hide() {
    }
    @Override
    public void dispose() {
        playStage.getWorld().dispose();
        b2dr.dispose();
        game.batch.dispose();
        controller.dispose();
    }
}
