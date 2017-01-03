package com.seungbin.ssgame.objects;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.seungbin.ssgame.stage.AbstractStage;
import com.seungbin.ssgame.utils.Constants;

import static java.lang.Math.abs;


/**
 * Created by seriskale on 11/30/2016.
 */

public abstract class Hero extends Sprite implements Constants {
    enum State {
        BLOCKING,
        HANGING,
        FALLING,
        JUMPING,
        STANDING,
        WALKING,
        RECOVERY,
        DASH,
        BLOCKRECOVERY,
        WA,
        SA,
        FWA,
        FSA,
        UWA,
        USA,
        DWA,
        DSA,
        JWA,
        JSA,
        SpecialA,
    };
    private State currentState;
    private State previousState;
    Animation heroStand;
    Animation heroWalk;
    Animation heroJumpUp;
    Animation heroJumpDown;
    Animation heroJumpRecover;
    Animation heroBlock;
    Animation heroBlockRecover;
    Animation heroDash;
    Animation heroWeakAttack;
    Animation heroStrongAttack;
    Animation heroDownWeakAttack;
    Animation heroDownStrongAttack;
    Animation heroUpWeakAttack;
    Animation heroUpStrongAttack;
    Animation heroJumpWeakAttack;
    Animation heroJumpStrongAttack;
    Animation heroForwardWeakAttack;
    Animation heroForwardStrongAttack;
    Animation heroSpecialAttack;

    private float stateTimer;
    private boolean runningRight;
    private boolean timeToRecover;
    private boolean timeToBlockRecover;


    private boolean stillInAction=true;

    private AbstractStage playStage;
    private World world;
    private Body b2Body;
    TextureAtlas atlas;

    public Hero(AbstractStage playStage, float position_X){
        this.playStage = playStage;
        this.world = playStage.getWorld();
        this.b2Body = createPlayer(position_X);
        stateTimer = 0;
        runningRight = true;
    }
    public Body createPlayer(float position_X){
        BodyDef bodydef = new BodyDef();
        bodydef.type = BodyDef.BodyType.DynamicBody;
        bodydef.position.set(new Vector2(position_X, playStage.getGroundHeight()));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(PLAYER_WIDTH/2,PLAYER_HEIGHT/2);
        Body body = world.createBody(bodydef);
        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        body.createFixture(fDef);
        shape.dispose();
        return body;
    }
    public Body getB2Body(){
        return b2Body;
    }

    private TextureRegion getFrame(float dt){
        currentState = getState();
        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        TextureRegion region;
        switch(currentState) {
            case JUMPING:
                region = heroJumpUp.getKeyFrame(stateTimer);
                break;
            case FALLING:
                region = heroJumpDown.getKeyFrame(stateTimer,true);
                if(heroJumpDown.isAnimationFinished(stateTimer)){
                    timeToRecover = true;
                }
                break;
            case WALKING:
                region = heroWalk.getKeyFrame(stateTimer,true);
                break;
            case BLOCKING:
                region = heroBlock.getKeyFrame(stateTimer);
                break;
            case RECOVERY:
                getB2Body().setLinearVelocity(0,0);
                region = heroJumpRecover.getKeyFrame(stateTimer);
                if(heroJumpRecover.isAnimationFinished(stateTimer)&&previousState!=State.FALLING)
                    timeToRecover = false;
                break;
            case DASH:
                region = heroDash.getKeyFrame(stateTimer);
                break;
            case WA:
                region = heroWeakAttack.getKeyFrame(stateTimer);
                System.out.println(stillInAction);
                if(heroWeakAttack.isAnimationFinished(stateTimer))
                    stillInAction = false;
                else if (!heroWeakAttack.isAnimationFinished(stateTimer))
                    stillInAction = true;
                break;
            case SA:
                region = heroStrongAttack.getKeyFrame(stateTimer);
                if(!heroStrongAttack.isAnimationFinished(stateTimer))
                    stillInAction = true;
                else
                    stillInAction = false;
                break;
            case FWA:
                region = heroForwardWeakAttack.getKeyFrame(stateTimer);
                break;
            case FSA:
                region = heroForwardStrongAttack.getKeyFrame(stateTimer);
                break;
            case DWA:
                region = heroDownWeakAttack.getKeyFrame(stateTimer);
                break;
            case DSA:
                region = heroDownStrongAttack.getKeyFrame(stateTimer);
                break;
            case UWA:
                region = heroUpWeakAttack.getKeyFrame(stateTimer);
                break;
            case USA:
                region = heroUpStrongAttack.getKeyFrame(stateTimer);
                break;
            case JWA:
                region = heroJumpWeakAttack.getKeyFrame(stateTimer);
                break;
            case JSA:
                region = heroJumpStrongAttack.getKeyFrame(stateTimer);
                break;
            case SpecialA:
                region = heroSpecialAttack.getKeyFrame(stateTimer);
                break;
            case BLOCKRECOVERY:
                region = heroBlockRecover.getKeyFrame(stateTimer);
                if(heroBlockRecover.isAnimationFinished(stateTimer))
                    timeToBlockRecover = false;
                else if (!heroBlockRecover.isAnimationFinished(stateTimer))
                    timeToBlockRecover = true;
                break;
            default:
                region = heroStand.getKeyFrame(stateTimer,true);
                break;
        }
        previousState = currentState;
        setAnimation(region);
        setRegion(region);
        return region;
    }


    private void setAnimation(TextureRegion region){
        if((getB2Body().getLinearVelocity().x<0)||!runningRight) {
            setBounds(getB2Body().getPosition().x + PLAYER_WIDTH / 2, getB2Body().getPosition().y - PLAYER_HEIGHT / 2, -region.getRegionWidth() / C_SCALE, region.getRegionHeight() / C_SCALE);
            runningRight=false;
        }
        if((getB2Body().getLinearVelocity().x>0)||runningRight) {
            setBounds(getB2Body().getPosition().x - PLAYER_WIDTH / 2, getB2Body().getPosition().y - PLAYER_HEIGHT / 2, region.getRegionWidth() / C_SCALE, region.getRegionHeight() / C_SCALE);
            runningRight=true;
        }
    }
/*    void setPositionX(TextureRegion region){
        setX(getB2Body().getPosition().x-PLAYER_WIDTH/2);
    }*/
    private State getState() {
        if (getB2Body().getLinearVelocity().y>0&&abs(getB2Body().getLinearVelocity().x)<=3)
            return State.JUMPING;
        else if (getB2Body().getLinearVelocity().y<0&&abs(getB2Body().getLinearVelocity().x)<=3)
            return State.FALLING;
        else if((abs(getB2Body().getLinearVelocity().x)>0)&&(abs(getB2Body().getLinearVelocity().x)<3)&&(abs(getB2Body().getLinearVelocity().y)==0)&&!timeToRecover)
            return State.WALKING;
        else if(getB2Body().getLinearVelocity().y == 0 && timeToRecover)
            return State.RECOVERY;
        else if(getB2Body().getUserData()=="DASH")
            return State.DASH;
        else if(getB2Body().getUserData()=="BLOCK")
            return State.BLOCKING;
        else if(getB2Body().getUserData()=="WA") {
            return State.WA;
        }
        else if(getB2Body().getUserData()=="SA")
            return State.SA;
        else if(getB2Body().getUserData()=="FWA")
            return State.FWA;
        else if(getB2Body().getUserData()=="FSA")
            return State.FSA;
        else if(getB2Body().getUserData()=="UWA")
            return State.UWA;
        else if(getB2Body().getUserData()=="USA")
            return State.USA;
        else if(getB2Body().getUserData()=="DWA")
            return State.DWA;
        else if(getB2Body().getUserData()=="DSA")
            return State.DSA;
        else if(getB2Body().getUserData()=="JWA")
            return State.JWA;
        else if(getB2Body().getUserData()=="JSA")
            return State.JSA;
        else if(getB2Body().getUserData()=="SpecialA")
            return State.SpecialA;
        else if(previousState == State.BLOCKING||timeToBlockRecover)
            return State.BLOCKRECOVERY;
        else {
            return State.STANDING;
        }
    }
    public boolean isStillInAction() {
        return stillInAction;
    }

    public void setStillInAction(boolean stillInAction) {
        this.stillInAction = stillInAction;
    }

    public void update(float dt){
        getFrame(dt);
    }
    void pos_setX(float offset) {
        if (runningRight)
            setCenterX(getB2Body().getPosition().x - offset / PPM);
        else
            setCenterX(getB2Body().getPosition().x + offset / PPM);
    }
}
