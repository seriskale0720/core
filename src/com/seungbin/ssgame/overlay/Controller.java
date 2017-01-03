package com.seungbin.ssgame.overlay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.seungbin.ssgame.utils.Constants;

/**
 * Created by seriskale on 11/30/2016.
 */

public class Controller implements Constants, Disposable {
    Viewport viewport;
    Stage stage;
    @Override
    public void dispose() {
        stage.dispose();
    }
    private boolean downPressed;
    private boolean upPressed;
    private boolean crossPressed;
    private boolean squarePressed;
    private boolean circlePressed;
    private boolean trianglePressed;
    private MyClickListener leftGesture,rightGesture;
    OrthographicCamera cam;
    public Controller(Batch batch) {
        cam = new OrthographicCamera();
        viewport = new FitViewport(VIEWPORT_W*PPM, VIEWPORT_H*PPM, cam);
        stage = new Stage(viewport, batch);

        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        table.left().bottom();
        Image up = new Image(new Texture("images/Controller/ARROW_BUTTON.png"));
        up.setSize(ARROW_SIZE, ARROW_SIZE);
        up.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                upPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                upPressed = false;
            }
        });
        Image down = new Image(new Texture("images/Controller/ARROW_BUTTON.png"));
        down.setSize(ARROW_SIZE, ARROW_SIZE);
        down.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                downPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                downPressed = false;
            }
        });
        Image right = new Image(new Texture("images/Controller/ARROW_BUTTON.png"));
        right.setSize(ARROW_SIZE, ARROW_SIZE);
        right.addListener(rightGesture = new MyClickListener());

        Image left = new Image(new Texture("images/Controller/ARROW_BUTTON.png"));
        left.setSize(ARROW_SIZE, ARROW_SIZE);
        left.addListener(leftGesture = new MyClickListener());
        table.add();
        table.add(up).size(up.getWidth(), up.getHeight());
        table.add();
        table.row();
        table.add(left).size(left.getWidth(), left.getHeight());
        table.add();
        table.add(right).size(right.getWidth(), right.getHeight());
        table.row();
        table.add();
        table.add(down).size(down.getWidth(), down.getHeight());
        table.add();
        stage.addActor(table);

        Table table_2 = new Table();

        Image triangle = new Image(new Texture("images/Controller/PS_TRIANGLE.png"));
        triangle.setSize(ARROW_SIZE,ARROW_SIZE);
        triangle.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                trianglePressed = true;
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                trianglePressed = false;
            }
        });

        Image cross = new Image(new Texture("images/Controller/PS_CROSS.png"));
        cross.setSize(ARROW_SIZE,ARROW_SIZE);
        cross.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                crossPressed = true;
                return false;
            }
        });

        Image square = new Image(new Texture("images/Controller/PS_SQUARE.png"));
        square.setSize(ARROW_SIZE,ARROW_SIZE);
        square.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                squarePressed = true;
                return false;
            }
        });

        Image circle = new Image(new Texture("images/Controller/PS_CIRCLE.png"));
        circle.setSize(ARROW_SIZE,ARROW_SIZE);
        circle.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                circlePressed = true;
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                circlePressed = false;
            }
        });
        table_2.add();
        table_2.add(triangle).size(triangle.getWidth(), up.getHeight());
        table_2.add();
        table_2.row();
        table_2.add(square).size(square.getWidth(), left.getHeight());
        table_2.add();
        table_2.add(circle).size(circle.getWidth(), right.getHeight());
        table_2.row();
        table_2.add();
        table_2.add(cross).size(cross.getWidth(), down.getHeight());
        table_2.add();
        table_2.setFillParent(true);
        table_2.right().bottom();
        stage.addActor(table_2);
    }
    public void draw(){
        stage.draw();
    }
    public void resize(int width, int height){
        viewport.update(width, height);
    }

    public boolean isLeftPressed() {
        return leftGesture.isPressed();
    }
    public boolean isRightPressed() {
        return rightGesture.isPressed();
    }

    public boolean isRightDoubleTapped() {
        return rightGesture.isDoubleTapped();
    }
    public boolean isLeftDoubleTapped() {
        return leftGesture.isDoubleTapped();
    }

    public void initializeRTap() {
        rightGesture.setTapCount(0);
    }
    public void initializeLTap() {
        leftGesture.setTapCount(0);
    }

    public boolean isDownPressed() {
        return downPressed;
    }
    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isTrianglePressed() {
        return trianglePressed;
    }

    public boolean isCrossPressed(){
        return crossPressed;
    }

    public void setCrossPressed(boolean crossPressed) {
        this.crossPressed = crossPressed;
    }

    public boolean isSquarePressed(){
        return squarePressed;
    }

    public void setSquarePressed(boolean squarePressed) {
        this.squarePressed = squarePressed;
    }

    public boolean isCirclePressed(){
        return circlePressed;
    }

    public void setTrianglePressed(boolean trianglePressed) {
        this.trianglePressed = trianglePressed;
    }

}


class MyClickListener extends InputListener{
    private boolean pressed;
    private long tapCountInterval = (long)(0.3f * 1000000000l);
    private long time, lastTapTime;
    private int tapCount;


    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        time = TimeUtils.nanoTime();
        if((time - lastTapTime) > tapCountInterval)
            tapCount = 0;
        tapCount++;
        lastTapTime = time;
        pressed = true;
        return true;
    }

    public void setTapCount(int tapCount){
        this.tapCount = tapCount;
    }

    public int getTapCount(){
        return this.tapCount;
    }
    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        pressed = false;
    }
    public boolean isPressed(){
        return pressed;
    }
    public boolean isDoubleTapped(){
        if (tapCount==2)
            return true;
        else
            return false;
    }
}