package com.lowpolywolf.talksim;

import com.badlogic.gdx.Game;
import com.lowpolywolf.talksim.model.GameWorld;
import com.lowpolywolf.talksim.utils.Assets;

public class G {
    public static boolean DEBUG = false;

    // Virtual resolution - potato units
    public static int TARGET_WIDTH = 900;
    public static int TARGET_HEIGHT = 600;

    // Game instance used to move between screens
    public static Game game;
    public static Assets assets;

    public static GameWorld world;
}
