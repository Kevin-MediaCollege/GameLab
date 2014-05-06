#pragma once

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
	
	std::vector<GameObject*> GetAllAttached();

	void SetCoreEngine(CoreEngine* coreEngine);
protected:
private:
	void Input(float delta);
	void Update(float delta);
	void Render(RenderingEngine* renderingEngine);

	std::vector<GameObject*> m_children;
	std::vector<Component*> m_components;
	
	CoreEngine* m_coreEngine;
};