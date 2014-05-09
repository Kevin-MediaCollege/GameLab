#pragma once

#include "component.h"
#include "math3d.h"

class Camera : public Component {
public:
	Camera(const Matrix4f& projection);

	virtual void AddToCoreEngine(CoreEngine* coreEngine);

	Matrix4f GetProjection() const;
protected:
private:
	Matrix4f m_projection;
};

