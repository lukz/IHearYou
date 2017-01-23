package com.lowpolywolf.talksim.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.lowpolywolf.talksim.G;
import com.lowpolywolf.talksim.utils.Assets;

/**
 * @author Lukasz Zmudziak, @lukz_dev on 2017-01-22.
 */
public class DayOverlay extends Table {

    public DayOverlay(int day) {
        setFillParent(true);
        align(Align.top);

        SpriteDrawable spriteDrawable = new SpriteDrawable(new Sprite(G.assets.gameRegion(Assets.Atlases.GameRegions.WhitePix)));
        spriteDrawable.getSprite().setColor(Color.BLACK);
        spriteDrawable.getSprite().setAlpha(0.7f);

        setBackground(spriteDrawable);

        Image dayImage = null;
        Image cardImage = null;

        switch(day) {
            case 1:
                dayImage = new Image(G.assets.gameRegion(Assets.Atlases.GameRegions.Day1));
                cardImage = new Image(G.assets.gameRegion(Assets.Atlases.GameRegions.Card1));
                break;
            case 2:
                dayImage = new Image(G.assets.gameRegion(Assets.Atlases.GameRegions.Day2));
                cardImage = new Image(G.assets.gameRegion(Assets.Atlases.GameRegions.Card2));
                break;
            case 3:
                dayImage = new Image(G.assets.gameRegion(Assets.Atlases.GameRegions.Day3));
                cardImage = new Image(G.assets.gameRegion(Assets.Atlases.GameRegions.Card3));
                break;
        }

        this.add(cardImage).padTop(40).row();
        this.add(dayImage).padTop(20);

        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                DayOverlay.this.remove();
            }
        });
    }
}
