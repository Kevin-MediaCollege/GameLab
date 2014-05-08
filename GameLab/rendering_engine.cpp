#include "rendering_engine.h"
#include "window.h"
#include "game_object.h"

#include <GL/glew.h>

RenderingEngine::RenderingEngine() {
	glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

	glFrontFace(GL_CW);
	glCullFace(GL_BACK);

	glEnable(GL_CULL_FACE);
	glEnable(GL_DEPTH_TEST);
	glEnable(GL_DEPTH_CLAMP);
}

RenderingEngine::~RenderingEngine() {

}

void RenderingEngine::Render(GameObject* gameObject) {
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	gameObject -> RenderAll(this);
}