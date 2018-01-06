#version 330

in vec3 position;
in vec2 uv;
in vec3 normal;

uniform struct {
    vec3 position;
    vec3 direction;
} camera;

uniform struct {
    float fov;
    float aspectRatio;
    float near;
    float far;
} projection;

out vec2 texturePosition;
out vec4 normalCam;

mat4 createProjectionMatrix() {
    return mat4(
        projection.fov / projection.aspectRatio, 0, 0, 0,
        0, projection.fov, 0, 0,
        0, 0, (projection.near + projection.far) / (projection.near - projection.far), -1,
        0, 0, 2 * projection.near * projection.far / (projection.near - projection.far), 0
    );
}

mat4 createViewMatrix() {
    vec3 nVec = normalize(-camera.direction);
    vec3 uVec = normalize(cross(vec3(0, 1, 0), nVec));
    vec3 vVec = normalize(cross(nVec, uVec));
    return mat4(
        uVec.x, vVec.x, nVec.x, 0,
        uVec.y, vVec.y, nVec.y, 0,
        uVec.z, vVec.z, nVec.z, 0,
        -dot(uVec, camera.position), -dot(vVec, camera.position), -dot(nVec, camera.position), 1 
    );
}

void main() {
    texturePosition = uv;
    mat4 viewModelMatrix = createViewMatrix();
    mat4 normalMatrix = transpose(inverse(viewModelMatrix));
    normalCam = vec4(mat3(normalMatrix) * normal, 0);
    gl_Position = createProjectionMatrix() * viewModelMatrix * vec4(position, 1);
}