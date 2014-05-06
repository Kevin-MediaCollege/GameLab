#include "core_engine.h"

CoreEngine::CoreEngine(Game* game) {
	m_game = game;

	m_isRunning = false;
}

CoreEngine::~CoreEngine() {
	SDL_Quit();
}

void CoreEngine::CreateWindow(int width, int height, const std::string& title, double framerate) {
	m_frameTime = 1.0 / framerate;
}

void CoreEngine::Start() {
	if(m_isRunning)
		return;

	m_isRunning = true;

	m_game -> Init();

	Loop();
}

void CoreEngine::Stop() {
	if(!m_isRunning)
		return;

	m_isRunning = false;
}

void CoreEngine::Loop() {

}