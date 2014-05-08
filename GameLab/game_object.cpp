#include "game_object.h"
#include "component.h"

GameObject::~GameObject() {
	for(unsigned int i = 0; i < m_components.size(); i++)
		if(m_components[i])
			delete m_components[i];
	
	for(unsigned int i = 0; i < m_children.size(); i++)
		if(m_children[i])
			delete m_children[i];
}

GameObject* GameObject::AddChild(GameObject* child) {
	m_children.push_back(child); 
	
	child -> GetTransform().SetParent(&m_transform);
	child -> SetCoreEngine(m_coreEngine);

	return this;
}

GameObject* GameObject::AddComponent(Component* component) {
	m_components.push_back(component);

	component -> SetParent(this);
	
	return this;
}

void GameObject::InputAll(float delta) {
	Input(delta);

	for(unsigned int i = 0; i < m_children.size(); i++)
		m_children[i] -> InputAll(delta);
}

void GameObject::UpdateAll(float delta) {
	Update(delta);

	for(unsigned int i = 0; i < m_children.size(); i++)
		m_children[i] -> UpdateAll(delta);
}

void GameObject::RenderAll(RenderingEngine* renderingEngine) {
	Render(renderingEngine);

	for(unsigned int i = 0; i < m_children.size(); i++)
		m_children[i] -> RenderAll(renderingEngine);
}

void GameObject::Input(float delta) {
	m_transform.Update();

	for(unsigned int i = 0; i < m_components.size(); i++)
		m_components[i] -> Input(delta);
}

void GameObject::Update(float delta) {
	for(unsigned int i = 0; i < m_components.size(); i++)
		m_components[i] -> Update(delta);
}

void GameObject::Render(RenderingEngine* renderingEngine) {
	for(unsigned int i = 0; i < m_components.size(); i++)
		m_components[i] -> Render(renderingEngine);
}

void GameObject::SetCoreEngine(CoreEngine* coreEngine) {
	if(m_coreEngine != coreEngine) {
		m_coreEngine = coreEngine;
		
		for(unsigned int i = 0; i < m_components.size(); i++)
			m_components[i] -> AddToCoreEngine(coreEngine);

		for(unsigned int i = 0; i < m_children.size(); i++)
			m_children[i] -> SetCoreEngine(coreEngine);
	}
}

std::vector<GameObject*> GameObject::GetAllAttached() {
	std::vector<GameObject*> result;
	
	for(unsigned int i = 0; i < m_children.size(); i++) {
		std::vector<GameObject*> childObjects = m_children[i] -> GetAllAttached();
		result.insert(result.end(), childObjects.begin(), childObjects.end());
	}
	
	result.push_back(this);

	return result;
}