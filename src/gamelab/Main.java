package gamelab;

import com.snakybo.sengine.core.CoreEngine;

public class Main {
	public static void main(String[] args) {
		CoreEngine engine = new CoreEngine(new TestGame());
		engine.createWindow(1280, 720, "GameLab", 60);
		engine.start();
	}
}