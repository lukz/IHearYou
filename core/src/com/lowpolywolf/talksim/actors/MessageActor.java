package com.lowpolywolf.talksim.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.lowpolywolf.talksim.Consts;
import com.lowpolywolf.talksim.G;
import com.lowpolywolf.talksim.utils.Assets;

/**
 * @author Lukasz Zmudziak, @lukz_dev on 2017-01-20.
 */

public class MessageActor extends Table {

    public String message;
    public int letterIndex = 0;

    public float letterPrintingTime = Consts.LETTER_DELAY;
    public float letterTime = 0;

    public Label label;

    public static BitmapFont reusedFont = new BitmapFont();

    static {
        reusedFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public MessageActor(String message, boolean isLeft) {
        this.message = message;

        Table messageTable = new Table();

        if(isLeft) {
            messageTable.background(new NinePatchDrawable(G.assets.gamePatch(Assets.Atlases.GameRegions.BubbleLeft)));
        } else {
            messageTable.background(new NinePatchDrawable(G.assets.gamePatch(Assets.Atlases.GameRegions.BubbleRight)));
        }

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = reusedFont;
        labelStyle.fontColor = Color.BLACK  ;

        label = new Label("", labelStyle);
        label.setWrap(true);

        if(isLeft) {
            label.setAlignment(Align.left);
        } else {
            label.setAlignment(Align.right);
        }

        messageTable.add(label).expandX().fillX();
        add(messageTable).expandX().fillX();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(isMessagePrinted()) return;
        letterTime -= delta;
        if(letterTime <= 0) {
            letterTime = letterPrintingTime;
            printNextLetter();
        }
    }

    public void printNextLetter() {
        CharSequence sequence = label.getText();

        label.setText(sequence.toString() + message.charAt(letterIndex));

        letterIndex++;
    }

    public boolean isMessagePrinted() {
        return letterIndex >= message.length();
    }
}
