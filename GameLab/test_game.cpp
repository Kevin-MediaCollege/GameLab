#include "test_game.h"
#include "test_component.h"
#include "immediate_renderer.h"
#include "texture.h"
#include "math3d.h"
#include "window.h"

#include <iostream>
#include <time.h>

void TestGame::Init() {
	Texture* textures[4] = { new Texture("beach.png"), new Texture("dirt.png"), new Texture("grass.png"), new Texture("water.png") };

	for(int z = 0; z < 56; z++) {
		for(int x = -27; x < 29; x++) {
			GameObject* go = new GameObject();

			Texture* texture = textures[rand() % 4];

			go -> AddComponent(new ImmediateRenderer(new Texture("beach.png"), new Vector2f(32, 32), new Vector3f(1, 1, 1)));
			go -> GetTransform().SetPosition(Vector3f(x * 32, 0, z * 32));

			AddChild(go);
		}
	}
}
