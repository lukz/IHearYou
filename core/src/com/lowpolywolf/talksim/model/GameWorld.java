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
import com.lowpolywolf.talksim.actors.DayOverlay;
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
    private DayOverlay dayOverlay;
    private Image deadOverlay;

    private int day = 1;

    private float shakeTime = 0;

    public enum GameStates {
        SHOW_DAY, SHOW_CARD, GAME_RUNNING, GAME_OVER
    }

    private Array<ConversationContainer> conversationContainers = new Array<ConversationContainer>();

    private GameStates state;

    public GameWorld() {
        this.gameStage = new Stage(new ExtendViewport(G.TARGET_WIDTH, G.TARGET_HEIGHT));

        this.gameStage.addActor(new Image(G.assets.gameRegion(Assets.Atlases.GameRegions.Bg)));

        this.messageLinesTable = new Table();
        this.messageLinesTable.defaults().space(6);
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

        setDay();
        setState(GameStates.SHOW_DAY);

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


        // Start
        if(dayOverlay.getStage() == null) {
            for (ConversationContainer conversationContainer : conversationContainers) {
                if(conversationContainer.getCallState() == ConversationContainer.CallStates.IDLE) {
                    conversationContainer.setState(ConversationContainer.CallStates.INCOMING_CALL);
                }
            }
        }

        // Finish
        if(conversationContainers.size > 0) {
            boolean finished = true;
            for (ConversationContainer conversationContainer : conversationContainers) {
                if(conversationContainer.getCallState() != ConversationContainer.CallStates.NO_MORE) {
                    finished = false;
                }
            }

            if(finished) {
                finish();
            }
        }

        if(hpBar.getHp() <= 0 && deadOverlay == null) {
            deadOverlay = new Image(G.assets.gameRegion(Assets.Atlases.GameRegions.End));
            gameStage.addActor(deadOverlay);
            gameOver();
        }

        if(deadOverlay != null) {
            if(Gdx.input.justTouched()) {
                deadOverlay.remove();
                deadOverlay = null;

                day = 1;
                getHpBar().setHp(1);
                setDay();
            }
        }

        switch(state) {
            case SHOW_DAY:
                break;
            case SHOW_CARD:
                break;
            case GAME_RUNNING:
                break;
            case GAME_OVER:
                break;
        }
    }

    public void setState(GameStates state) {
        this.state = state;

        switch(state) {
            case SHOW_DAY:
                dayOverlay = new DayOverlay(day);
                gameStage.addActor(dayOverlay);
                break;
            case SHOW_CARD:
                break;
            case GAME_RUNNING:
                break;
            case GAME_OVER:
                break;
        }
    }

    public void finish() {
        day++;

        conversationContainers.clear();
        messageLinesTable.clear();

        if(day > 3) {

            return;
        }

        setDay();
        setState(GameStates.SHOW_DAY);
    }

    public void gameOver() {
        day++;

        conversationContainers.clear();
        messageLinesTable.clear();
    }

    public void setDay() {
        Array<Conversations.ConversationDef> levelPool = Conversations.createPoolForLevel(day, 2, 2);

        for (int i = 0; i < day; i++) {
            ConversationContainer container = new ConversationContainer(levelPool);

            this.messageLinesTable.add(container);
            this.conversationContainers.add(container);
        }


        this.messageLinesTable.pack();
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
