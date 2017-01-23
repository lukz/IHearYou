package com.lowpolywolf.talksim.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Assets extends AssetManager {

    // Second day!!

    public static class Atlases {
        public static final String Game = "gfx/gfx_game.atlas";

        public static class GameRegions {
            public static final String Bg = "bg";
            public static final String MessageLineBg = "messageLineBg";
            public static final String TopOverlay = "topOverlay";

            public static final String HpBg = "hpBg";
            public static final String HpFill = "hpFill";
            public static final String HpPartyLoyalty = "partyText";

            public static final String ProgressBg = "progressBg";
            public static final String ProgressFill = "progressFill";

            public static final String ReportButtonUp = "reportButtonUp";
            public static final String ReportButtonDown = "reportButtonDown";
            public static final String ReportButtonDisabled = "reportButtonDisabled";

            public static final String BubbleCenter = "bubble-center";
            public static final String BubbleLeft = "bubble-left";
            public static final String BubbleRight = "bubble-right";

            public static final String Waves = "waves";
            public static final String WavesIdle = "wavesIdle";
            public static final String WavesInit = "wavesInit";
            public static final String WavesTalk = "wavesTalk";
            public static final String WavesEnd = "wavesEnd";

            public static final String StampGood = "goodStamp";
            public static final String StampBad = "badStamp";

            public static final String Card1 = "card1";
            public static final String Card2 = "card2";
            public static final String Card3 = "card3";

            public static final String Day1 = "day1";
            public static final String Day2 = "day2";
            public static final String Day3 = "day3";

            public static final String WhitePix = "whitePix";

            public static final String End = "end";
        }

    }
        public static final String Music = "music.mp3";

    public Assets() {
        Texture.setAssetManager(this);

        enqueueAssets();
    }

    public void enqueueAssets() {
        // Textures atlases
        load(Atlases.Game, TextureAtlas.class);
        load(Music, Music.class);
    }

    public TextureAtlas gameAtlas() {
        return get(Atlases.Game, TextureAtlas.class);
    }

    public TextureRegion gameRegion(String name) {
        return gameAtlas().findRegion(name);
    }

    public Array<TextureAtlas.AtlasRegion> gameRegions(String name) {
        return gameAtlas().findRegions(name);
    }

    public NinePatch gamePatch(String name) {
        return gameAtlas().createPatch(name);
    }

}
