#include "core_engine.h"
#include "time.h"
#include "window.h"
#include "input.h"
#include "utils.h"
#include "game.h"

#include <iostream>

bool CoreEngine::s_isDebug = false;

CoreEngine::CoreEngine(Game* game, bool debug) : m_game(game), m_isRunning(false) {
	s_isDebug = debug;

	m_game -> SetCoreEngine(this);
}

CoreEngine::~CoreEngine() {
	Window::Destroy();

	if(m_renderingEngine)
		delete m_renderingEngine;
}

void CoreEngine::CreateWindow(int width, int height, const std::string& title, double framerate, bool fullscreen, int x, int y) {
	Window::Create(width, height, title, fullscreen, x, y);

	m_renderingEngine = new RenderingEngine();
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
	double lastTime = Time::GetTime();
	double unprocessedTime = 0;
	double frameCounter = 0;

	int frames = 0;

	while(m_isRunning) {
		bool render = false;

		double startTime = Time::GetTime();
		double passedTime = startTime - lastTime;
		lastTime = startTime;

		unprocessedTime += passedTime;
		frameCounter += passedTime;

		if(frameCounter >= 1.0) {
			if(s_isDebug)
				std::cout << "FPS: " << frames << std::endl;

			frames = 0;
			frameCounter = 0;
		}

		while(unprocessedTime > m_frameTime) {
			render = true;

			if(Window::IsCloseRequested())
				Stop();

			Input::Update();

			m_game -> Input((float)m_frameTime);
			m_game -> Update((float)m_frameTime);

			unprocessedTime -= m_frameTime;
		}

		if(render) {
			m_game -> Render(m_renderingEngine);
			Window::Update();
			frames++;
		} else {
			Utils::Sleep(1);
		}
	}
}