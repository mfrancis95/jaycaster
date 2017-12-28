#version 330

in vec2 texturePosition;

uniform sampler2D tex;

out vec4 color;

void main() {
    color = texture(tex, texturePosition);
}