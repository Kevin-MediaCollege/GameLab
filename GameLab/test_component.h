#pragma once

#include "component.h"

class TestComponent : public Component {
public:
	TestComponent() {}
	
	virtual void Input(float delta);
};