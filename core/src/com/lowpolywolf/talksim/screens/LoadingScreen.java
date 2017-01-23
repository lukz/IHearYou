package com.lowpolywolf.talksim.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.lowpolywolf.talksim.G;
import com.lowpolywolf.talksim.utils.Assets;

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
            G.assets.font(Assets.FontConsolas).getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

            Music music =  G.assets.get(Assets.Music, Music.class);
            music.setLooping(true);
            music.play();

            G.game.setScreen(new GameScreen());
        }
    }
}
