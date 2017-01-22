package com.lowpolywolf.talksim.actors;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.lowpolywolf.talksim.Consts;
import com.lowpolywolf.talksim.G;
import com.lowpolywolf.talksim.model.Conversation;
import com.lowpolywolf.talksim.model.Conversations;

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
}
