package com.lowpolywolf.talksim.actors;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.lowpolywolf.talksim.G;
import com.lowpolywolf.talksim.utils.Assets;

/**
 * @author Lukasz Zmudziak, @lukz_dev on 2017-01-21.
 */
public class ReportButton extends ImageButton {

    private static ImageButtonStyle style;

    static {
        style = new ImageButtonStyle();
        style.up = new TextureRegionDrawable(G.assets.gameRegion(Assets.Atlases.GameRegions.ReportButtonUp));
        style.down = new TextureRegionDrawable(G.assets.gameRegion(Assets.Atlases.GameRegions.ReportButtonDown));
        style.disabled = new TextureRegionDrawable(G.assets.gameRegion(Assets.Atlases.GameRegions.ReportButtonDisabled));

    }

    public ReportButton(final ConversationContainer conversationContainer) {
        super(style);

        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                conversationContainer.reportAs(false);
            }
        });


    }

    public void disable(boolean isDisabled) {
        if(isDisabled) {
            setTouchable(Touchable.disabled);
        } else {
            setTouchable(Touchable.enabled);
        }

        setDisabled(isDisabled);
    }
}
