package com.seungbin.ssgame.role;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.seungbin.ssgame.objects.Hero;
import com.seungbin.ssgame.objects.Kenshin;
import com.seungbin.ssgame.overlay.Controller;
import com.seungbin.ssgame.stage.AbstractStage;
import com.seungbin.ssgame.utils.Constants;

import static java.lang.StrictMath.abs;

/**
 * Created by seriskale on 12/1/2016.
 */

public class Player implements Constants {

    private Controller controller;
    private Hero hero;

    public Player(Controller controller, AbstractStage playStage){
        this.controller = controller;
        hero = new Kenshin(playStage, TS_W/3);
    }
    public void update(float dt){
        hero.update(dt);
    }
    public void handleInput(){
        action();
    }
    public void draw(Batch gamebatch){
        hero.draw(gamebatch);
    }
    public float positionX(){
        return hero.getB2Body().getPosition().x;
    }
    public float positionY(){
        return hero.getB2Body().getPosition().y;
    }
    private void action(){
        if ((controller.isRightDoubleTapped()||controller.isLeftDoubleTapped())&&hero.getB2Body().getLinearVelocity().y==0){
            hero.getB2Body().setUserData("DASH");
            if(controller.isRightDoubleTapped()) {
                hero.getB2Body().setLinearVelocity(new Vector2(7, 0));
                controller.initializeRTap();
            }
            else if (controller.isLeftDoubleTapped()) {
                hero.getB2Body().setLinearVelocity(new Vector2(-7, 0));
                controller.initializeLTap();
            }
        }
        if (hero.getB2Body().getLinearVelocity().y==0 && controller.isTrianglePressed()) {
            hero.getB2Body().setUserData("JUMP");
            hero.getB2Body().setLinearVelocity(new Vector2(hero.getB2Body().getLinearVelocity().x, 5f));
            controller.setTrianglePressed(false);
        }
        else if (controller.isTrianglePressed()&&hero.getB2Body().getUserData() == "JUMP"){
            hero.getB2Body().setUserData("DOUBLEJUMP");
            hero.getB2Body().setLinearVelocity(new Vector2(hero.getB2Body().getLinearVelocity().x, 5f));
        }
        if ((controller.isRightPressed()||controller.isLeftPressed())&&hero.getB2Body().getUserData()!="DASH") {
            if(controller.isRightPressed())
                hero.getB2Body().setLinearVelocity(new Vector2(1.7f, hero.getB2Body().getLinearVelocity().y));
            else if(controller.isLeftPressed())
                hero.getB2Body().setLinearVelocity(new Vector2(-1.7f, hero.getB2Body().getLinearVelocity().y));
        }
        else if (controller.isDownPressed()) {
            hero.getB2Body().setLinearVelocity(new Vector2(0, hero.getB2Body().getLinearVelocity().y));
            hero.getB2Body().setUserData("BLOCK");
        }
        else if (controller.isCrossPressed()){
            hero.getB2Body().setUserData("WA");
            if(!hero.isStillInAction())
                controller.setCrossPressed(false);
        }
        else if (controller.isSquarePressed()){
            hero.getB2Body().setUserData("SA");
            if(!hero.isStillInAction())
                controller.setSquarePressed(false);
        }
        else if (hero.getB2Body().getLinearVelocity().y==0&&abs(hero.getB2Body().getLinearVelocity().x)<=6.5f){
            hero.getB2Body().setUserData("STANDING");
            hero.getB2Body().setLinearVelocity(0,hero.getB2Body().getLinearVelocity().y);
        }
    }
}
