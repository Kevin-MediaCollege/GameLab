#include "rendering_engine.h"
#include "window.h"
#include "game_object.h"

#include <GL/glew.h>
RenderingEngine::RenderingEngine() {
	glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

	/*glFrontFace(GL_CW);
	glCullFace(GL_BACK);

	glEnable(GL_CULL_FACE);
	glEnable(GL_DEPTH_CLAMP);*/

	glEnable(GL_DEPTH_TEST);
	glEnable(GL_NORMALIZE);
	
	// TODO: Temporary drawing solution
	glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
	glOrtho(0, Window::GetWidth(), 0, Window::GetHeight(), -1000, 1500);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
}

RenderingEngine::~RenderingEngine() {

}

void RenderingEngine::Render(GameObject* gameObject) {
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	gameObject -> RenderAll(this);
}