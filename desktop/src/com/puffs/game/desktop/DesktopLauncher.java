package com.puffs.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.puffs.game.PuffsDriver;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=PuffsDriver.SCR_W;
		config.height=PuffsDriver.SCR_H;
		new LwjglApplication(new PuffsDriver(), config);
	}
}
