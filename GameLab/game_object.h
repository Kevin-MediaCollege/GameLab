#pragma once

#include "transform.h"

#include <vector>

class CoreEngine;
class Component;
class RenderingEngine;

class GameObject {
public:
	GameObject() { m_coreEngine = 0; }
	virtual ~GameObject();

	GameObject* AddChild(GameObject* child);
	GameObject* AddComponent(Component* component);

	void InputAll(float delta);
	void UpdateAll(float delta);
	void RenderAll(RenderingEngine* renderingEngine);
	
	void SetCoreEngine(CoreEngine* coreEngine);

	std::vector<GameObject*> GetAllAttached();

	inline Transform& GetTransform() { return m_transform; }
protected:
private:
	void Input(float delta);
	void Update(float delta);
	void Render(RenderingEngine* renderingEngine);

	std::vector<GameObject*> m_children;
	std::vector<Component*> m_components;
	
	Transform m_transform;
	CoreEngine* m_coreEngine;
};