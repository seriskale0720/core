package com.seungbin.ssgame.utils;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by seriskale on 11/21/2016.
 */

public interface Constants {
    float PPM = 100f;
    float C_SCALE = 70f;
    float VIEWPORT_W = 350f/PPM;
    float VIEWPORT_H = 150f/PPM;
    Vector2 GRAVITY = new Vector2(0, -10);
    float GROUND_DENSITY = 0f;
    float TIME_STEP = 1 / 300f;
    float PLAYER_WIDTH = VIEWPORT_W/(8);
    float PLAYER_HEIGHT = VIEWPORT_H/(2);
    //training stage
    float TS_W = 720/PPM;
    float TBG1_H = 256/PPM;
    float TBG2_H = 230/PPM;
    float TGR_H = 47/PPM;
    float TGR_B2DH = 32/PPM;
    float TPLAT_H = 25/PPM;
    float TBG1_HP = 168/PPM;
    float TBG2_HP = 38/PPM;
    Vector2 VERTICES_1 = new Vector2(0,0);
    Vector2 VERTICES_2 = new Vector2(0,424/PPM);
    Vector2 VERTICES_3 = new Vector2(720/PPM, 424/PPM);
    Vector2 VERTICES_4 = new Vector2(720/PPM, 0);

    //Controller arrow size;
    float ARROW_SIZE = 20;
    float BUTTON_SIZE_X = 25;
    float BUTTON_SIZE_Y = 30;
}

