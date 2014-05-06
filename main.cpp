#include "core_engine.h"
#include "game.h"

#include <iostream>

class TestGame : public Game {
public:
	TestGame() {}

	void Init() override;
protected:
private:
	TestGame(const TestGame& other) {}
	void operator=(const TestGame& other) {}
};

void TestGame::Init() {
	std::cout << "Game initialized" << std::endl;
}

int main(int argc, char** argv) {
	TestGame game;
	CoreEngine engine(&game);

	engine.CreateWindow(1280, 720, "Swag", 60);
	engine.Start();

	return 0;
}