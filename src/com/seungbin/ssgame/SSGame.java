package com.seungbin.ssgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.seungbin.ssgame.screens.PlayScreen;
import com.seungbin.ssgame.utils.Constants;

/**
 * Created by seriskale on 11/17/2016.
 */

public class SSGame extends Game implements Constants {

	public SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}
}
