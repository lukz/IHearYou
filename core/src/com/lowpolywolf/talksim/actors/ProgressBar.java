package com.lowpolywolf.talksim.actors;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.lowpolywolf.talksim.G;
import com.lowpolywolf.talksim.utils.Assets;

/**
 * @author Lukasz Zmudziak, @lukz_dev on 2017-01-21.
 */
public class ProgressBar extends Group {

    private Image progressFill;
    private Image progressBg;

    private float progress = 1;
//    private float progressShown = progress;

    private float maxWidth;

    public ProgressBar() {
        progressBg = new Image(G.assets.gameRegion(Assets.Atlases.GameRegions.ProgressBg));
        progressFill = new Image(G.assets.gameRegion(Assets.Atlases.GameRegions.ProgressFill));

        progressFill.setPosition(progressBg.getPrefWidth() / 2 - progressFill.getPrefWidth() / 2,
                progressBg.getPrefHeight() - progressFill.getPrefHeight()   );

        setBounds(0, 0, progressBg.getPrefWidth(), progressBg.getPrefHeight());

        maxWidth = progressFill.getPrefWidth();

        addActor(progressBg);
        addActor(progressFill);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

//        progress = MathUtils.clamp(progress, 0, 1);
//
//        progressShown = MathUtils.lerp(progressShown, progress, 0.1f * delta * 60);

        progressFill.setWidth(maxWidth * progress);
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }
}
