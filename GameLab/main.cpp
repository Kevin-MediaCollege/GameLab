#include "core_engine.h"
#include "test_game.h"

int main(int argc, char** argv) {
	TestGame game;
	CoreEngine engine(&game);

	engine.CreateWindow(1280, 720, "GameLab", 60);
	engine.Start();

	return 0;
}