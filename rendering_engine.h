#pragma once

class GameObject;

class RenderingEngine {
public:
	RenderingEngine();
	virtual ~RenderingEngine();

	void Render(GameObject* gameObject);
protected:
private:
	RenderingEngine(const RenderingEngine& other) {}
	void operator=(const RenderingEngine& other) {}
};