#pragma once

#include "game.h"

#include <string>
#include <SDL2/SDL.h>

class CoreEngine {
public:
	CoreEngine(Game* game);
	virtual ~CoreEngine();

	void CreateWindow(int width, int height, const std::string& title, double framerate);
	void Start();
	void Stop();
protected:
private:
	Game* m_game;

	double m_frameTime;

	bool m_isRunning;

	void Loop();
};