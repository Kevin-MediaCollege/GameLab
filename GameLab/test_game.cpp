#include "test_game.h"
#include "test_component.h"

#include <iostream>

void TestGame::Init() {
	std::cout << "Test game init" << std::endl;

	GameObject* testGameObject = new GameObject();

	testGameObject -> AddComponent(new TestComponent());

	AddChild(testGameObject);
}
