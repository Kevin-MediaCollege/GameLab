#include "game.h"
#include "rendering_engine.h"

void Game::Input(float delta) {
	m_root.InputAll(delta);
}

void Game::Update(float delta) {
	m_root.UpdateAll(delta);
}

void Game::Render(RenderingEngine* renderingEngine) {
	renderingEngine -> Render(&m_root);
}