#version 330

in vec2 texturePosition;
in vec4 normalCam;

uniform struct {
    vec4 color;
    vec3 direction;
} light;

uniform sampler2D tex;

out vec4 color;

void main() {
    vec4 N = normalize(normalCam);
    vec4 L = normalize(-vec4(light.direction, 1));
    vec4 diffuse = vec4(1, 1, 1, 1) * max(0, dot(L, N));
    color = (light.color + diffuse) * texture(tex, texturePosition);
}