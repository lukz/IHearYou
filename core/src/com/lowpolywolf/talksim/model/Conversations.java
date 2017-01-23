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

        /**
         * Level 1
         */
        addConversation(1, true)
                .addLine(1, "Hello, is this the meat shop?", 0.2f)
                .addLine(2, "Yes, Staszek's Meat.", 0.2f)
                .addLine(1, "I want sausage.", 0.2f)
                .addLine(2, "We're out of sausage.", 0.2f)
                .addLine(1, "Can you double check?", 0.2f)
                .addLine(2, "I'm sure we're out of sausage.", 0.2f)
                .addLine(1, "Oh okay then, bye. Fuck you.", 0.2f);

        addConversation(1, true)
                .addLine(1, "Hallo. Bank?", 0.2f)
                .addLine(2, "...", 2f)
                .addLine(1, "Who's there?", 2f)
                .addLine(2, "...", 3f)
                .addLine(1, "Are you dead?", 0.5f)
                .addLine(1, "Fine, be like that.", 0.5f);

        addConversation(1, true)
                .addLine(1, "Hey mom!", 0.1f)
                .addLine(2, "Out of money?", 0.2f)
                .addLine(1, "What? Why? No.", 0.2f)
                .addLine(2, "You wouldn't call otherwise.", 0.2f)
                .addLine(1, "Okay can you help me out a bit?", 1f)
                .addLine(2, "No.", 0.2f)
                .addLine(1, "Please.", 0.2f);

        addConversation(1, false)
                .addLine(1, "Tangerine.", 0.5f)
                .addLine(2, "...", 2f)
                .addLine(1, "Tangerine.", 0.2f)
                .addLine(2, "Potato.", 1f)
                .addLine(1, "Mom?", 0f)
                .addLine(1, "Ooops. Wrong number.", 1f);

        addConversation(1, false)
                .addLine(1, "Chestnuts on the Red Square are surprisingly ripe this year.", 0.5f)
                .addLine(2, "Bikes go very fast.", 0.2f)
                .addLine(1, "How many?", 0.2f)
                .addLine(2, "3 dead.", 0.2f)
                .addLine(1, "I'll send clean up.", 0f);


        /**
         * Level 2
         */
        addConversation(2, true)
                .addLine(1, "Hey my car broke down.", 0.5f)
                .addLine(2, "Need help?", 0.2f)
                .addLine(1, "Yeah, just come.", 1f)
                .addLine(2, "I'll be there in an hour.", 0.2f);

        addConversation(2, true)
                .addLine(1, "Hey I need help.", 0.5f)
                .addLine(2, "What?", 2f)
                .addLine(1, "Just come.", 2f)
                .addLine(2, "What happened?", 0.2f)
                .addLine(1, "Just come here", 0.5f)
                .addLine(2, "Tell me what happened.", 0.2f)
                .addLine(1, "Fucked up my leg with an axe...", 0.5f);

        addConversation(2, false)
                .addLine(1, "Hello is this a butcher shop?", 0.5f)
                .addLine(2, "It depends what are you buying.", 1f)
                .addLine(1, "I'd like to order 3 pigs.", 1f)
                .addLine(2, "Any exact date?", 1f)
                .addLine(1, "Yes, tomorrow.", 1f)
                .addLine(2, "In uniforms?", 1f)
                .addLine(1, "Just pigs. Did you forget the code? Are you compromised?", 0.2f)
                .addLine(2, "...", 1f)
                .addLine(1, "Shit.", 0.2f);

        addConversation(2, false)
                .addLine(1, "Revelations 3:14", 0.5f)
                .addLine(2, "Genesis 15:12", 0.2f)
                .addLine(1, "What street?", 0f)
                .addLine(2, "Main square.", 3f)
                .addLine(1, "How many?", 0f)
                .addLine(1, "John 45:23", 1f);


        addConversation(2, false)
                .addLine(1, "Hello, meat shop?", 0.5f)
                .addLine(2,"Yes, how can I help you.", 0.1f)
                .addLine(1, "I want sausage.", 0f)
                .addLine(2, "We're out.", 0.1f)
                .addLine(1, "I have money.", 0f)
                .addLine(2, "We're out. Money means nothing here.", 0.1f)
                .addLine(1, "I'm coming right now, they say you have a secret stash. I'm good.", 0.2f)
                .addLine(2, "Please don't come. We don't want your dirty money, we don't have any sausage.", 0.1f);


        /**
         * Level 3
         */
        addConversation(3, false)
                .addLine(1, "Hey!", 0.5f)
                .addLine(2, "Did you hear about the party?", 0f)
                .addLine(1, "Yeah, terrible people.", 1f)
                .addLine(2, "How can they kill us like that?", 3f)
                .addLine(1, "I'm wondering the same thing. Someone shuold do something. I hope these party people get punished soon.", 1f)
                .addLine(2, "I hope you're not telling this to everyone? You don't know who you can trust anymore.", 1f)
                .addLine(1, "I trust you.", 0.1f)
                .addLine(2, "You don't know who's listening.", 0.1f);

        addConversation(3, false)
                .addLine(1, "Hallo?", 0.5f)
                .addLine(2, "...", 2f)
                .addLine(1, "Who's there?", 2f)
                .addLine(2, "...", 3f)
                .addLine(2, "Do it", 0.5f);

        addConversation(3, false)
                .addLine(1, "Whatsaaap!", 0.5f)
                .addLine(2, "Whatsaaaaaaaaaaaaaaaaap?!", 1f)
                .addLine(1, "Whatpsaaaapp?", 1f)
                .addLine(2, "Whatsaaaaaaaaaaaaaaaaap?!", 1f)
                .addLine(1, "Whatsaaaaaaaaaaaaaaaa!!", 1f)
                .addLine(2, "Who is there? Do I know you? Another stranger?", 1f)
                .addLine(1, "BYEE!", 1f)
                .addLine(2, "YO!", 1f);

        addConversation(3, true)
                .addLine(1, "Hallo?", 0.5f)
                .addLine(2, "...", 2f)
                .addLine(1, "Who's there?", 0f)
                .addLine(2, "...", 3f)
                .addLine(1, "Mom?", 0f)
                .addLine(1, "Is it you?", 1f);


        addConversation(3, true)
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
