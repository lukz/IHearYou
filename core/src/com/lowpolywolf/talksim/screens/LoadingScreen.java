package com.lowpolywolf.talksim.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.lowpolywolf.talksim.G;

/**
 * @author Lukasz Zmudziak, @lukz_dev on 2017-01-20.
 */
public class LoadingScreen extends ScreenAdapter {

    @Override
    public void show() {
        G.assets.enqueueAssets();
    }

    @Override
    public void render(float delta) {
        if(G.assets.update()) {
            G.game.setScreen(new GameScreen());
        }
    }
}
