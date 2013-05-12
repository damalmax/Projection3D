package com.git.util;

import javax.vecmath.Matrix4f;

/**
 * Projection.
 * <p/>
 * Date: 5/12/13
 */
public final class Projection {

    private Projection() {
    }

    /**
     * Creates a Matrix4f that generates a perspective projection.
     *
     * @param left   left
     * @param right  right
     * @param bottom bottom
     * @param top    top
     * @param near   near
     * @param far    far
     * @return {@link Matrix4f}
     */
    public static Matrix4f perspective(float left, float right, float bottom, float top, float near, float far) {
        Matrix4f matrix = new Matrix4f();

        matrix.setIdentity();

        matrix.m00 = 2 * near / (right - left);
        matrix.m11 = 2 * near / (top - bottom);
        matrix.m22 = -(far + near) / (far - near);
        matrix.m23 = -1;
        matrix.m32 = -2 * far * near / (far - near);
        matrix.m20 = (right + left) / (right - left);
        matrix.m21 = (top + bottom) / (top - bottom);
        matrix.m33 = 0;

        return matrix;
    }

// creates a Matrix4f that generates an orthogonal projection

    /**
     * Creates a Matrix4f that generates an orthogonal projection.
     *
     * @param left   left
     * @param right  right
     * @param bottom bottom
     * @param top    top
     * @param near   near
     * @param far    far
     * @return {@link Matrix4f}
     */
    public static Matrix4f orthogonal(float left, float right, float bottom, float top, float near, float far) {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();

        matrix.m00 = 2 / (right - left);
        matrix.m11 = 2 / (top - bottom);
        matrix.m22 = -2 / (far - near);
        matrix.m32 = (far + near) / (far - near);
        matrix.m30 = (right + left) / (right - left);
        matrix.m31 = (top + bottom) / (top - bottom);

        return matrix;
    }
}
