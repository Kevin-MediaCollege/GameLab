#include "test_game.h"
#include "test_component.h"
#include "mesh_renderer.h"
#include "texture.h"

void TestGame::Init() {
	Vertex vertices[] = {
		Vertex(Vector3f(-1, -1, 0), Vector2f(0, 0)),
		Vertex(Vector3f(-1,  1, 0), Vector2f(0, 1)),
		Vertex(Vector3f( 1,  1, 0), Vector2f(1, 1)),
		Vertex(Vector3f( 1, -1, 0), Vector2f(1, 0)),
	};

	int indices[] = {
		0, 1, 3,
		3, 1, 2
	};

	Texture texture("swag.png");

	GameObject* testGameObject = new GameObject();

	testGameObject -> AddComponent(new TestComponent());
	testGameObject -> AddComponent(new MeshRenderer()); 

	AddChild(testGameObject);
}
