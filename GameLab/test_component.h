#pragma once

#include "component.h"
#include "vertex.h"

class TestComponent : public Component {
public:
	TestComponent() {}
	
	virtual void Input(float delta);
protected:
private:
};