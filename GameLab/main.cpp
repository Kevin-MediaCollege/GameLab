#include "core_engine.h"
#include "component.h"
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

class TestComponent : public Component {
public:
	TestComponent() {}

	
protected:
private:
};

void TestGame::Init() {
	std::cout << "Game initialized" << std::endl;

	GameObject go;

	AddChild(&go);

	//GameObject go;
	//TestComponent component;

	//go.AddComponent(&component);
	//AddChild(&go);
}

int main(int argc, char** argv) {
	TestGame game;
	CoreEngine engine(&game);

	engine.CreateWindow(1280, 720, "GameLab", 60);
	engine.Start();

	return 0;
}