#include "window.h"
#include "sdl_backend.h"

#include <iostream>
#include <GL/glew.h>

std::string Window::s_title = "Engine";

int Window::s_width = 0;
int Window::s_height = 0;

bool Window::s_fullscreen = false;
bool Window::s_created = false;

void Window::Create(int width, int height, const std::string& title, bool fullscreen, int x, int y) {
	s_width = width;
	s_height = height;
	s_title = title;
	s_fullscreen = fullscreen;
	s_created = true;
	
	SDL_Init(SDL_INIT_EVERYTHING);

	SDL_GL_SetAttribute(SDL_GL_RED_SIZE, 8);
	SDL_GL_SetAttribute(SDL_GL_GREEN_SIZE, 8);
	SDL_GL_SetAttribute(SDL_GL_BLUE_SIZE, 8);
	SDL_GL_SetAttribute(SDL_GL_ALPHA_SIZE, 8);
	SDL_GL_SetAttribute(SDL_GL_BUFFER_SIZE, 32);
	SDL_GL_SetAttribute(SDL_GL_DEPTH_SIZE, 16);
	SDL_GL_SetAttribute(SDL_GL_DOUBLEBUFFER, 1);

	SDLBackend::Window_Create(title.c_str(), x, y, width, height, fullscreen);

	GLenum glewResult = glewInit();

	if(glewResult != GLEW_OK)
		std::cerr << "Glew initialization error: " << glewGetErrorString(glewResult) << std::endl;
}

void Window::Destroy() {
	SDLBackend::Window_Destroy();
	SDL_Quit();
}

void Window::Update() {
	SDLBackend::Window_SwapBuffers();
}

bool Window::IsCloseRequested() {
	return SDLBackend::Window_IsCloseRequested();
}

bool Window::IsFullscreen() {
	return s_fullscreen;
}

bool Window::IsCreated() {
	return s_created;
}

void Window::SetTitle(const std::string& title) {
	SDLBackend::Window_SetTitle(title.c_str());
}

void Window::SetSize(int width, int height) {
	SDLBackend::Window_SetSize(width, height);
}

void Window::SetFullscreen(bool fullscreen) {
	SDLBackend::Window_SetFullscreen(fullscreen);
}

const std::string& Window::GetTitle() {
	return s_title;
}

const Vector2i Window::GetCenter() {
	return Vector2i(GetWidth() / 2, GetHeight() / 2);
}

float Window::GetAspectRatio() {
	return (float)s_width / (float)s_height;
}

int Window::GetWidth() {
	return s_width;
}

int Window::GetHeight() {
	return s_height;
}