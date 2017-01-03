package com.seungbin.ssgame.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.seungbin.ssgame.stage.AbstractStage;

/**
 * Created by seriskale on 12/4/2016.
 */

public class Kenshin extends Hero{


    public Kenshin (AbstractStage PlayStage, float position_X){
        super(PlayStage, position_X);
        atlas = new TextureAtlas("images/Hero/Kenshin/Kenshin.txt");
        Array<TextureRegion> frames = new Array<TextureRegion>();
        getStandAnimation(frames);
        getWalkAnimation(frames);
        getJumpDownAnimation(frames);
        getStandAnimation(frames);
        getJumpUpAnimation(frames);
        getJumpRecoverAnimation(frames);
        getBlockAnimation(frames);
        getDashAnimation(frames);
        getWeakAttack(frames);
        getStrongAttack(frames);
        getForwardWeakAttack(frames);
        getForwardStrongAttack(frames);
        getDownWeakAttack(frames);
        getDownStrongAttack(frames);
        getUpWeakAttack(frames);
        getUpStrongAttack(frames);
        getJumpWeakAttack(frames);
        getJumpStrongAttack(frames);
        getSpecialAttack(frames);
    }
    private Animation getStandAnimation(Array<TextureRegion> frames){
        for(int i=1; i<=2; i++) {
            frames.add(new TextureRegion(atlas.findRegion("Stand" + i),-2,0,31,53));
        }
        for(int i=3; i<=4; i++){
            frames.add(new TextureRegion(atlas.findRegion("Stand"+ i)));
        }
        heroStand = new Animation(0.15f, frames);
        frames.clear();
        return heroStand;
    }
    private Animation getWalkAnimation(Array<TextureRegion> frames){
        for(int i=1; i<=8; i++){
            frames.add(new TextureRegion(atlas.findRegion("Walk" + i)));
        }
        heroWalk = new Animation(0.07f, frames);
        frames.clear();
        return heroWalk;
    }
    private Animation getJumpUpAnimation(Array<TextureRegion> frames){
        for(int i=1; i<=3; i++){
            frames.add(new TextureRegion(atlas.findRegion("JumpUp"+i)));
        }
        heroJumpUp = new Animation(0.03f, frames);
        frames.clear();
        return heroJumpUp;
    }
    private Animation getJumpDownAnimation(Array<TextureRegion> frames){
        for(int i=1; i<=2; i++){
            frames.add(new TextureRegion(atlas.findRegion("JumpDown" + i )));
        }
        heroJumpDown = new Animation(0.1f, frames);
        frames.clear();
        return heroJumpDown;
    }
    private Animation getJumpRecoverAnimation(Array<TextureRegion> frames){
        for(int i=1; i<=3; i++){
            frames.add(new TextureRegion(atlas.findRegion("JumpRecover" + i)));
        }
        heroJumpRecover = new Animation(0.07f, frames);
        frames.clear();
        return heroJumpRecover;
    }
    private Animation getBlockAnimation(Array<TextureRegion> frames){
        frames.add(new TextureRegion(atlas.findRegion("Block3")));
        for(int i=1; i<=2; i++) {
            frames.add(new TextureRegion(atlas.findRegion("Block"+i)));
        }
        heroBlock = new Animation(0.08f, frames);
        frames.reverse();
        heroBlockRecover = new Animation(0.06f, frames);
        frames.clear();
        return heroBlock;
    }
    private Animation getDashAnimation(Array<TextureRegion> frames){
        for(int i=1; i<=2; i++) {
            frames.add(new TextureRegion(atlas.findRegion("Dash" + i)));
        }
        heroDash = new Animation(0.1f, frames);
        frames.clear();
        return heroDash;
    }
    private Animation getWeakAttack(Array<TextureRegion> frames){
        for(int i=1; i<=5; i++){
            frames.add(new TextureRegion(atlas.findRegion("B"+i)));
        }
        heroWeakAttack = new Animation(0.1f, frames);
        frames.clear();
        return heroWeakAttack;
    }
    private Animation getStrongAttack(Array<TextureRegion> frames){
        for(int i=1; i<=11; i++){
            frames.add(new TextureRegion(atlas.findRegion("Y"+i)));
        }
        heroStrongAttack = new Animation(0.1f, frames);
        frames.clear();
        return heroStrongAttack;
    }
    private Animation getForwardWeakAttack(Array<TextureRegion> frames){
        for(int i=1; i<=7; i++){
            frames.add(new TextureRegion(atlas.findRegion("FB"+i)));
        }
        heroForwardWeakAttack = new Animation(0.1f, frames);
        frames.clear();
        return heroForwardWeakAttack;
    }
    private Animation getForwardStrongAttack(Array<TextureRegion> frames){
        for(int i=1; i<=11; i++){
            frames.add(new TextureRegion(atlas.findRegion("FY"+i)));
        }
        heroForwardStrongAttack = new Animation(0.1f, frames);
        frames.clear();
        return heroForwardStrongAttack;
    }
    private Animation getDownWeakAttack(Array<TextureRegion> frames){
        for(int i=1; i<=8; i++){
            frames.add(new TextureRegion(atlas.findRegion("DB"+i)));
        }
        heroDownWeakAttack = new Animation(0.1f, frames);
        frames.clear();
        return heroDownWeakAttack;
    }
    private Animation getDownStrongAttack(Array<TextureRegion> frames){
        for(int i=1; i<=8; i++){
            frames.add(new TextureRegion(atlas.findRegion("DY"+i)));
        }
        heroDownStrongAttack = new Animation(0.1f, frames);
        frames.clear();
        return heroDownStrongAttack;
    }
    private Animation getUpWeakAttack(Array<TextureRegion> frames){
        for(int i=1; i<=8; i++){
            frames.add(new TextureRegion(atlas.findRegion("UB"+i)));
        }
        heroUpWeakAttack = new Animation(0.1f, frames);
        frames.clear();
        return heroUpWeakAttack;
    }
    private Animation getUpStrongAttack(Array<TextureRegion> frames){
        for(int i=1; i<=11; i++){
            frames.add(new TextureRegion(atlas.findRegion("UY"+i)));
        }
        heroUpStrongAttack = new Animation(0.1f, frames);
        frames.clear();
        return heroUpStrongAttack;
    }
    private Animation getJumpWeakAttack(Array<TextureRegion> frames){
        for(int i=1; i<=7; i++){
            frames.add(new TextureRegion(atlas.findRegion("JB"+i)));
        }
        heroJumpWeakAttack = new Animation(0.1f, frames);
        frames.clear();
        return heroJumpWeakAttack;
    }
    private Animation getJumpStrongAttack(Array<TextureRegion> frames){
        for(int i=1; i<=11; i++){
            frames.add(new TextureRegion(atlas.findRegion("JY"+i)));
        }
        heroJumpStrongAttack = new Animation(0.1f, frames);
        frames.clear();
        return heroJumpStrongAttack;
    }
    private Animation getSpecialAttack(Array<TextureRegion> frames){
        for(int i=1; i<=13; i++){
            frames.add(new TextureRegion(atlas.findRegion("Amakake"+i)));
        }
        heroSpecialAttack = new Animation(0.1f, frames);
        frames.clear();
        return heroSpecialAttack;
    }
    /*void setPositionX(TextureRegion region){
        if(region == heroBlock.getKeyFrame(2*heroBlock.getFrameDuration()))
            pos_setX(5f);
        else if (region == heroBlock.getKeyFrame(1*heroBlock.getFrameDuration()))
            pos_setX(-4f);
        else if (region == heroWalk.getKeyFrame(0*heroWalk.getFrameDuration())|region==heroWalk.getKeyFrame(4*heroWalk.getFrameDuration()))
            pos_setX(2);
        else if (region == heroWalk.getKeyFrame(3*heroWalk.getFrameDuration())|region == heroWalk.getKeyFrame(7*heroWalk.getFrameDuration()))
            pos_setX(1);
        else
            super.setPositionX(region);
    }*/

}
