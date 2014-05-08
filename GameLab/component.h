#pragma once

#include "transform.h"
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

	inline Transform& GetTransform() { return m_parent -> GetTransform(); }
	inline const Transform& GetTransform() const { return m_parent -> GetTransform(); }
protected:
private:
	GameObject* m_parent;
};