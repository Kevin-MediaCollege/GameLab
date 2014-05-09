#include "camera.h"
#include "rendering_engine.h"
#include "core_engine.h"

Camera::Camera(const Matrix4f& projection) : m_projection(projection) {}

void Camera::AddToCoreEngine(CoreEngine* coreEngine) {
	coreEngine -> GetRenderingEngine() -> SetMainCamera(this);
}

Matrix4f Camera::GetProjection() const {
	return m_projection	*
		GetTransform().GetRotation().Conjugate().ToRotationMatrix() *
		Matrix4f().InitTranslation(GetTransform().GetPosition() * -1);
}
