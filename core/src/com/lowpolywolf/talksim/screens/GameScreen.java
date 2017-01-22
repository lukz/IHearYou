package com.lowpolywolf.talksim.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.lowpolywolf.talksim.G;
import com.lowpolywolf.talksim.model.GameWorld;

/**
 * @author Lukasz Zmudziak, @lukz_dev on 2017-01-20.
 */
public class GameScreen extends ScreenAdapter {


    public GameScreen() {
        G.world = new GameWorld();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        G.world.update(delta);
        G.world.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        G.world.resize(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
