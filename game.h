#pragma once

class Game {
public:
	virtual void Init();

	void Input(float delta);
	void Update(float delta);
	void Render();
protected:
private:
};