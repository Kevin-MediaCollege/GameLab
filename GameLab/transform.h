#pragma once

#include "math3d.h"

class Transform {
public:
	Transform(const Vector3f& position = Vector3f(0, 0, 0), const Quaternion& rotation = Quaternion(0, 0, 0, 1), const Vector3f& scale = Vector3f(1, 1, 1));

	void Update();

	void Translate(const Vector3f& direction);
	void Rotate(const Vector3f& axis, float angle);
	void Rotate(const Quaternion& rotation);
	
	void LookAt(const Vector3f& point, const Vector3f& up);

	Matrix4f GetTransformation() const;
	Quaternion GetLookAtRotation(const Vector3f& point, const Vector3f& up) { return Quaternion(Matrix4f().InitRotationFromDirection((point - m_position).Normalized(), up)); }

	inline void SetParent(Transform* parent) { m_parent = parent; }
	inline void SetPosition(const Vector3f& position) { m_position = position; }
	inline void SetRotation(const Quaternion& rotation) { m_rotation = rotation; }
	inline void SetScale(const Vector3f& scale) { m_scale = scale; }

	inline Vector3f& GetLocalPosition() { return m_position; }
	inline const Vector3f& GetLocalPosition() const { return m_position; }
	inline Vector3f GetPosition() const { return Vector3f(GetParentMatrix().Transform(m_position)); }

	inline Quaternion& GetLocalRotation() { return m_rotation; }
	inline const Quaternion GetLocalRotation() const { return m_rotation; }
	inline Quaternion GetRotation() const {
		Quaternion parentRotation = Quaternion(0, 0, 0, 1);

		if(m_parent)
			parentRotation = m_parent -> GetRotation();

		return parentRotation * m_rotation;
	}
	
	inline Vector3f& GetLocalScale() { return m_scale; }
	inline const Vector3f& GetLocalScale() const { return m_scale; }
protected:
private:
	Matrix4f GetParentMatrix() const;

	bool HasChanged();

	Vector3f m_position;
	Quaternion m_rotation;
	Vector3f m_scale;

	Transform* m_parent;

	mutable Vector3f m_oldPosition;
	mutable Quaternion m_oldRotation;
	mutable Vector3f m_oldScale;
	mutable Matrix4f m_parentMatrix;
	mutable bool m_initializedOldVariables;
};