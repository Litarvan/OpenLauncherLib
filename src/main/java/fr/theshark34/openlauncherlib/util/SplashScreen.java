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
package fr.theshark34.openlauncherlib.util;

import javax.swing.*;
import java.awt.*;

/**
 * The Splash Screen
 *
 * <p>
 *     This class cans create a splash screen with an image.
 * </p>
 *
 * @author TheShark34
 * @version 2.0-SNAPSHOT
 */
public class SplashScreen extends JFrame {

    /**
     * Basic Constructor
     *
     * @param title
     *            The Window title
     * @param image
     *            The splash image
     */
    public SplashScreen(String title, Image image) {
        this.setTitle(title);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(image.getWidth(this), image.getHeight(this));
        this.setLocationRelativeTo(null);
        this.setContentPane(new SplashPanel(image));
    }

}

/**
 * The Splash Panel
 *
 * <p>
 *     The container of a SplashScreen with an image in background
 * </p>
 *
 * @author TheShark34
 * @version 2.0-SNAPSHOT
 */
class SplashPanel extends JPanel {

    /**
     * The splash image
     */
    private Image image;

    /**
     * Basic constructor
     *
     * @param image
     *            The splash image
     */
    public SplashPanel(Image image) {
        this.image = image;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }

}
