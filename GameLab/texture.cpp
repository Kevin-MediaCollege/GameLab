#include "texture.h"
#include "stb_image.h"

#include <iostream>
#include <cassert>

Texture::Texture(const std::string& fileName, GLenum textureType, GLfloat filter) {
	int x, y, bytesPerPixel;

	unsigned char* data = stbi_load(("./res/textures/" + fileName).c_str(), &x, &y, &bytesPerPixel, 4);

	if(data == NULL)
		std::cerr << "Unable to load texture: " << fileName << std::endl;

	glGenTextures(1, &m_textureId);
	glActiveTexture(GL_TEXTURE0);
	glBindTexture(textureType, m_textureId);

	glTexParameteri(textureType, GL_TEXTURE_WRAP_S, GL_REPEAT);
	glTexParameteri(textureType, GL_TEXTURE_WRAP_T, GL_REPEAT);

	glTexParameterf(textureType, GL_TEXTURE_MIN_FILTER, filter);
	glTexParameterf(textureType, GL_TEXTURE_MAG_FILTER, filter);

	glTexImage2D(textureType, 0, GL_RGBA8, x, y, 0, GL_RGBA, GL_UNSIGNED_BYTE, data);

	stbi_image_free(data);

	m_textureType = textureType;
}

Texture::~Texture() {
	if(m_textureId)
		glDeleteTextures(1, &m_textureId);
}

void Texture::Bind(unsigned int unit) const {
	assert(unit >= 0 && unit <= 31);

	glActiveTexture(GL_TEXTURE0 + unit);

	glBindTexture(m_textureType, m_textureId);
}