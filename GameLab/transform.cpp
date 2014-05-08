#include "transform.h"

Transform::Transform(const Vector3f& position, const Quaternion& rotation, const Vector3f& scale) {
	m_position = position;
	m_rotation = rotation;
	m_scale = scale;

	m_initializedOldVariables = false;
	m_parent = 0;

	m_parentMatrix = Matrix4f().InitIdentity();
}

void Transform::Update() {
	if(m_initializedOldVariables) {
		m_oldPosition = m_position;
		m_oldRotation = m_rotation;
		m_oldScale = m_scale;
	} else {
		m_oldPosition = m_position + Vector3f(1, 1, 1);
		m_oldRotation = m_rotation * 0.5f;
		m_oldScale = m_scale + Vector3f(1, 1, 1);

		m_initializedOldVariables = true;
	}
}

void Transform::Translate(const Vector3f& direction) {
	m_position += direction;
}

void Transform::Rotate(const Vector3f& axis, float angle) {
	Rotate(Quaternion(axis, angle));
}

void Transform::Rotate(const Quaternion& rotation) {
	m_rotation = Quaternion((rotation * m_rotation).Normalized());
}

void Transform::LookAt(const Vector3f& point, const Vector3f& up) {
	m_rotation = GetLookAtRotation(point, up);
}

Matrix4f Transform::GetTransformation() const {
	Matrix4f translationMatrix = Matrix4f().InitTranslation(Vector3f(m_position.GetX(), m_position.GetY(), m_position.GetZ()));
	Matrix4f scaleMatrix = Matrix4f().InitScale(Vector3f(m_scale.GetX(), m_scale.GetY(), m_scale.GetZ()));

	return GetParentMatrix() * (translationMatrix * m_rotation.ToRotationMatrix() * scaleMatrix);
}

Matrix4f Transform::GetParentMatrix() const {
	if(m_parent != 0 && m_parent -> HasChanged())
		m_parentMatrix = m_parent -> GetTransformation();

	return m_parentMatrix;
}

bool Transform::HasChanged() {
	if(m_parent != 0 && m_parent -> HasChanged())
		return true;

	if(m_position != m_oldPosition)
		return true;

	if(m_rotation != m_oldRotation)
		return true;

	if(m_scale != m_oldScale)
		return true;

	return false;
}