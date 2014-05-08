#pragma once

#include "game_object.h"
#include "core_engine.h"

class Game {
public:
	Game() {}
	~Game() {}

	virtual void Init() {}

	void Input(float delta);
	void Update(float delta);
	void Render(RenderingEngine* renderingEngine);

	inline void SetCoreEngine(CoreEngine* coreEngine) { m_root.SetCoreEngine(coreEngine); }
protected:
	inline void AddChild(GameObject* child) { m_root.AddChild(child); }
private:
	Game(const Game& other) {}
	void operator=(const Game& game) {}

	GameObject m_root;
};