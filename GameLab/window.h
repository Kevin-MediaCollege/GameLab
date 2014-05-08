#pragma once

#include "math3d.h"

#include <string>
#include <SDL2/SDL.h>

class Window {
public:
	static void Create(int width, int height, const std::string& title, bool fullscreen = false, int x = SDL_WINDOWPOS_CENTERED, int y = SDL_WINDOWPOS_CENTERED);
	static void Destroy();
	static void Update();

	static bool IsCloseRequested();
	static bool IsFullscreen();
	static bool IsCreated();

	static void SetTitle(const std::string& title);
	static void SetSize(int width, int height);
	static void SetFullscreen(bool fullscreen);

	static const std::string& GetTitle();
	static const Vector2i GetCenter();

	static float GetAspectRatio();
	static int GetWidth();
	static int GetHeight();
protected:
private:
	static std::string s_title;

	static int s_width;
	static int s_height;

	static bool s_fullscreen;
	static bool s_created;
};