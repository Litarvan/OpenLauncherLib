/*
 * Copyright 2015 TheShark34
 *
 * This file is part of the OpenLauncherLib.

 * The OpenLauncherLib is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The OpenLauncherLib is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the OpenLauncherLib.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.theshark34.openlauncherlib.launcher.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The Window Mover
 *
 * <p>
 *     This class when added as a mouse listener and mouse motion listener to a JFrame
 *     will move it when the user will click on it.
 * </p>
 *
 * To add it :
 *
 * <code>
 *     WindowMover mover = new WindowMover(frame);
 *     frame.addMouseListener(mover);
 *     frame.addMouseMotionListener(mover);
 * </code>
 *
 * @author TheShark34
 * @version 2.1-SNAPSHOT
 */
public class WindowMover extends MouseAdapter {

    /**
     * The initial click point
     */
    private Point click;

    /**
     * The window to move
     */
    private JFrame window;

    /**
     * Basic constructor
     *
     * @param window
     *            The window to move
     */
    public WindowMover(JFrame window) {
        this.window = window;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // If the initial click point isn't null (can happen sometimes)
        if (click != null) {
            // Get the dragged point
            Point draggedPoint = MouseInfo.getPointerInfo()
                    .getLocation();

            // And move the window
            window.setLocation(new Point((int) draggedPoint.getX()
                    - (int) click.getX(), (int) draggedPoint
                    .getY() - (int) click.getY()));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        click = e.getPoint();
    }

}
