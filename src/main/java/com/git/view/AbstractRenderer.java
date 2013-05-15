package com.git.view;

import javax.media.opengl.GLAutoDrawable;

/**
 * Enter class description.
 * <p/>
 * Date: 5/12/13
 */
public abstract class AbstractRenderer implements Renderer {

    @Override
    public void dispose() {
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i2, int i3, int i4) {
    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b2) {
    }
}
