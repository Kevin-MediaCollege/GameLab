#pragma once

#include "game.h"

class TestGame : public Game {
public:
	TestGame() {}
	
	virtual void Init();
protected:
private:
	TestGame(const TestGame& other) {}
	void operator=(const TestGame& other);
};
