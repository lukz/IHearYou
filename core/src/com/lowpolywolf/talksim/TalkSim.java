package com.lowpolywolf.talksim;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.lowpolywolf.talksim.screens.LoadingScreen;
import com.lowpolywolf.talksim.utils.Assets;

public class TalkSim extends Game {

	private FPSLogger log;

	@Override
	public void create() {
		G.game = this;
		G.assets = new Assets();
		log = new FPSLogger();

		G.game.setScreen(new LoadingScreen());
	}

	@Override
	public void render() {
		super.render();
		log.log();
	}

	@Override public void dispose () {
		super.dispose();
		G.assets.dispose();
	}


}
