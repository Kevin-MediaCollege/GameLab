#pragma once

#include <SDL2/SDL.h>

class SDLBackend {
public:
	static void Window_Create(const char* title, int x, int y, int width, int height, bool fullscreen);
	static void Window_Destroy();
	static void Window_SwapBuffers();
	static void Window_RequestClose();

	static bool Window_IsCloseRequested();

	static void Window_SetTitle(const char* title);
	static void Window_SetSize(int width, int height);
	static void Window_SetFullscreen(bool fullscreen);
	static void Window_SetMousePosition(int x, int y);
protected:
private:
	static SDL_Window* s_window;
	static SDL_GLContext s_glContext;
	
	static bool s_window_isCloseRequested;
};

