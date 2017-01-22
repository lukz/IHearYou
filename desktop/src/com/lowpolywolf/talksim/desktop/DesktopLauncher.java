package com.lowpolywolf.talksim.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.lowpolywolf.talksim.G;
import com.lowpolywolf.talksim.TalkSim;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = G.TARGET_WIDTH;
		config.height = G.TARGET_HEIGHT;

		new LwjglApplication(new TalkSim(), config);
	}
}
