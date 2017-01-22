package com.lowpolywolf.talksim.actors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.lowpolywolf.talksim.G;
import com.lowpolywolf.talksim.utils.Assets;

/**
 * @author Lukasz Zmudziak, @lukz_dev on 2017-01-22.
 */
public class Waves extends Image {

    private Animation<TextureRegion> talkAnimation = new Animation(1 / 10f, G.assets.gameRegions(Assets.Atlases.GameRegions.WavesTalk), Animation.PlayMode.LOOP);
    private Animation<TextureRegion> initAnimation = new Animation(1 / 10f, G.assets.gameRegions(Assets.Atlases.GameRegions.WavesInit), Animation.PlayMode.LOOP);
    private Animation<TextureRegion> endAnimation = new Animation(1 / 10f, G.assets.gameRegions(Assets.Atlases.GameRegions.WavesEnd), Animation.PlayMode.NORMAL);
    private float time;

    public enum WaveStates {
        IDLE, INIT, TALK, END,
    }

    public WaveStates state = WaveStates.IDLE;

    public Waves() {
        super(new TextureRegionDrawable(G.assets.gameRegion(Assets.Atlases.GameRegions.WavesIdle)));
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        time += delta;

        switch (state) {
            case IDLE:
                ((TextureRegionDrawable)getDrawable()).setRegion(G.assets.gameRegion(Assets.Atlases.GameRegions.WavesIdle));
                break;
            case INIT:
                ((TextureRegionDrawable)getDrawable()).setRegion(initAnimation.getKeyFrame(time));
                break;
            case TALK:
                ((TextureRegionDrawable)getDrawable()).setRegion(talkAnimation.getKeyFrame(time));
                break;
            case END:
                ((TextureRegionDrawable)getDrawable()).setRegion(endAnimation.getKeyFrame(time));
                break;
        }
    }

    public void setState(WaveStates state) {
        this.state = state;
        time = 0;
    }
}
