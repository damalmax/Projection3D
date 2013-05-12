package com.git.model;

import javax.media.opengl.GL;

/**
 * Enter class description.
 * <p/>
 * Date: 5/12/13
 */
public class Cube {


    /**
     * Draws a cube in the given GL2 context
     * @param gl - GL2 - the GL2 context
     * @param sideLength - float - a side length
     */
    public static void draw(GL gl, float sideLength){
        float halfSide = sideLength / 2;
        gl.glBegin(GL.GL_QUADS);

        // forward : blue
       // gl.glColor3f(0.0f, 0.0f, 1.0f);
        // front face
       // //gl.glTexCoord2f(0.0f, 1.0f);                // bottom left
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
       // //gl.glTexCoord2f(1.0f, 1.0f);                // bottom right
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
      //  //gl.glTexCoord2f(1.0f, 0.0f);                // top right
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
       // //gl.glTexCoord2f(0.0f, 0.0f);                // top left
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);

        // right face
       // //gl.glTexCoord2f(0.0f, 1.0f);                // bottom left
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
       // //gl.glTexCoord2f(1.0f, 1.0f);                // bottom right
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
       // //gl.glTexCoord2f(1.0f, 0.0f);                // top right
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
      //  //gl.glTexCoord2f(0.0f, 0.0f);                // top left
        gl.glVertex3f(1.0f, 1.0f, 1.0f);

        // left face
        //gl.glTexCoord2f(0.0f, 1.0f);                // bottom left
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        //gl.glTexCoord2f(1.0f, 1.0f);                // bottom right
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        //gl.glTexCoord2f(1.0f, 0.0f);                // top right
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
        //gl.glTexCoord2f(0.0f, 0.0f);                // top left
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);

        // back face
        //gl.glTexCoord2f(0.0f, 1.0f);                // bottom left
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        //gl.glTexCoord2f(1.0f, 1.0f);                // bottom right
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        //gl.glTexCoord2f(1.0f, 0.0f);                // top right
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        //gl.glTexCoord2f(0.0f, 0.0f);                // top left
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
    }
}
