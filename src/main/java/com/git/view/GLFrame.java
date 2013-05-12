package com.git.view;

import javax.media.opengl.GLCanvas;

/**
 * Enter class description.
 * <p/>
 * Date: 5/12/13
 */
public class GLFrame extends GLCanvas {


    private Renderer glRenderer;
    private Renderer fpsRenderer;

    public GLFrame() {
        super(null);
        /* create renders*/
        glRenderer = new GLRenderer(this);
        fpsRenderer = new FPSRenderer();
        addGLEventListener(glRenderer);
        addGLEventListener(fpsRenderer);
    }

    public void dispose() {
        glRenderer.dispose();
        fpsRenderer.dispose();
    }
}