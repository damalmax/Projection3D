package com.git.view;

import com.sun.opengl.util.BufferUtil;
import com.sun.opengl.util.FPSAnimator;
import com.sun.opengl.util.GLUT;
import com.sun.opengl.util.j2d.TextRenderer;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.FloatBuffer;
import java.text.MessageFormat;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

/**
 * Enter class description.
 * <p/>
 * Date: 5/12/13
 */
public class GLRenderer implements Renderer, KeyListener {


    /**
     * The frames per second setting.
     */
    private int fps = 60;

    /**
     * The OpenGL animator.
     */
    private FPSAnimator animator;

    private GLFrame glFrame;

    private TextRenderer textRenderer;


    /*  Globals */
    double dim = 3.0; /* dimension of orthogonal box */


    /*  Various global state */
    int toggleAxes = 0;   /* toggle axes on and off */
    int toggleValues = 1; /* toggle values on and off */
    int toggleMode = 0; /* projection mode */
    int th = 0;   /* azimuth of view angle */
    int ph = 0;   /* elevation of view angle */
    int fov = 10; /* field of view for perspective */
    int asp = 1;  /* aspect ratio */

    private static final int PERSPECTIVE = 1;

    private float vertA[] = {0.5f, 0.5f, 0.5f};
    private float vertB[] = {-0.5f, 0.5f, 0.5f};
    private float vertC[] = {-0.5f, -0.5f, 0.5f};
    private float vertD[] = {0.5f, -0.5f, 0.5f};
    private float vertE[] = {0.5f, 0.5f, -0.5f};
    private float vertF[] = {-0.5f, 0.5f, -0.5f};
    private float vertG[] = {-0.5f, -0.5f, -0.5f};
    private float vertH[] = {0.5f, -0.5f, -0.5f};

    public GLRenderer(GLFrame glFrame) {
        this.glFrame = glFrame;
        animator = new FPSAnimator(glFrame, fps);
        animator.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(GLAutoDrawable drawable) {
        drawable.addKeyListener(this);
        textRenderer = new TextRenderer(new Font("Default", Font.PLAIN, 15));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
       /*  Clear the image */
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        /*  Enable Z-buffering in OpenGL */
        gl.glEnable(GL.GL_DEPTH_TEST);
         /*  Reset previous transforms */
        gl.glLoadIdentity();

        /*  Perspective - set eye position */
        if (toggleMode == PERSPECTIVE) {
            double Ex = -2 * dim * Math.sin(th) * Math.cos(ph);
            double Ey = +2 * dim * Math.sin(ph);
            double Ez = +2 * dim * Math.cos(th) * Math.cos(ph);
            /* camera/eye position, aim of camera lens, up-vector */
            glu.gluPerspective(fov, asp, dim / 4, 4 * dim);
            glu.gluLookAt(Ex, Ey, Ez, 0, 0, 0, 0, Math.cos(ph), 0);
        }
         /*  Orthogonal - set world orientation */
        else {
            gl.glRotatef(ph, 1, 0, 0);
            gl.glRotatef(th, 0, 1, 0);
        }


        /*  Draw  */
        drawAxes(gl);
        drawValues(drawable);
        drawShape(gl);

        /*  Flush and swap */
        gl.glFlush(); //instead of calling glutSwapBuffers()()
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        gl.glViewport(0, 0, width, height);

        asp = (height > 0) ? width / height : 1;
        setProjection(gl, glu);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b2) {
    }


    private void drawAxes(GL gl) {
        GLUT glut = new GLUT();
        if (toggleAxes == 1) {
           /*  Length of axes */
            double len = 2.0;
            gl.glColor3f(1.0f, 1.0f, 1.0f);
            gl.glBegin(GL.GL_LINES);
            gl.glVertex3d(0, 0, 0);
            gl.glVertex3d(len, 0, 0);
            gl.glVertex3d(0, 0, 0);
            gl.glVertex3d(0, len, 0);
            gl.glVertex3d(0, 0, 0);
            gl.glVertex3d(0, 0, len);
            gl.glEnd();
            /*  Label axes */
            gl.glRasterPos3d(len, 0, 0);
            print(glut, "X");
            gl.glRasterPos3d(0, len, 0);
            print(glut, "Y");
            gl.glRasterPos3d(0, 0, len);
            print(glut, "Z");
        }
    }


    /*
     *  Draw the values in the lower left corner.
     */
    private void drawValues(GLAutoDrawable drawable) {
        if (toggleValues == 1) {
            GLUT glut = new GLUT();
            GL gl = drawable.getGL();
            gl.glColor3f(0.8f, 0.8f, 0.8f);
            printAt(drawable, MessageFormat.format("View Angle: th={0}, ph={1}, fov={2}", th, ph, fov), 0, 5);
            printAt(drawable, MessageFormat.format("Projection mode =({0})", toggleMode == 1 ? "Perspective" : "Orthogonal"), 0, 25);
            gl.glRasterPos3fv(createFloatBuffer(vertA));
            print(glut, "A");
            gl.glRasterPos3fv(createFloatBuffer(vertB));
            print(glut, "B");
            gl.glRasterPos3fv(createFloatBuffer(vertC));
            print(glut, "C");
            gl.glRasterPos3fv(createFloatBuffer(vertD));
            print(glut, "D");
            gl.glRasterPos3fv(createFloatBuffer(vertE));
            print(glut, "E");
            gl.glRasterPos3fv(createFloatBuffer(vertF));

            print(glut, "F");
            gl.glRasterPos3fv(createFloatBuffer(vertG));
            print(glut, "G");
            gl.glRasterPos3fv(createFloatBuffer(vertH));
            print(glut, "H");
        }
    }

    private FloatBuffer createFloatBuffer(float[] mas) {
        FloatBuffer fb = BufferUtil.newFloatBuffer(mas.length);
        fb.put(mas);
        fb.rewind();
        return fb;
    }

    /*
     *  Draw the GLUT shape.
     */
    private void drawShape(GL gl) {
        /* Cube */
        gl.glBegin(GL.GL_QUADS);
        /* front => ABCD yellow */
        gl.glColor3f(1.0f, 1.0f, 0.0f);
        gl.glVertex3fv(createFloatBuffer(vertA));
        gl.glVertex3fv(createFloatBuffer(vertB));
        gl.glVertex3fv(createFloatBuffer(vertC));
        gl.glVertex3fv(createFloatBuffer(vertD));
        /* back => FEHG red */
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3fv(createFloatBuffer(vertF));
        gl.glVertex3fv(createFloatBuffer(vertE));
        gl.glVertex3fv(createFloatBuffer(vertH));
        gl.glVertex3fv(createFloatBuffer(vertG));
        /* right => EADH green */
        gl.glColor3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3fv(createFloatBuffer(vertE));
        gl.glVertex3fv(createFloatBuffer(vertA));
        gl.glVertex3fv(createFloatBuffer(vertD));
        gl.glVertex3fv(createFloatBuffer(vertH));
        /* left => BFGC blue */
        gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3fv(createFloatBuffer(vertB));
        gl.glVertex3fv(createFloatBuffer(vertF));
        gl.glVertex3fv(createFloatBuffer(vertG));
        gl.glVertex3fv(createFloatBuffer(vertC));
        /* top => EFBA turquoise */
        gl.glColor3f(0.0f, 1.0f, 1.0f);
        gl.glVertex3fv(createFloatBuffer(vertE));
        gl.glVertex3fv(createFloatBuffer(vertF));
        gl.glVertex3fv(createFloatBuffer(vertB));
        gl.glVertex3fv(createFloatBuffer(vertA));
        /* bottom => DCGH pink */
        gl.glColor3f(1.0f, 0.0f, 1.0f);
        gl.glVertex3fv(createFloatBuffer(vertD));
        gl.glVertex3fv(createFloatBuffer(vertC));
        gl.glVertex3fv(createFloatBuffer(vertG));
        gl.glVertex3fv(createFloatBuffer(vertH));
        gl.glEnd();
    }

    @Override
    public void dispose() {
        animator.stop();
    }

    @Override
    public void keyTyped(KeyEvent key) {

    }

    @Override
    public void keyPressed(KeyEvent key) {
         /*  Right arrow key - increase azimuth by 5 degrees */
        if (key.getKeyCode() == KeyEvent.VK_RIGHT) {
            th += 5;
        }
        /*  Left arrow key - decrease azimuth by 5 degrees */
        if (key.getKeyCode() == KeyEvent.VK_LEFT) {
            th -= 5;
        }
         /*  Up arrow key - increase elevation by 5 degrees */
        if (key.getKeyCode() == KeyEvent.VK_UP) {
            ph += 5;
        }
        /*  Down arrow key - decrease elevation by 5 degrees */
        if (key.getKeyCode() == KeyEvent.VK_DOWN) {
            ph -= 5;
        }
        if (key.getKeyCode() == KeyEvent.VK_EQUALS) {

        }
          /*  Change field of view angle */
        if (key.getKeyCode() == KeyEvent.VK_MINUS) {
            fov--;
            System.out.println(fov);

        }
        if (key.getKeyCode() == KeyEvent.VK_PLUS) {
            fov++;
            System.out.println(fov);

        }
        if (key.getKeyCode() == KeyEvent.VK_OPEN_BRACKET) {

        }
        if (key.getKeyCode() == KeyEvent.VK_CLOSE_BRACKET) {

        }
          /*  Change dimensions */
        if (key.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            dim += 0.1;
        }
        if (key.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            dim -= 0.1;

        }

        if (key.getKeyCode() == KeyEvent.VK_A) {
            toggleAxes = 1 - toggleAxes;

        }
        if (key.getKeyCode() == KeyEvent.VK_V) {
            toggleValues = 1 - toggleValues;

        }
        if (key.getKeyCode() == KeyEvent.VK_M) {
            toggleMode = 1 - toggleMode;
            System.out.println("toggleMode was set to: " + toggleMode);
        }

        th %= 360;
        ph %= 360;
        glFrame.display();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * Sets the projection.
     */
    private void setProjection(GL gl, GLU glu) {
        // Change to projection matrix.
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        if (toggleMode == PERSPECTIVE) {
            /* perspective projection*/
            System.out.println("project:: perspective");
            glu.gluPerspective(fov, asp, dim / 4, 4 * dim);
        } else {
            /* orthogonal projection*/
            System.out.println("project:: orthogonal projection");
            gl.glOrtho(-dim * asp, +dim * asp, -dim, +dim, -dim, +dim);
        }

        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    private void printAt(GLAutoDrawable drawable, String text, int x, int y) {
        textRenderer.beginRendering(drawable.getWidth(), drawable.getHeight());
        textRenderer.setColor(1f, 1f, 0f, 1f);
        textRenderer.draw(text, x, y);
        textRenderer.endRendering();
    }

    private void print(GLUT glut, int size, String text) {
        glut.glutBitmapString(size, text);
    }

    private void print(GLUT glut, String text) {
        print(glut, 5, text);
    }
}
