#include "test_component.h"
#include "input.h"

#include <iostream>

void TestComponent::Input(float delta) {
	if(Input::GetKeyDown(Input::KEY_A))
		std::cout << "Key Pressed" << std::endl;

	if(Input::GetKey(Input::KEY_A))
		std::cout << "Key Down" << std::endl;

	if(Input::GetKeyUp(Input::KEY_A))
		std::cout << "Key Released" << std::endl;

	if(Input::GetMouseDown(Input::LEFT_MOUSE))
		std::cout << "Mouse Pressed" << std::endl;

	if(Input::GetMouse(Input::LEFT_MOUSE))
		std::cout << "Mouse Down" << std::endl;

	if(Input::GetMouseUp(Input::LEFT_MOUSE))
		std::cout << "Mouse Released" << std::endl;
}