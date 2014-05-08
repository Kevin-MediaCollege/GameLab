#pragma once

#include "game_object.h"

class RenderingEngine;

class Component {
public:
	virtual ~Component() {}

	virtual void Input(float delta) {}
	virtual void Update(float delta) {}
	virtual void Render(RenderingEngine* renderingEngine) {}

	virtual void AddToCoreEngine(CoreEngine* coreEngine) {}

	inline void SetParent(GameObject* parent) { m_parent = parent; }

	inline GameObject* GetParent() { return m_parent; }
protected:
private:
	GameObject* m_parent;
};