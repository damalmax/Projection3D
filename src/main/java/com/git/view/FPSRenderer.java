package com.git.view;

import com.sun.opengl.util.j2d.TextRenderer;

import java.awt.Font;
import javax.media.opengl.GLAutoDrawable;

/**
 * Enter class description.
 * <p/>
 * Date: 5/12/13
 */
public class FPSRenderer extends AbstractRenderer implements Renderer {

    private long t0;
    private long t1;
    private int fps;
    private int frames;
    private TextRenderer textRenderer;

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        textRenderer = new TextRenderer(new Font("Default", Font.PLAIN, 20));
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        frames++;
        t1 = System.currentTimeMillis();
        if (t1 - t0 >= 1000) {
            fps = frames;
            t0 = t1;
            frames = 0;
        }

        textRenderer.beginRendering(drawable.getWidth(), drawable.getHeight());
        textRenderer.setColor(1f, 1f, 0f, 1f);
        textRenderer.draw("fps: " + String.valueOf(fps), 0, drawable.getHeight() - 20);
        textRenderer.endRendering();
    }

}
