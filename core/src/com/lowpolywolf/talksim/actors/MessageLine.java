package com.lowpolywolf.talksim.actors;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.Align;
import com.lowpolywolf.talksim.Consts;
import com.lowpolywolf.talksim.G;
import com.lowpolywolf.talksim.model.Conversation;
import com.lowpolywolf.talksim.model.Conversations;
import com.lowpolywolf.talksim.utils.Assets;

/**
 * @author Lukasz Zmudziak, @lukz_dev on 2017-01-20.
 */
public class MessageLine extends Table {

    private Conversation conversation;

    private MessageActor currentMessage = null;
    private Table messagesTable;

    private ScrollPane messagesTableScroll;

    private ProgressBar progressBar;

    public MessageLine() {
        this.defaults().align(Align.top);

        background(new TextureRegionDrawable(G.assets.gameRegion(Assets.Atlases.GameRegions.MessageLineBg)));
        align(Align.top);

        this.progressBar = new ProgressBar();

        this.messagesTable = new Table();
        this.messagesTable.defaults().align(Align.top).padTop(5).padBottom(5);
        this.messagesTable.align(Align.top);

        this.messagesTableScroll = new ScrollPane(this.messagesTable);
        this.messagesTableScroll.setOverscroll(false, false);

        this.add(new Image(new TextureRegionDrawable(G.assets.gameRegion(Assets.Atlases.GameRegions.Waves)))).padTop(2).row();
        this.add(progressBar).row();
        this.add(messagesTableScroll).size(244 - 4, 450 - 89).padLeft(2).padRight(2).expand().fill().row();
    }

    public void update(float delta) {
        if(currentMessage != null && !currentMessage.isMessagePrinted())
            return;

        conversation.updateDelay(delta);

        if(conversation.hasNewLine()) {
            addNewLine(conversation.consumeNextLine());
        }
    }

    public void updateScroll() {
        if(!this.messagesTableScroll.isPanning()) {
            this.messagesTableScroll.setScrollPercentY(1);
        }
    }

    public void addNewLine(Conversations.ConversationLineDef line) {
        currentMessage = new MessageActor(line.text, line.person % 2 == 0);

        Cell newCell = messagesTable.add(currentMessage).expandX().fillX();

        if(line.person % 2 == 0) {
            newCell.align(Align.left).padLeft(5).padRight(15);
        } else {
            newCell.align(Align.right).padRight(5).padLeft(15);
        }

        messagesTable.row();
    }

    public void addFinished() {
        Table incomingTable = new Table();
        incomingTable.background(new NinePatchDrawable(G.assets.gamePatch(Assets.Atlases.GameRegions.BubbleCenter)));

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = MessageActor.reusedFont;

        Label label = new Label("Call ended...", labelStyle) {

            private  float timeToFlash = 1;

            @Override
            public void act(float delta) {
                super.act(delta);
                timeToFlash += delta;
                setVisible(timeToFlash % 2 > 1);

                if(timeToFlash >= Consts.CONVERSATION_FINISHED_DELAY) {
                    setVisible(true);
                }
            }
        };

        label.setVisible(false);
        label.setAlignment(Align.center);
        label.setWrap(true);

        incomingTable.add(label).padTop(10).padBottom(10).expandX().fillX();

        messagesTable.add(incomingTable).expandX().fillX().row();
    }

    public void addIncoming() {
        Table incomingTable = new Table();
        incomingTable.background(new NinePatchDrawable(G.assets.gamePatch(Assets.Atlases.GameRegions.BubbleCenter)));

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = MessageActor.reusedFont;

        Label label = new Label("Initializing call...", labelStyle) {

            private  float timeToFlash = 1;

            @Override
            public void act(float delta) {
                super.act(delta);

                timeToFlash += delta;
                setVisible(timeToFlash % 2 > 1);

                if(timeToFlash >= Consts.CONVERSATION_INCOMING_DELAY) {
                    setVisible(true);
                }
            }
        };

        label.setVisible(false);
        label.setAlignment(Align.center);
        label.setWrap(true);

        incomingTable.add(label).padTop(10).padBottom(10).expandX().fillX();

        messagesTable.add(incomingTable).padTop(20).expandX().fillX().row();
    }

    public void newConversation(Conversation conversation) {
        messagesTable.clear();

        this.conversation = conversation;

        currentMessage = null;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public MessageActor getCurrentMessage() {
        return currentMessage;
    }
}
