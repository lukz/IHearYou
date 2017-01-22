package com.lowpolywolf.talksim.actors;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.lowpolywolf.talksim.Consts;
import com.lowpolywolf.talksim.G;
import com.lowpolywolf.talksim.model.Conversation;
import com.lowpolywolf.talksim.model.Conversations;
import com.lowpolywolf.talksim.utils.Assets;

/**
 * @author Lukasz Zmudziak, @lukz_dev on 2017-01-21.
 */
public class ConversationContainer extends Table {

    private Array<Conversations.ConversationDef> conversationsPool;
    private Conversation conversation;

    private MessageLine messageLine;
    private ReportButton reportButton;

    private float conversationTime = 0;

    public enum CallStates {
        INCOMING_CALL, CALL_RUNNING, CALL_FINISH, CALL_RESULT
    }

    public float delay = 0;

    public CallStates callState;

    public Image stamp;

    public ConversationContainer(Array<Conversations.ConversationDef> conversationsPool) {
        this.conversationsPool = conversationsPool;

        this.messageLine = new MessageLine();
        this.reportButton = new ReportButton(this);

        this.add(messageLine).size(244, 450).row();
        this.add(reportButton).padTop(-45);

        setState(CallStates.INCOMING_CALL);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        switch(callState) {
            case INCOMING_CALL:
                conversationTime += delta;

                delay -= delta;
                if(delay < 0) setState(CallStates.CALL_RUNNING);
                break;

            case CALL_RUNNING:
                messageLine.update(delta);
                messageLine.updateScroll();
                conversationTime += delta;

                if(conversation.isFinished() && messageLine.getCurrentMessage().isMessagePrinted()) {
                    setState(CallStates.CALL_FINISH);
                }

                break;

            case CALL_FINISH:
                conversationTime += delta;
                delay -= delta;
                messageLine.updateScroll();

                if(delay < 0) {
                    reportAs(true);
                }
                break;


            case CALL_RESULT:
                delay -= delta;
                messageLine.updateScroll();

                if(delay < 0 && isThereNextConversation()) {
                    setState(CallStates.INCOMING_CALL);
                }

                break;
        }

        messageLine.getProgressBar().setProgress(getProgress());
    }

    private void newConversation() {
        Conversations.ConversationDef def = conversationsPool.pop();

        this.conversation = new Conversation(def);

        this.messageLine.newConversation(conversation);

        this.conversationTime = 0;
    }

    public void setState(CallStates newState) {
        callState = newState;

        switch (newState) {
            case INCOMING_CALL:
                if(stamp != null) {
                    stamp.remove();
                }

                newConversation();
                this.messageLine.addIncoming();
                this.delay = Consts.CONVERSATION_INCOMING_DELAY;

                this.reportButton.disable(true);
                break;

            case CALL_RUNNING:
                this.reportButton.disable(false);
                break;

            case CALL_FINISH:
                this.messageLine.addFinished();
                this.delay = Consts.CONVERSATION_FINISHED_DELAY;
                break;

            case CALL_RESULT:
                this.reportButton.disable(true);
                this.delay = Consts.CONVERSATION_RESULT_DELAY;

                break;
        }
    }

    public void reportAs(boolean good) {
        if(good != conversation.conversationDef.isGood) {
            G.world.getHpBar().hit();
            stampConversation(false);
        } else {
            stampConversation(true);
        }


        setState(CallStates.CALL_RESULT);
    }

    public boolean isThereNextConversation() {
        return conversationsPool.size > 0;
    }

    public float getProgress() {
        float maxTime = conversation.conversationDef.getConversationTime() + Consts.CONVERSATION_INCOMING_DELAY  + Consts.CONVERSATION_FINISHED_DELAY;

        return MathUtils.clamp(conversationTime / maxTime, 0, 1);
    }

    public void stampConversation(final boolean good) {
        if(good) {
            stamp = new Image(new TextureRegionDrawable(G.assets.gameRegion(Assets.Atlases.GameRegions.StampGood)));
        } else {
            stamp = new Image(new TextureRegionDrawable(G.assets.gameRegion(Assets.Atlases.GameRegions.StampBad)));
        }

        Vector2 posX = messageLine.localToStageCoordinates(new Vector2(messageLine.getX(Align.center), messageLine.getY(Align.center)));
        Vector2 posY = localToStageCoordinates(new Vector2(getX(Align.center), getY(Align.center)));

        stamp.setOrigin(Align.center);
        stamp.setScale(5, 5);
        stamp.rotateBy(90);

        stamp.setPosition(posX.x, posY.y, Align.center);

        stamp.addAction(Actions.sequence(
                Actions.parallel(
                        Actions.scaleTo(1, 1, 0.1f, Interpolation.exp5In),
                        Actions.rotateTo(0, 0.1f, Interpolation.exp5In)
                ),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        G.world.addShake();
                    }
                }),
                Actions.delay(Consts.CONVERSATION_RESULT_DELAY - 0.1f)
        ));

        this.getStage().addActor(stamp);
    }
}
