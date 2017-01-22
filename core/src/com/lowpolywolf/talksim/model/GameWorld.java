package com.lowpolywolf.talksim.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.lowpolywolf.talksim.G;
import com.lowpolywolf.talksim.actors.ConversationContainer;
import com.lowpolywolf.talksim.actors.HpBar;
import com.lowpolywolf.talksim.utils.Assets;

/**
 * @author Lukasz Zmudziak, @lukz_dev on 2017-01-20.
 */
public class GameWorld {

    private Stage gameStage;
    private Table overlayRoot;

    // Actors
    private HpBar hpBar;
    private Table messageLinesTable;

    private float shakeTime = 0;

    public GameWorld() {
        this.gameStage = new Stage(new ExtendViewport(G.TARGET_WIDTH, G.TARGET_HEIGHT));

        this.gameStage.addActor(new Image(G.assets.gameRegion(Assets.Atlases.GameRegions.Bg)));

        this.messageLinesTable = new Table();
        this.messageLinesTable.defaults().space(6);

        Array<Conversations.ConversationDef> levelPool = Conversations.createPoolForLevel(1, 2, 2);

        this.messageLinesTable.add(new ConversationContainer(levelPool));
        this.messageLinesTable.add(new ConversationContainer(levelPool));
        this.messageLinesTable.add(new ConversationContainer(levelPool));
        this.messageLinesTable.pack();
        this.messageLinesTable.setPosition(34, 0);
        this.gameStage.addActor(messageLinesTable);

        this.overlayRoot = new Table();
        this.overlayRoot.setFillParent(true);
        this.overlayRoot.align(Align.top);
        this.overlayRoot.add(new Image(G.assets.gameRegion(Assets.Atlases.GameRegions.TopOverlay))).expandX().fillX();
        this.overlayRoot.setTouchable(Touchable.disabled);
        this.gameStage.addActor(overlayRoot);

        this.hpBar = new HpBar();
        this.hpBar.setPosition(8, G.TARGET_HEIGHT - 8, Align.topLeft);
        this.gameStage.addActor(this.hpBar);

        if(G.DEBUG) {
            this.gameStage.setDebugAll(true);
        }

        Gdx.input.setInputProcessor(this.gameStage);
    }

    public void update(float delta) {
        gameStage.act();

        // Reset camera pos
        gameStage.getCamera().position.set(gameStage.getCamera().viewportWidth / 2, gameStage.getCamera().viewportHeight / 2, 0);


        // Poorman's screenshake!
        if(shakeTime > 0) {
            shakeTime -= delta;
            gameStage.getCamera().position.x += MathUtils.random(-15f, 15f);
            gameStage.getCamera().position.y += MathUtils.random(-15f, 15f);
        }
    }

    public void draw() {
        gameStage.draw();
    }

    public void resize(int width, int height) {
        gameStage.getViewport().update(width, height, true);
    }

    public HpBar getHpBar() {
        return hpBar;
    }

    public void addShake() {
        shakeTime += 0.2f;
    }
}
