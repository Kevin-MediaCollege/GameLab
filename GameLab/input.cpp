#include "input.h"
#include "sdl_backend.h"

#include <SDL2/SDL.h>

const static int NUM_KEYS = 512;
const static int NUM_MOUSEBUTTONS = 256;

static SDL_Event e;

static int mouseX = 0;
static int mouseY = 0;

static bool lastKeys[NUM_KEYS];
static bool downKeys[NUM_KEYS];
static bool upKeys[NUM_KEYS];

static bool lastMouse[NUM_MOUSEBUTTONS];
static bool downMouse[NUM_MOUSEBUTTONS];
static bool upMouse[NUM_MOUSEBUTTONS];

void Input::Update() {
	for(int i = 0; i < NUM_MOUSEBUTTONS; i++) {
		downMouse[i] = false;
		upMouse[i] = false;
	}

	for(int i = 0; i < NUM_KEYS; i++) {
		downKeys[i] = false;
		upKeys[i] = false;
	}

	while(SDL_PollEvent(&e)) {
		if(e.type == SDL_QUIT)
			SDLBackend::Window_RequestClose();

		if(e.type == SDL_MOUSEMOTION) {
			mouseX = e.motion.x;
			mouseY = e.motion.y;
		}

		if(e.type == SDL_KEYDOWN) {
			int value = e.key.keysym.scancode;
			
			if(!lastKeys[value]) {
				lastKeys[value] = true;
				downKeys[value] = true;
			}
		}

		if(e.type == SDL_KEYUP)	{
			int value = e.key.keysym.scancode;

			lastKeys[value] = false;
			upKeys[value] = true;
		}

		if(e.type == SDL_MOUSEBUTTONDOWN) {
			int value = e.button.button;

			if(!lastMouse[value]) {
				lastMouse[value] = true;
				downMouse[value] = true;
			}
		}

		if(e.type == SDL_MOUSEBUTTONUP)	{
			int value = e.button.button;

			lastMouse[value] = false;
			upMouse[value] = true;
		}
	}
}

void Input::SetMousePosition(Vector2f pos) {
	SDLBackend::Window_SetMousePosition((int)pos.GetX(), (int)pos.GetY());
}

void Input::SetCursor(bool visible) {
	SDL_ShowCursor(visible ? 1 : 0);
}

bool Input::GetKey(int keyCode) {
	return lastKeys[keyCode];
}

bool Input::GetKeyDown(int keyCode) {
	return GetKey(keyCode) && downKeys[keyCode];
}

bool Input::GetKeyUp(int keyCode) {
	return upKeys[keyCode];
}

bool Input::GetMouse(int button) {
	return lastMouse[button];
}

bool Input::GetMouseDown(int button) {
	return GetMouse(button) && downMouse[button];
}

bool Input::GetMouseUp(int button) {
	return upMouse[button];
}

Vector2f Input::GetMousePosition() {
	Vector2f res((float)mouseX, (float)mouseY);

	return res;
}