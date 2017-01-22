package com.lowpolywolf.talksim.actors;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.lowpolywolf.talksim.G;
import com.lowpolywolf.talksim.utils.Assets;

/**
 * @author Lukasz Zmudziak, @lukz_dev on 2017-01-21.
 */
public class HpBar extends Group {

    private Image hpFill;
    private Image hpBg;
    private Image partyText;

    private float hp = 1;
    private float hpShown = hp;

    private float maxWidth;

    public HpBar() {
        hpBg = new Image(G.assets.gameRegion(Assets.Atlases.GameRegions.HpBg));
        hpFill = new Image(G.assets.gameRegion(Assets.Atlases.GameRegions.HpFill));
        partyText = new Image(G.assets.gameRegion(Assets.Atlases.GameRegions.HpPartyLoyalty));

        hpFill.setPosition(hpBg.getPrefWidth() / 2 - hpFill.getPrefWidth() / 2,
                hpBg.getPrefHeight() / 2 - hpFill.getPrefHeight() / 2);

        partyText.setPosition(hpBg.getPrefWidth() / 2 - partyText.getPrefWidth() / 2,
                hpBg.getPrefHeight() / 2 - partyText.getPrefHeight() / 2);

        setBounds(0, 0, hpBg.getPrefWidth(), hpBg.getPrefHeight());

        maxWidth = hpFill.getPrefWidth();

        addActor(hpBg);
        addActor(hpFill);
        addActor(partyText);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        hp = MathUtils.clamp(hp, 0, 1);

        hpShown = MathUtils.lerp(hpShown, hp, 0.1f * delta * 60);

        hpFill.setWidth(maxWidth * hpShown);
    }

    public void hit() {
        hp -= 0.1;
    }
}
