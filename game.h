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

	inline void SetCoreEngine(CoreEngine* coreEngine) {
		m_coreEngine = coreEngine;
		m_root.SetCoreEngine(coreEngine);
	}

	inline GameObject& GetRootGameObject() {
		return m_root;
	}
protected:
	inline void AddChild(GameObject* child) {
		m_root.AddChild(child);
	}

	CoreEngine* m_coreEngine;
private:
	Game(const Game& other) {}
	void operator=(const Game& game) {}

	GameObject m_root;
};