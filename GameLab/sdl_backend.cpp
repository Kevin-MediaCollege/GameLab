#include "sdl_backend.h"

SDL_Window* SDLBackend::s_window;
SDL_GLContext SDLBackend::s_glContext;
	
bool SDLBackend::s_window_isCloseRequested = false;

void SDLBackend::Window_Create(const char* title, int x, int y, int width, int height, bool fullscreen) {
	int mode = fullscreen ? SDL_WINDOW_FULLSCREEN : 0;
	
	s_window = SDL_CreateWindow(title, x, y, width, height, SDL_WINDOW_OPENGL | mode);
	s_glContext = SDL_GL_CreateContext(s_window);

	SDL_GL_SetSwapInterval(1);
}

void SDLBackend::Window_Destroy() {
	if(!s_window)
		return;

	SDL_GL_DeleteContext(s_glContext);
	SDL_DestroyWindow(s_window);
}

void SDLBackend::Window_SwapBuffers() {
	if(!s_window)
		return;

	SDL_GL_SwapWindow(s_window);
}

void SDLBackend::Window_RequestClose() {
	s_window_isCloseRequested = true;
}

bool SDLBackend::Window_IsCloseRequested() {
	return s_window_isCloseRequested;
}

void SDLBackend::Window_SetTitle(const char* title) {
	if(!s_window)
		return;

	SDL_SetWindowTitle(s_window, title);
}

void SDLBackend::Window_SetSize(int width, int height) {
	if(!s_window)
		return;

	SDL_SetWindowSize(s_window, width, height);
}

void SDLBackend::Window_SetFullscreen(bool fullscreen) {
	if(!s_window)
		return;

	int mode = fullscreen ? SDL_WINDOW_FULLSCREEN : 0;

	SDL_SetWindowFullscreen(s_window, mode);
}

void SDLBackend::Window_SetMousePosition(int x, int y) {
	if(!s_window)
		return;

	SDL_WarpMouseInWindow(s_window, x, y);
}