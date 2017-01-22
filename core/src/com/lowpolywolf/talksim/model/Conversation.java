package com.lowpolywolf.talksim.model;

/**
 * @author Lukasz Zmudziak, @lukz_dev on 2017-01-21.
 */
public class Conversation {

    private int currentLine;

    private float lineDelayTime;
    private Conversations.ConversationLineDef lineToPrint = null;

    private boolean conversationFinished = false;

    public Conversations.ConversationDef conversationDef;

    public Conversation(Conversations.ConversationDef conversationDef) {
        this.conversationDef = conversationDef;

        currentLine = -1;
//        lineToPrint = conversationDef.lines.get(currentLine);
    }

    public void updateDelay(float delta) {
        if(conversationFinished) return;

        lineDelayTime += delta;

        if(lineDelayTime > conversationDef.lines.get(currentLine + 1).delay) {
            lineDelayTime = 0;
            currentLine++;
            lineToPrint = conversationDef.lines.get(currentLine);

            if(currentLine + 1 >= conversationDef.lines.size) {
                conversationFinished = true;
                return;
            }
        }
    }
/*
    public void reset() {
        currentLine = 0;
        lineDelayTime = 0;
        lineToPrint = null;
        conversationFinished = false;
    }*/

    public boolean isFinished() {
        return conversationFinished;
    }

    public boolean hasNewLine() {
        return lineToPrint !=null;
    }

    public Conversations.ConversationLineDef consumeNextLine() {
        Conversations.ConversationLineDef nextLine = lineToPrint;
        lineToPrint = null;

        return nextLine;
    }

    public float progress() {
        return  1- ((currentLine + 1) / (float)conversationDef.lines.size);
    }


}
