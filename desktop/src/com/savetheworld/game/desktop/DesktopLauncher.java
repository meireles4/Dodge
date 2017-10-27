package com.savetheworld.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.savetheworld.game.SaveTheWorld;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = SaveTheWorld.WIDTH;
		config.height = SaveTheWorld.HEIGHT;
		config.title = SaveTheWorld.TITTLE;

		new LwjglApplication(new SaveTheWorld(), config);
	}
}
