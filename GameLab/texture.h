#pragma once

#include <string>
#include <GL/glew.h>

class Texture {
public:
	Texture(const std::string& fileName, GLenum textureType = GL_TEXTURE_2D, GLfloat filter = GL_LINEAR);
	virtual ~Texture();

	void Bind(unsigned int unit = 0) const;
protected:
private:
	GLenum m_textureType;
	GLenum m_textureId;

	Texture(const Texture& other) {}
	void operator=(const Texture& other) {}
};