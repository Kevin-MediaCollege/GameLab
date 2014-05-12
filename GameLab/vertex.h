#pragma once

#include "math3d.h"

struct Vertex {
	Vector3f position;
	Vector2f texCoord;

	Vertex(Vector3f& position, Vector2f& texCoord = Vector2f(0, 0)) :
		position(position),
		texCoord(texCoord) {}
};