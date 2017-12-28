#version 330

in vec2 position;
in vec2 uv;

out vec2 texturePosition;

void main() {
    texturePosition = uv;
    gl_Position = vec4(position, 0, 1);
}