package com.lowpolywolf.talksim.model;

import com.badlogic.gdx.utils.Array;
import com.lowpolywolf.talksim.Consts;

/**
 * @author Lukasz Zmudziak, @lukz_dev on 2017-01-21.
 */
public class Conversations {


    private static Array<ConversationDef> level1_good = new Array<ConversationDef>();
    private static Array<ConversationDef> level1_bad = new Array<ConversationDef>();

    private static Array<ConversationDef> level2_good = new Array<ConversationDef>();
    private static Array<ConversationDef> level2_bad = new Array<ConversationDef>();

    private static Array<ConversationDef> level3_good = new Array<ConversationDef>();
    private static Array<ConversationDef> level3_bad = new Array<ConversationDef>();

    static {

        addConversation(1, false)
            .addLine(1, "Hey! What's up?", 0.5f)
            .addLine(2, "Let's meet. We need to talk.", 0f)
            .addLine(1, "What's going on?", 1f)
            .addLine(2, "I'll tell you when we meet.", 3f)
            .addLine(1, "Ok. I understand now, bye.", 1f)
            .addLine(2, "Bye.", 1f);

        addConversation(1, false)
                .addLine(1, "Hallo?", 0.5f)
                .addLine(2, "...", 2f)
                .addLine(1, "Who's there?", 2f)
                .addLine(2, "...", 3f)
                .addLine(2, "Do it", 0.5f);

        addConversation(1, true)
                .addLine(1, "Whatsaaap!", 0.5f)
                .addLine(2, "Whatsaaaaaaaaaaaaaaaaap?!", 1f)
                .addLine(1, "Whatpsaaaapp?", 1f)
                .addLine(2, "Whatsaaaaaaaaaaaaaaaaap?!", 1f)
                .addLine(1, "Whatsaaaaaaaaaaaaaaaa!!", 1f)
                .addLine(2, "Who is there? Do I know you? Another stranger?", 1f)
                .addLine(1, "BYEE!", 1f)
                .addLine(2, "YO!", 1f);

        addConversation(1, true)
                .addLine(1, "Hallo?", 0.5f)
                .addLine(2, "...", 2f)
                .addLine(1, "Who's there?", 0f)
                .addLine(2, "...", 3f)
                .addLine(1, "Mom?", 0f)
                .addLine(1, "Is it you?", 1f);
    }

    public static class ConversationDef {
        public Array<ConversationLineDef> lines = new Array<ConversationLineDef>();

        public boolean isGood;

        public ConversationDef(boolean isGood) {
            this.isGood = isGood;
        }

        public ConversationDef addLine(int person, String text, float delay) {
            lines.add(new ConversationLineDef(person, text, delay));

            return this;
        }

        public float getConversationTime() {
            float time = 0;

            for (ConversationLineDef line : lines) {
                time += line.delay;
                time += line.text.length() * Consts.LETTER_DELAY;
            }

            return time;
        }
    }

    public static class ConversationLineDef {
        public int person;
        public String text;
        public float delay;

        public ConversationLineDef(int person, String text, float delay) {
            this.person = person;
            this.text = text;
            this.delay = delay;
        }
    }

    public static ConversationDef addConversation(int level, boolean isGood) {
        ConversationDef def = new ConversationDef(isGood);
        getPoolByLevel(level, isGood).add(def);
        return def;
    }

    private static Array<ConversationDef> getPoolByLevel(int level, boolean isGood) {
        switch(level) {
            case 1:
                if(isGood) return level1_good;
                return level1_bad;
            case 2:
                if(isGood) return level2_good;
                return level2_bad;
            case 3:
                if(isGood) return level3_good;
                return level3_bad;
        }

        return null;
    }

    public static Array<ConversationDef> createPoolForLevel(int level, int goodCount, int badCount) {
        Array<ConversationDef> newPool = new  Array<ConversationDef>();

        Array<ConversationDef> goodPool = getPoolByLevel(level, true);
        goodPool.shuffle();

        Array<ConversationDef> badPool = getPoolByLevel(level, false);
        badPool.shuffle();

        for (int i = 0; i < goodCount; i++) {
            newPool.add(goodPool.get(i));
        }

        for (int i = 0; i < badCount; i++) {
            newPool.add(badPool.get(i));
        }

        return newPool;
    }

}
