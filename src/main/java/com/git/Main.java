package com.git;

import com.git.view.GLFrame;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Enter class description.
 * <p/>
 * Date: 5/12/13
 */
public class Main {
    public static void main(String[] args) {

        GLFrame glFrame = new GLFrame();
        glFrame.requestFocus();
        addCanvasToFrame(glFrame);
    }

    private static void addCanvasToFrame(
        final GLFrame glFrame) {
        Frame f = new Frame("JOGL Rotating Half - Cube");
        f.setSize(600, 400);
        f.add(glFrame);
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                glFrame.dispose();
                System.exit(0);
            }
        });
    }

}
