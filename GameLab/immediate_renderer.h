#pragma once

#include "component.h"
#include "math3d.h"
#include "texture.h"

class ImmediateRenderer : public Component {
public:
	ImmediateRenderer(Texture* texture, Vector2f* size, Vector3f* color) :
		m_texture(texture),
		m_size(size),
		m_color(color) {}

	virtual ~ImmediateRenderer() {
		if(m_texture)
			delete m_texture;

		if(m_size)
			delete m_size;

		if(m_color)
			delete m_color;
	}

	virtual void Render(RenderingEngine* renderingEngine) {
		glPushMatrix();
		glLoadIdentity();

		glEnable(GL_TEXTURE_2D);
		
		m_texture -> Bind();

		glRotatef(35.264f, 1.0f, 0.0f, 0.0f);
		glRotatef(-45.0f, 0.0f, 1.0f, 0.0f);
	//	glScalef(1.0f, 1.0f, -1.0f);
		glTranslatef(GetTransform().GetPosition().GetX(), 0, GetTransform().GetPosition().GetZ());

		glColor3f(m_color -> GetX(), m_color -> GetY(), m_color -> GetZ());

		glBegin(GL_POLYGON);
		
		glTexCoord2f(0, 0); glVertex2f(0, 0);
		glTexCoord2f(1, 0); glVertex2f(m_size -> GetX(), 0);
		glTexCoord2f(1, 1); glVertex2f(m_size -> GetX(), m_size -> GetY());
		glTexCoord2f(0, 1); glVertex2f(0, m_size -> GetY());

		glEnd();

		glDisable(GL_TEXTURE_2D);
		glPopMatrix();
	}
protected:
private:
	Texture* m_texture;
	Vector2f* m_size;
	Vector3f* m_color;
};