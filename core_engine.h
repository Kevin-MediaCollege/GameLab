#pragma once

#include "rendering_engine.h"

#include <string>
#include <SDL2/SDL.h>

class Game;

class CoreEngine {
public:
	CoreEngine(Game* game);
	virtual ~CoreEngine();

	void CreateWindow(int width, int height, const std::string& title, double framerate, bool fullscreen = false, int x = SDL_WINDOWPOS_CENTERED, int y = SDL_WINDOWPOS_CENTERED);
	void Start();
	void Stop();

	inline RenderingEngine* GetRenderingEngine() {
		return m_renderingEngine;
	}
protected:
private:
	CoreEngine(const CoreEngine& other) {}
	void operator=(const CoreEngine& other) {}

	RenderingEngine* m_renderingEngine;
	Game* m_game;

	double m_frameTime;

	bool m_isRunning;

	void Loop();
};